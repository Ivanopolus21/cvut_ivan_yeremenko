package tests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.states.Login;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
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
    public void loginTest() {
        Login loginPage = new Login(driver, "https://automationteststore.com/index.php?rt=account/login");
        loginPage.setLoginName("bunkvik22");
        loginPage.setLoginPassword("yeremiva123!");
        loginPage.submitLogin();

        String expectedUrl = "https://automationteststore.com/index.php?rt=account/account";

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }
}

