package pages.items;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class Specials {
    private WebDriver driver;
    private int numberOfProductsAddedInCart = 0;

    public Specials(WebDriver driver, String url) {
        this.driver = driver;
        driver.get(url);

        PageFactory.initElements(driver, this);
    }

    public void clickShoppingCart() {
        driver.findElement(By.className("nobackground")).click();
    }


    public void chooseItemForClick(String item) {
//        WebElement itemList = driver.findElement(By.className("grid"));
//        List<WebElement> links = itemList.findElements(By.className("productcart"));

        switch(item) {
            case "ABSOLUE EYE PRECIOUS CELLS":
                driver.findElement(By.cssSelector("a[data-id='65']")).click();
                numberOfProductsAddedInCart++;
                break;
            case "ACQUA DI GIO POUR HOMME":
                driver.findElement(By.cssSelector("a[data-id='80']")).click();
                numberOfProductsAddedInCart++;
                break;
            case "CK ONE SUMMER 3.4 OZ":
                driver.findElement(By.cssSelector("a[data-id='88']")).click();
                numberOfProductsAddedInCart++;
                break;
            case "LE ROUGE ABSOLU Reshaping & Replenishing LipColour SPF 15":
                driver.findElement(By.cssSelector("a[data-id='55']")).click();
                numberOfProductsAddedInCart++;
                break;
            case "CREME PRECIEUSE NUIT 50ML":
                driver.findElement(By.cssSelector("a[data-id='93']")).click();
                numberOfProductsAddedInCart++;
                break;
            case "BRUNETTE EXPRESSIONS CONDITIONER":
                driver.findElement(By.cssSelector("a[data-id='72']")).click();
                numberOfProductsAddedInCart++;
                break;
            default:
                break;
        }
    }

    public int getNumberOfProductsAddedInCart() {
        return numberOfProductsAddedInCart;
    }
}
