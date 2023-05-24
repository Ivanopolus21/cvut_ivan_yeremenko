package pages.states;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private WebDriver driver;

    public ShoppingCart(WebDriver driver, String url) {
        this.driver = driver;
        driver.get(url);

        PageFactory.initElements(driver, this);
    }

    public void applyCoupon(String coupon) {
        driver.findElement(By.id("coupon_coupon")).sendKeys(coupon);
        driver.findElement(By.id("apply_coupon_btn")).click();
    }

    public void setShippingCountry(String country) {
        Select select = new Select(driver.findElement(By.id("estimate_country")));
        select.selectByVisibleText(country);
    }

    public void setShippingState(String state) {
        Select select = new Select(driver.findElement(By.id("estimate_country_zones")));
        select.selectByVisibleText(state);
    }

    public void setZipCode(String zip) {
        driver.findElement(By.id("estimate_postcode")).sendKeys(zip);
    }

    public void estimateShipment() {
        driver.findElement(By.cssSelector("button[title='Estimate']")).click();
    }

    public void continueShopping() {
        driver.findElement(By.className("mb10")).click();
    }

    public void checkout() {
        driver.findElement(By.id("cart_checkout2")).click();
    }

    public ArrayList<String> getResultsLinks(int quantity) {
        WebElement resultList = driver.findElement(By.className("product-list"));
        List<WebElement> elements = resultList.findElements(By.cssSelector("a[href^='https://automationteststore.com/index.php?rt=product/product&product_id']"));
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
