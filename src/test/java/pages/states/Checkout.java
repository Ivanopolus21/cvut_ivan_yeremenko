package pages.states;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Checkout {
    @FindBy(css = "button[title='Confirm Order']")
    private WebElement confirm_order;

    @FindBy(id = "back")
    private WebElement back_button;

    private WebDriver driver;

    public Checkout(WebDriver driver, String url) {
        this.driver = driver;
        driver.get(url);

        PageFactory.initElements(driver, this);
    }

    public void confirmOrder() {
        confirm_order.click();
    }

    public void clickBack() {
        back_button.click();
    }

    public void clickContinue() {
        driver.findElement(By.cssSelector("a[title='Continue']")).click();
    }

    public void setBackComment(String comment) {
        driver.findElement(By.id("payment_comment")).sendKeys(comment);
    }

    public void clickReturnPolicyAgree() {
        driver.findElement(By.id("payment_agree")).click();
    }

    public void clickBackContinue() {
        driver.findElement(By.className("ml10")).click();
    }
}
