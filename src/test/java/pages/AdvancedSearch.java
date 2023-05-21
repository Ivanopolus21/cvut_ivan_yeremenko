package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdvancedSearch {
    private WebDriver driver;
    public AdvancedSearch(WebDriver driver, String url) {
        this.driver = driver;
        driver.get(url);

        PageFactory.initElements(driver, this);
    }

    public void setSearchByAllWords(String key) {
        driver.findElement(By.id("all-words")).sendKeys(key);
    }

    public void setSearchByAtLeastOneOfTheWords(String key) {
        driver.findElement(By.id("least-words")).sendKeys(key);
    }

    public void setSearchByTitle(String key) {
        driver.findElement(By.id("title")).sendKeys(key);
    }
    public void setSearchYearOptionToInYear() {
        Select select = new Select(driver.findElement(By.id("date-facet-mode")));
        select.selectByValue("in");
    }

    public void setSearchByYear(String year) {
        driver.findElement(By.id("facet-start-year")).sendKeys(year);
    }

    public void submitAdvancedSearch() {
        driver.findElement(By.id("submit-advanced-search")).click();
    }

    public void acceptCookies() {
        driver.findElement(By.className("cc-button")).click();
    }

}
