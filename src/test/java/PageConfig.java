import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.chrome.ChromeDriver;
import pages.items.Product;
import pages.items.Category;
import pages.items.Specials;
import pages.states.*;

public class PageConfig {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(1600,900));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @AfterEach
    public void closeWindow() {
        driver.quit();
    }

    @Test
    public void registerAndBuyTest() {
        //Home
        Home home = new Home(driver, "https://automationteststore.com/");
        home.clickLogin();
        home.clickRegister();

//      User register on the website
        Register register = new Register(driver, driver.getCurrentUrl());

        String e = "something14@gmail.com";
        String lN = "someonee14";
        //Register -- Your personal details
        register.setFirstName("Ivan");
        register.setLastName("Yeremenko");
        register.setEmail(e);
        register.setTelephone("+42012345678");
        register.setFax("02381828219");

        //Register -- Your Adress
        register.setCompany("CTU");
        register.setAddress1("Vaníčkova 100/6, 169 00 Praha 6-Strahov");
        register.setAddress2("Vaníčkova 2464, 169 00 Praha 6-Strahov");

        register.setCity("Prague");
        register.setZIPCode("BS2");
        register.setCountry("Czech Republic");
        register.setRegion("Praha");

        //Register -- Login Details
        register.setLoginName(lN);
        register.setPassword("yeremiva123!");
        register.setPasswordConfirm("yeremiva123!");

        register.agreeToPrivacyPolicy();
        register.setSubscription();
        register.submitRegistration();

        //Click after registration
        register.clickContinue();

        //Profile settings
        Profile profile = new Profile(driver, driver.getCurrentUrl());
        profile.clickOnEditAccountDetails();
        profile.setNewFirstName("Who The Hell Is");
        profile.setNewLastName("Edgar");
        profile.setNewTelephone("+4277777777");
        profile.setNewFax("123456789");
        profile.clickContinue();
        profile.clickCategory("MAKEUP");

        //Category
        Category category = new Category(driver, driver.getCurrentUrl());
        category.sortBy("Rating Lowest");
        ArrayList<String> productLinks = category.getResultsLinks(3);

        StringBuilder dataForCSV = new StringBuilder();

        for (String link : productLinks) {
            Product product = new Product(driver, link);
            dataForCSV.append(product.getName()).append(", ");
            dataForCSV.append(product.getPrice()).append("\n");
        }

        // Writing in CSV
        try {
            FileWriter myWriter = new FileWriter("src/test/resources/three_makeup_ratinglowest_products.csv");
            myWriter.write(dataForCSV.toString());
            myWriter.close();
        } catch (IOException ee) {
            ee.printStackTrace();
        }

        //Product
        Product product = new Product(driver, driver.getCurrentUrl());
        product.chooseColor("Viva Glam II");
        product.chooseQuantity("5");
        product.addToWishlist();
        product.addToCart();

        //Shopping Cart
        ShoppingCart shoppingCart = new ShoppingCart(driver, driver.getCurrentUrl());
        shoppingCart.checkout();

        //Checkout
        Checkout checkout = new Checkout(driver, driver.getCurrentUrl());
        checkout.confirmOrder();
        checkout.clickContinue();

        //END
    }


    @Test
    public void loginSpecialsDetailsInCheckoutTest() {
        //Home
        Home home = new Home(driver, "https://automationteststore.com/");
        home.clickLogin();

        //Login
        Login login = new Login(driver, driver.getCurrentUrl());
        login.setLoginName("Wellplay");
        login.setLoginPassword("Qwerty12345");
        login.submitLogin();

        //Profile
        Profile profile = new Profile(driver, driver.getCurrentUrl());
        profile.clickSpecials();

        //Specials
        Specials specials = new Specials(driver, driver.getCurrentUrl());
        specials.chooseItemForClick("ABSOLUE EYE PRECIOUS CELLS");
        specials.clickShoppingCart();

        //Shopping Cart
        ShoppingCart shoppingCart = new ShoppingCart(driver, driver.getCurrentUrl());

        //CSV
        ArrayList<String> productLinks = shoppingCart.getResultsLinks(specials.getNumberOfProductsAddedInCart());
        StringBuilder dataForCSV = new StringBuilder();

        for (String link : productLinks) {
            System.out.println(link);
            Product product = new Product(driver, link);
            dataForCSV.append(product.getName()).append(", ");
            dataForCSV.append(product.getPrice()).append("\n");
        }

        // Writing in CSV
        try {
            FileWriter myWriter = new FileWriter("src/test/resources/shopping_cart_products.csv");
            myWriter.write(dataForCSV.toString());
            myWriter.close();
        } catch (IOException ee) {
            ee.printStackTrace();
        }
        //

        //Product
        Product product = new Product(driver, driver.getCurrentUrl());
        product.clickShoppingCart();


        shoppingCart.applyCoupon("23311302");
        //coupon is not valid
        shoppingCart.setShippingCountry("Sweden");
        shoppingCart.setShippingState("Kalmar");
        shoppingCart.setZipCode("23144");
        shoppingCart.estimateShipment();
        shoppingCart.checkout();

        //Checkout
        Checkout checkout = new Checkout(driver, driver.getCurrentUrl());
        checkout.confirmOrder();
        checkout.clickContinue();

        home.clickProfile();

        profile.clickLogOff();

        //END
    }



    @Test
    public void loginSearchManyObjectsTest() {
        //Login, серч, додати предмет в карт, потім контінью шоппінг, додати ще предмет через серч, і так 7 штук, потім карт, чекаут, лог аут.

        ArrayList<String> links = new ArrayList<>();

        //Home
        Home home = new Home(driver, "https://automationteststore.com/");
        home.clickSearch();

        //Search -- 1
        Search search = new Search(driver, driver.getCurrentUrl());
        search.setKeyword("Lipstick");
        search.submitSearch();

        //Add the link to the links
        links.add(driver.getCurrentUrl());

        //Product
        Product product = new Product(driver, driver.getCurrentUrl());
        product.addToCart();

        //Shopping Cart
        ShoppingCart shoppingCart = new ShoppingCart(driver, driver.getCurrentUrl());
        shoppingCart.continueShopping();

        product.clickSearch();

        //Search -- 2
        search.submitModel();
        search.submitDescription();
        search.setKeyword("obsession");
        search.submitSearch();
        //shows three of them
        search.setCategory("   Men");
        search.submitSearch();
        //only one

        //Add the link to the links
        links.add(driver.getCurrentUrl());

        product.addToCart();
        shoppingCart.continueShopping();
        product.clickSearch();

        //Search -- 3
        search.submitSearch();
        //nothing to show
        search.setKeyword("mas");
        search.submitDescription();
        search.submitSearch();
        search.clickOnTheArmaniToiletWaterCart();
        //Add the link to the links
        links.add("https://automationteststore.com/index.php?rt=product/product&product_id=81");

        search.clickShoppingCart();

        //CSV
        StringBuilder dataForCSV = new StringBuilder();

        for (String link : links) {
            System.out.println(link);
            product = new Product(driver, link);
            dataForCSV.append(product.getName()).append(", ");
            dataForCSV.append(product.getPrice()).append("\n");
        }

        // Writing in CSV
        try {
            FileWriter myWriter = new FileWriter("src/test/resources/searched_products.csv");
            myWriter.write(dataForCSV.toString());
            myWriter.close();
        } catch (IOException ee) {
            ee.printStackTrace();
        }
        //

        product.clickShoppingCart();
        shoppingCart.checkout();

        //Login
        Login login = new Login(driver, driver.getCurrentUrl());
        login.setLoginName("Wellplay");
        login.setLoginPassword("Qwerty12345");
        login.submitLogin();

        //Checkout
        Checkout checkout = new Checkout(driver, driver.getCurrentUrl());
        checkout.clickBack();
        checkout.setBackComment("It is the best shop I have ever seen! Please, leave the order near my door.");
        checkout.clickReturnPolicyAgree();
        checkout.clickBackContinue();
        checkout.confirmOrder();
        checkout.clickContinue();

        home.clickProfile();

        //Profile
        Profile profile = new Profile(driver, driver.getCurrentUrl());
        profile.clickLogOff();

        //END
    }
}
