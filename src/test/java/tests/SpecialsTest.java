package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.items.Specials;
import pages.states.Login;
import pages.states.Profile;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpecialsTest {
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
    public void clickShoppingCartTest() {
        String expectedUrl = "https://automationteststore.com/index.php?rt=checkout/cart";

        Specials specials = new Specials(driver, "https://automationteststore.com/index.php?rt=product/special");
        specials.clickShoppingCart();

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    public void chooseItemForClickTest() {
        String expectedUrl = "https://automationteststore.com/index.php?rt=product/special";
        Login login = new Login(driver, "https://automationteststore.com/index.php?rt=account/login");
        login.setLoginName("Wellplay");
        login.setLoginPassword("Qwerty12345");
        login.submitLogin();

        Profile profile = new Profile(driver, driver.getCurrentUrl());
        profile.clickSpecials();

        Specials specials = new Specials(driver, driver.getCurrentUrl());
        specials.chooseItemForClick("ABSOLUE EYE PRECIOUS CELLS");
        specials.chooseItemForClick("CK ONE SUMMER 3.4 OZ");

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

}
