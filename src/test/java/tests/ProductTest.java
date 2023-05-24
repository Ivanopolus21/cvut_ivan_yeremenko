package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.items.Product;
import pages.states.Login;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(1600,900));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @Test
    public void addingToCartTest() {
        String expectedUrl = "https://automationteststore.com/index.php?rt=checkout/cart";

        Product product = new Product(driver, "https://automationteststore.com/index.php?rt=product/product&path=36&product_id=59");
        product.clickShoppingCart();

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    public void choosingColorTest() {
        String expectedPrice = "$7.00";

        Product product = new Product(driver, "https://automationteststore.com/index.php?rt=product/product&path=36&product_id=59");
        product.chooseColor("Viva Glam VI $2.00 (986 In Stock)");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds
        WebElement totalPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("total-price")));

        assertEquals(expectedPrice, totalPriceElement.getText());
    }

    @Test
    public void choosingQuantityTest() {
        String expectedPrice = "$495.00";

        Product product = new Product(driver, "https://automationteststore.com/index.php?rt=product/product&path=36&product_id=59");
        product.chooseQuantity("99");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds
        WebElement totalPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("total-price")));

        assertEquals(expectedPrice, totalPriceElement.getText());

    }

    @Test
    public void addingToWishlistTest() {
        String expectedWishlistState = "Remove from wish list";

        Login login = new Login(driver, "https://automationteststore.com/index.php?rt=account/login");
        login.setLoginName("Wellplay");
        login.setLoginPassword("Qwerty12345");
        login.submitLogin();

        Product product = new Product(driver, "https://automationteststore.com/index.php?rt=product/product&path=36&product_id=59");
        product.addToWishlist();

        assertEquals(expectedWishlistState, driver.findElement(By.className("wishlist_remove")).getText());
    }

    @Test
    public void getNameTest() {
        String expectedName = "Creme Precieuse Nuit 50ml";

        Product product = new Product(driver, "https://automationteststore.com/index.php?rt=product/product&path=43&product_id=93");

        assertEquals(expectedName, product.getName());
    }

    @Test
    public void getPriceTest() {
        String expectedPrice = "$220.00";

        Product product = new Product(driver, "https://automationteststore.com/index.php?rt=product/product&path=43&product_id=93");

        assertEquals(expectedPrice, product.getPrice());
    }

    @Test
    public void clickShoppingCartTest() {
        String expectedUrl = "https://automationteststore.com/index.php?rt=checkout/cart";

        Product product = new Product(driver, "https://automationteststore.com/index.php?rt=product/product&path=43&product_id=93");
        product.clickShoppingCart();

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    public void clickSearchTest() {
        String expectedUrl = "https://automationteststore.com/index.php?rt=product/search&category_id=0";

        Product product = new Product(driver, "https://automationteststore.com/index.php?rt=product/product&path=43&product_id=93");
        product.clickSearch();

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    public void clickCategoryTest() {
        String expectedUrl = "https://automationteststore.com/index.php?rt=product/category&path=58";

        Product product = new Product(driver, "https://automationteststore.com/index.php?rt=product/product&path=43&product_id=93");
        product.clickCategory("MEN");

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

}