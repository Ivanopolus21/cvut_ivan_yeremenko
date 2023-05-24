package pages.states;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Search {
    @FindBy(id="keyword")
    private WebElement keyword;

    @FindBy(id="category_id")
    private WebElement category_id;

    @FindBy(id="description")
    private WebElement description;

    @FindBy(id="model")
    private WebElement model;

    @FindBy(css = "button[title='Search']")
    private WebElement button_search;


    private WebDriver driver;

    public Search(WebDriver driver, String url) {
        this.driver = driver;
        driver.get(url);

        PageFactory.initElements(driver, this);
    }

    public void setKeyword(String keyword) {
        this.keyword.sendKeys(keyword);
    }

    public void setCategory(String category) {
        Select selectCategory = new Select(this.category_id);
        selectCategory.selectByVisibleText(category);
    }

    public void submitDescription() {
        description.click();
    }

    public void submitModel() {
        model.click();
    }

    public void submitSearch() {
        button_search.click();
    }

    public void clickOnTheArmaniToiletWaterCart() {
        driver.findElement(By.cssSelector("a[data-id='81']")).click();
    }

    public void clickShoppingCart() {
        driver.findElement(By.className("nobackground")).click();
    }
}
