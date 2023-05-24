package pages.states;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Home {
    private WebDriver driver;

    public Home(WebDriver driver, String url) {
        this.driver = driver;
        driver.get(url);

        PageFactory.initElements(driver, this);
    }

    public void clickLogin() {
        driver.findElement(By.id("customer_menu_top")).click();
    }

    public void clickRegister() {
        driver.findElement(By.id("customer_menu_top")).click();
        driver.findElement(By.cssSelector("button[title='Continue']")).click();
    }

    public void clickSearch() {
        driver.findElement(By.className("button-in-search")).click();
    }

    public void clickProfile() {
        driver.findElement(By.className("menu_account")).click();
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
