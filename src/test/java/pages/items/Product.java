package pages.items;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Product {
    private WebDriver driver;

    public Product(WebDriver driver, String url) {
        this.driver = driver;
        driver.get(url);

        PageFactory.initElements(driver, this);
    }

    public void addToCart() {
        driver.findElement(By.className("cart")).click();
    }

    public void chooseColor(String color) {
        Select select = new Select(driver.findElement(By.cssSelector("select[id^='option']")));
        select.selectByVisibleText(color);
    }

    public void chooseQuantity(String quantity) {
        driver.findElement(By.id("product_quantity")).clear();
        driver.findElement(By.id("product_quantity")).sendKeys(quantity);
    }

    public void addToWishlist() {
        driver.findElement(By.className("wishlist")).click();
    }

    public String getName() {
        return driver.findElement(By.className("bgnone")).getText();
    }

    public String getPrice() {
        return driver.findElement(By.className("productfilneprice")).getText();
    }

    public void clickShoppingCart() {
        driver.findElement(By.className("nobackground")).click();
    }

    public void clickSearch() {
        driver.findElement(By.className("button-in-search")).click();
    }

    public void clickCategory(String category) {
        String url = null;
        switch(category) {
            case ("MAKEUP"):
                url = "a[href='https://automationteststore.com/index.php?rt=product/category&path=36']";
                break;
            case("SKINCARE"):
                url = "a[href='https://automationteststore.com/index.php?rt=product/category&path=43']";
                break;
            case("FRAGRANCE"):
                url = "a[href='https://automationteststore.com/index.php?rt=product/category&path=49']";
                break;
            case("MEN"):
                url = "a[href='https://automationteststore.com/index.php?rt=product/category&path=58']";
                break;
            case("HAIR CARE"):
                url = "a[href='https://automationteststore.com/index.php?rt=product/category&path=52']";
                break;
            case("APPAREL & ACCESSORIES"):
                url = "a[href='https://automationteststore.com/index.php?rt=product/category&path=68']";
                break;
            case("BOOKS"):
                url = "a[href='https://automationteststore.com/index.php?rt=product/category&path=65']";
                break;
            default:
                url = "a[href='https://automationteststore.com/']";
                break;
        }
        driver.findElement(By.cssSelector(url)).click();
    }
}
