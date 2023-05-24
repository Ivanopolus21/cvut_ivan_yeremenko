package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.states.Checkout;
import pages.states.Login;
import pages.states.Profile;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTest {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(1600,900));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @Test
    public void checkoutTest() {
        String expectedUrl = "https://automationteststore.com/";

        Login login = new Login(driver, "https://automationteststore.com/index.php?rt=account/login");
        login.setLoginName("Wellplay");
        login.setLoginPassword("Qwerty12345");
        login.submitLogin();

        Profile profile = new Profile(driver, driver.getCurrentUrl());
        profile.clickCheckout();

        Checkout checkout = new Checkout(driver, driver.getCurrentUrl());
        checkout.confirmOrder();
        checkout.clickContinue();

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    public void checkoutBackTest() {
        String expectedUrl = "https://automationteststore.com/index.php?rt=checkout/payment&mode=edit";

        Login login = new Login(driver, "https://automationteststore.com/index.php?rt=account/login");
        login.setLoginName("Wellplay");
        login.setLoginPassword("Qwerty12345");
        login.submitLogin();

        Profile profile = new Profile(driver, driver.getCurrentUrl());
        profile.clickCheckout();

        Checkout checkout = new Checkout(driver, driver.getCurrentUrl());
        checkout.clickBack();

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }
}
