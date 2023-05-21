package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.Search;

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void setSearchedContentTypeTest() {
        String expectedUrl = "https://link.springer.com/search?date-facet-mode=between&showAll=true&facet-content-type=%22Book%22";

        Search search = new Search(driver, "https://link.springer.com/search?date-facet-mode=between&showAll=true");
        search.acceptCookies();
        search.setSearchedContentType("Book");

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    public void getResultsLinksTest() {
        Search search = new Search(driver, "https://link.springer.com/search/page/1?date-facet-mode=in&facet-start-year=2022&showAll=true&query=Page+AND+Object+AND+Model+AND+%28Sellenium+OR+Testing%29");
        search.acceptCookies();
        assertEquals(4, search.getResultsLinks(4).size());
    }
}
