package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.states.Home;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomeTest {
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
    public void clickLoginTest() {
        String expectedUrl = "https://automationteststore.com/index.php?rt=account/login";

        Home home = new Home(driver, "https://automationteststore.com/");
        home.clickLogin();

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    public void clickRegisterTest() {
        String expectedUrl = "https://automationteststore.com/index.php?rt=account/create";

        Home home = new Home(driver, "https://automationteststore.com/");
        home.clickRegister();

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    public void clickSearchTest() {
        String expectedUrl = "https://automationteststore.com/index.php?rt=product/search&category_id=0";

        Home home = new Home(driver, "https://automationteststore.com/");
        home.clickSearch();

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    public void clickProfileTest() {
        String expectedUrl = "https://automationteststore.com/index.php?rt=account/login";

        Home home = new Home(driver, "https://automationteststore.com/");
        home.clickProfile();

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    public void clickCategoryTest() {
        String expectedUrl = "https://automationteststore.com/index.php?rt=product/category&path=36";

        Home home = new Home(driver, "https://automationteststore.com/");
        home.clickCategory("MAKEUP");

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }
}
