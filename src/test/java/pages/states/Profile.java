package pages.states;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Profile {
    private WebDriver driver;
    public Profile(WebDriver driver, String url) {
        this.driver = driver;
        driver.get(url);

        PageFactory.initElements(driver, this);
    }

    public void clickOnEditAccountDetails() {
        driver.findElement(By.cssSelector("a[title][href='https://automationteststore.com/index.php?rt=account/edit']")).click();
    }

    public void clickOnWishlist() {
        driver.findElement(By.cssSelector("a[title][href='https://automationteststore.com/index.php?rt=account/wishlist']")).click();
    }

    public void setNewFirstName(String firstName) {
        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys(firstName);
    }

    public void setNewLastName(String lastName) {
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys(lastName);
    }

    public void setNewEmail(String email) {
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys(email);

    }

    public void setNewTelephone(String number) {
        driver.findElement(By.name("telephone")).clear();
        driver.findElement(By.name("telephone")).sendKeys(number);
    }

    public void setNewFax(String fax) {
        driver.findElement(By.name("fax")).clear();
        driver.findElement(By.name("fax")).sendKeys(fax);
    }

    public void clickContinue() {
        driver.findElement(By.cssSelector("button[title='Continue']")).click();
    }

    public void clickContinueAfterLogout() {
        driver.findElement(By.className("mr10")).click();
    }

    public void clickLogOff() {
        driver.findElement(By.cssSelector("a[title][href='https://automationteststore.com/index.php?rt=account/logout']")).click();
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

    //The same as in other classes
    public void clickCheckout() {
        driver.findElement(By.className("menu_checkout")).click();
    }

    //The same as in other classes
    public void clickSpecials() {
        driver.findElement(By.className("menu_specials")).click();
    }

    //The same as in other classes
    public void clickShoppingCart() {
        driver.findElement(By.className("nobackground")).click();
    }
}
