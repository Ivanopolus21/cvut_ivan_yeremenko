package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.items.Category;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {
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
    public void sortingTest() {
        String expectedUrl = "https://automationteststore.com/index.php?rt=product/category&path=68&sort=rating-DESC&limit=20";

        Category category = new Category(driver, "https://automationteststore.com/index.php?rt=product/category&path=68");
        category.sortBy("Rating Highest");

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    public void getResultLinksTest() {
        Category category = new Category(driver, "https://automationteststore.com/index.php?rt=product/category&path=58");

        assertEquals(4, category.getResultsLinks(4).size());
    }
}
