import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PageConfig {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(1600,900));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @AfterEach
    public void closeWindow() {
        driver.quit();
    }

    @Test
    public void startTesting() {
        //Home
        Home home = new Home(driver, "https://link.springer.com/");
        home.clickAdvancedSearch();

        //Advanced Search
        AdvancedSearch advancedSearch = new AdvancedSearch(driver, driver.getCurrentUrl());
        advancedSearch.acceptCookies();
        advancedSearch.setSearchByAllWords("Page Object Model");
        advancedSearch.setSearchByAtLeastOneOfTheWords("Sellenium Testing");
        advancedSearch.setSearchYearOptionToInYear();
        advancedSearch.setSearchByYear(String.valueOf(LocalDate.now().getYear()));
        advancedSearch.submitAdvancedSearch();

        //Search
        Search search = new Search(driver, driver.getCurrentUrl());
        search.setSearchedContentType("Article");

        //Links
        ArrayList<String> articleLinks = search.getResultsLinks(4);

        // Data of articles
        StringBuilder dataForCSV = new StringBuilder();

        for (String link : articleLinks) {
            Article page = new Article(driver, link);
            dataForCSV.append(page.getName()).append(", ");
            dataForCSV.append(page.getDate()).append(", ");
            dataForCSV.append(page.getDOI()).append("\n");
        }

        // Writing in CSV
        try {
            FileWriter myWriter = new FileWriter("src/test/resources/data.csv");
            myWriter.write(dataForCSV.toString());
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void parameterizedTest(String name, String date, String DOI) {
        // Home
        Home home = new Home(driver, "https://link.springer.com/");
        home.clickLogin();

        // Login
        Login login = new Login(driver, driver.getCurrentUrl());
        login.setLoginEmail("i.p.yeremenko@gmail.com");
        login.setLoginPassword("Ivanopolus123");
        login.submitLogin();

        // From home to advanced search
        home.clickAdvancedSearch();

        // Search
        AdvancedSearch advancedSearch = new AdvancedSearch(driver, driver.getCurrentUrl());
        advancedSearch.setSearchByTitle(name);
        advancedSearch.submitAdvancedSearch();

        // Search
        Search search = new Search(driver, driver.getCurrentUrl());

        // Article
        Article article = new Article(driver, search.getResultsLinks(1).get(0));

        // ASSERT
        assertEquals(name, article.getName());
        assertEquals(date, article.getDate());
        assertEquals(DOI, article.getDOI());
    }

}
