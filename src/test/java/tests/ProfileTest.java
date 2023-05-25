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

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfileTest {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(1600,900));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        //Login to the profile
        Login login = new Login(driver, "https://automationteststore.com/index.php?rt=account/login");
        login.setLoginName("Wellplay");
        login.setLoginPassword("Qwerty12345");
        login.submitLogin();
    }

    @Test
    public void editProfileTest() {
        String expectedUrl = "https://automationteststore.com/index.php?rt=account/account";

        //Profile
        Profile profile = new Profile(driver, driver.getCurrentUrl());
        profile.clickOnEditAccountDetails();
        profile.setNewFirstName("Who The Hell Is");
        profile.setNewLastName("Edgar");
        profile.setNewEmail("testik11@gmail.com");
        profile.setNewTelephone("+4277777777");
        profile.setNewFax("123456789");
        profile.clickContinue();

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    public void logoutTest() {
        String expectedUrl = "https://automationteststore.com/";
//
        Profile profile = new Profile(driver, driver.getCurrentUrl());
        profile.clickLogOff();
        profile.clickContinueAfterLogout();

        assertEquals(expectedUrl, driver.getCurrentUrl());

    }

    @Test
    public void clickOnWishlistTest() {
        String expectedUrl = "https://automationteststore.com/index.php?rt=account/wishlist";

        Profile profile = new Profile(driver, driver.getCurrentUrl());
        profile.clickOnWishlist();

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }
}
