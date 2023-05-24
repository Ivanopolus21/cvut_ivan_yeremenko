package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.states.Search;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchTest {
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
    public void incorrectKeywordForSearch(){
        Search searchPage = new Search(driver, "https://automationteststore.com/index.php?rt=product/search&category_id=0");
        String keyword = "qwertyuasdf";

        searchPage.setKeyword(keyword);
        searchPage.submitDescription();
        searchPage.submitSearch();
        String expectedUrl = "https://automationteststore.com/index.php?rt=product/search&limit=&keyword=" + keyword + "&category_id=0&description=1";

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    public void correctKeywordForSearch(){
        Search searchPage = new Search(driver, "https://automationteststore.com/index.php?rt=product/search&category_id=0");
        String keyword = "lipstick";

        searchPage.setKeyword(keyword);
        searchPage.setCategory("   Makeup");
        searchPage.submitSearch();
        String expectedUrl = "https://automationteststore.com/index.php?rt=product/product&product_id=59";

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    public void correctKeywordDescriptionModel(){
        Search searchPage = new Search(driver, "https://automationteststore.com/index.php?rt=product/search&category_id=0");
        String keyword = "shampoo";

        searchPage.setKeyword(keyword);
        searchPage.setCategory("   Hair Care");
        searchPage.submitDescription();
        searchPage.submitModel();
        searchPage.submitSearch();
        String expectedUrl = "https://automationteststore.com/index.php?rt=product/search&limit=&keyword="+ keyword+"&category_id=0%2C52&description=1&model=1";

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }
}
