package pages;

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

    public void clickSearch() {
        driver.findElement(By.name("query")).click();
    }

    public void clickAdvancedSearch() {
        driver.findElement(By.id("search-options")).click();
        driver.findElement(By.id("advanced-search-link")).click();
    }

    public void clickLogin() {
        driver.findElement(By.className("register-link")).click();
    }
}
