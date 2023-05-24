package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.states.Login;
import pages.states.Profile;
import pages.states.ShoppingCart;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartTest {
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
    public void getResultLinksTest() {
        String expectedUrl = "https://automationteststore.com/";

        Login login = new Login(driver, "https://automationteststore.com/index.php?rt=account/login");
        login.setLoginName("Wellplay");
        login.setLoginPassword("Qwerty12345");
        login.submitLogin();

        Profile profile = new Profile(driver, driver.getCurrentUrl());
        profile.clickShoppingCart();

        ShoppingCart shoppingCart = new ShoppingCart(driver, driver.getCurrentUrl());

        assertEquals(4, shoppingCart.getResultsLinks(4).size());
    }
}
