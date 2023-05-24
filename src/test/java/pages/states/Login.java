package pages.states;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
    private WebDriver driver;

    @FindBy(id = "loginFrm_loginname")
    private WebElement login_name;

    @FindBy(id = "loginFrm_password")
    private WebElement password;

    @FindBy(css = "button[title='Login']")
    private WebElement login_button;

    public Login(WebDriver driver, String url) {
        this.driver = driver;
        driver.get(url);

        PageFactory.initElements(driver, this);
    }

    public void setLoginName(String loginName) {
        this.login_name.sendKeys(loginName);
    }

    public void setLoginPassword(String password) {
        this.password.sendKeys(password);
    }

    public void submitLogin() {
        login_button.click();
    }
}
