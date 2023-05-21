package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.Login;

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void inputTest() {
        String email = "i.p.yeremenko@gmail.com";
        String password = "Ivanopolus123";
        String expectedUrl = "https://link.springer.com/";

        Login login = new Login(driver, "https://link.springer.com/signup-login?previousUrl=https%3A%2F%2Flink.springer.com%2F");
        login.acceptCookies();
        login.setLoginEmail(email);
        login.setLoginPassword(password);
        login.submitLogin();

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }
}
