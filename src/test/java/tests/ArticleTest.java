package tests;

import com.beust.ah.A;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.Article;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArticleTest {
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
    public void getNameTest() {
        String expectedName = "Phishing Website Detection with and Without Proper Feature Selection Techniques: Machine Learning Approach";

        Article article = new Article(driver, "https://link.springer.com/chapter/10.1007/978-3-031-24475-9_61");
        article.acceptCookies();

        assertEquals(expectedName, article.getName());
    }

    @Test
    public void getDateTest() {
        String expectedDate = "29 January 2023";

        Article article = new Article(driver, "https://link.springer.com/chapter/10.1007/978-3-031-24475-9_61");
        article.acceptCookies();

        assertEquals(expectedDate, article.getDate());
    }

    @Test
    public void getDOITest() {
        String expectedDOI = "https://doi.org/10.1007/978-3-031-24475-9_61";

        Article article = new Article(driver, "https://link.springer.com/chapter/10.1007/978-3-031-24475-9_61");
        article.acceptCookies();

        assertEquals(expectedDOI, article.getDOI());
    }
}
