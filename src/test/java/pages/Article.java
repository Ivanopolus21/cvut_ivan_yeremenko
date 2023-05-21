package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Article {
    private WebDriver driver;

    public Article(WebDriver driver, String url) {
        this.driver = driver;
        driver.get(url);

        PageFactory.initElements(driver, this);
    }

    public String getName() {
        return driver.findElement(By.className("c-article-title")).getText();
    }

    public String getDate() {
        return driver.findElement(By.cssSelector("time")).getText();
    }

    public String getDOI() {
        return driver.findElement(By.cssSelector(".c-bibliographic-information__list-item--doi .c-bibliographic-information__value")).getText();
    }

    public void acceptCookies() {
        driver.findElement(By.className("cc-button")).click();
    }
}