package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

import pages.states.Register;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterTest {
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
    public void registerTest() {
        String first_name = "Viktoriia";
        String last_name = "Bunko";
        String email = "bunkvika2r@cvut.cz";
        String telephone = "+420788850704";
        String fax = "01619998888";

        //Your Address
        String company = "CTU";
        String address_1 = "Vaníčkova 100/6, 169 00 Praha 6-Strahov";
        String address_2 = "Vaníčkova 2464, 169 00 Praha 6-Strahov";
        String city = "Prague";
        String region = "Praha";
        String zip = "BS2";
        String country = "Czech Republic";

        //Login Details
        String login_name = "bunkvi2";
        String password = "yeremiva123!";
        String password_confirm = "yeremiva123!";

        Register register = new Register(driver, "https://automationteststore.com/index.php?rt=account/create");
        register.setFirstName(first_name);
        register.setLastName(last_name);
        register.setEmail(email);
        register.setTelephone(telephone);
        register.setFax(fax);
        register.setCompany(company);
        register.setAddress1(address_1);
        register.setAddress2(address_2);
        register.setCity(city);
        register.setZIPCode(zip);
        register.setCountry(country);
        register.setRegion(region);
        register.setLoginName(login_name);
        register.setPassword(password);
        register.setPasswordConfirm(password_confirm);

        register.setSubscription();
        register.agreeToPrivacyPolicy();
        register.submitRegistration();

        register.clickContinue();
        String expectedUrl = "https://automationteststore.com/index.php?rt=account/account";

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }
}
