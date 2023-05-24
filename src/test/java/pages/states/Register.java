package pages.states;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Register {
    private WebDriver driver;

//    Your Personal Details
    @FindBy(id = "AccountFrm_firstname")
    private WebElement first_name;

    @FindBy(id = "AccountFrm_lastname")
    private WebElement last_name;

    @FindBy(id = "AccountFrm_email")
    private WebElement email;

    @FindBy(id = "AccountFrm_telephone")
    private WebElement telephone;

    @FindBy(id = "AccountFrm_fax")
    private WebElement fax;

    //Your Address
    @FindBy(id = "AccountFrm_company")
    private WebElement company;

    @FindBy(id = "AccountFrm_address_1")
    private WebElement address_1;

    @FindBy(id = "AccountFrm_address_2")
    private WebElement address_2;

    @FindBy(id = "AccountFrm_city")
    private WebElement city;

    @FindBy(id = "AccountFrm_zone_id")
    private WebElement region;

    @FindBy(id = "AccountFrm_postcode")
    private WebElement zip;

    @FindBy(id = "AccountFrm_country_id")
    private WebElement country;

    //Login Details

    @FindBy(id = "AccountFrm_loginname")
    private WebElement login_name;

    @FindBy(id = "AccountFrm_password")
    private WebElement password;

    @FindBy(id = "AccountFrm_confirm")
    private WebElement password_confirm;

    //Newsletter
    @FindBy(id = "AccountFrm_newsletter1")
    private WebElement newsletter_yes;

    @FindBy(id = "AccountFrm_agree")
    private WebElement agree;

    @FindBy(css = "button.btn.btn-orange")
    private WebElement submit_button;


    public Register(WebDriver driver, String url) {
        this.driver = driver;
        driver.get(url);

        PageFactory.initElements(driver, this);
    }

    //Your Personal Details
    public void setFirstName(String first_name) {
        this.first_name.sendKeys(first_name);
    }

    public void setLastName(String last_name) {
        this.last_name.sendKeys(last_name);
    }

    public void setEmail(String email) {
        this.email.sendKeys(email);
    }

    public void setTelephone(String telephone) {
        this.telephone.sendKeys(telephone);
    }

    public void setFax(String fax) {
        this.fax.sendKeys(fax);
    }

    //Your Address
    public void setCompany(String companyName) {
        this.company.sendKeys(companyName);
    }

    public void setAddress1(String address1) {
        this.address_1.sendKeys(address1);
    }

    public void setAddress2(String address2) {
        this.address_2.sendKeys(address2);
    }

    public void setCity(String city) {
        this.city.sendKeys(city);
    }

    public void setRegion(String region) {
        Select selectRegion = new Select(this.region);
        selectRegion.selectByVisibleText(region);
    }

    public void setZIPCode(String zip) {
        this.zip.sendKeys(zip);
    }

    public void setCountry(String country) {
        Select selectCountry = new Select(this.country);
        selectCountry.selectByVisibleText(country);
    }

    //Login Details
    public void setLoginName(String loginName) {
        this.login_name.sendKeys(loginName);
    }

    public void setPassword(String password) {
        this.password.sendKeys(password);
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.password_confirm.sendKeys(passwordConfirm);
    }

    //Newsletter
    public void setSubscription() {
        this.newsletter_yes.click();
    }

    public void agreeToPrivacyPolicy() {
        this.agree.click();
    }

    public void submitRegistration() {
        this.submit_button.click();
    }

    public void clickContinue() {
        driver.findElement(By.className("mr10")).click();
    }
}
