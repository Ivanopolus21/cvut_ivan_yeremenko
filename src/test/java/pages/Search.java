package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class Search {
    private WebDriver driver;

    public Search(WebDriver driver, String url) {
        this.driver = driver;
        driver.get(url);

        PageFactory.initElements(driver, this);
    }

    public void setSearchedContentType(String type) {
        WebElement contentType = driver.findElement(By.id("content-type-facet"));
        List<WebElement> links = contentType.findElements(By.className("facet-link"));

        for (WebElement element : links) {
            if (element.findElement(By.className("facet-title")).getText().equals(type)) {
                element.click();
                break;
            }
        }
    }

    public ArrayList<String> getResultsLinks(int quantity) {
        WebElement resultList = driver.findElement(By.id("results-list"));
        List<WebElement> elements = resultList.findElements(By.className("title"));
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

    public void acceptCookies() {
        driver.findElement(By.className("cc-button")).click();
    }
}
