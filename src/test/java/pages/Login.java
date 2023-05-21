package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Login {
    private WebDriver driver;

    public Login(WebDriver driver, String url) {
        this.driver = driver;
        driver.get(url);

        PageFactory.initElements(driver, this);
    }

    public void setLoginEmail(String key) {
        driver.findElement(By.id("login-box-email")).sendKeys(key);
    }

    public void setLoginPassword(String key) {
        driver.findElement(By.id("login-box-pw")).sendKeys(key);
    }

    public void submitLogin() {
        driver.findElement(By.cssSelector("button[title='Log in']")).click();
    }

    public void acceptCookies() {
        driver.findElement(By.className("cc-button")).click();
    }

}
