package pages.items;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private WebDriver driver;

    public Category(WebDriver driver, String url) {
        this.driver = driver;
        driver.get(url);

        PageFactory.initElements(driver, this);
    }

    public void sortBy(String type) {
        Select select = new Select(driver.findElement(By.id("sort")));
        select.selectByVisibleText(type);
    }

    public ArrayList<String> getResultsLinks(int quantity) {
        WebElement resultList = driver.findElement(By.className("grid"));
        List<WebElement> elements = resultList.findElements(By.className("prdocutname"));
        ArrayList<String> links = new ArrayList<>();

        if (elements.size() < quantity) {
            for (WebElement element : elements) {
                links.add(element.getAttribute("href"));
            }

            return links;
        }

        for (WebElement element : elements.subList(0, quantity)) {
            links.add(element.getAttribute("href"));
        }

        return links;
    }

}
