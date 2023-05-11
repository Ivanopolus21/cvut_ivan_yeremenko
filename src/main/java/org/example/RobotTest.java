package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RobotTest{
    private WebDriver driver = new ChromeDriver();
    public RobotTest() {
        this.driver.get("https://moodle.fel.cvut.cz/my/");

        PageFactory.initElements(driver, this);

        //Moodle page
        driver.findElement(By.partialLinkText("Log in")).click();
        //SSO Login page
        driver.findElement(By.className("btn")).click();
        //Login page fpr moodle
        driver.findElement(By.id("username")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.className("btn")).click();
        //Course page moodle
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.partialLinkText("Software Testing")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //Software Testing Page
//        driver.findElement(By.cssSelector("a.d-flex[href='/course/view.php?id=7747#section-4']")).click();
//        driver.findElement(By.cssSelector("a.sectiontoggle[href='#collapse-4']")).click();
        driver.findElement(By.partialLinkText("Cvičení")).click();
        driver.findElement(By.cssSelector("a.sectiontoggle[href='#collapse-4']")).click();

        driver.findElement(By.cssSelector("a.aalink[href='https://moodle.fel.cvut.cz/mod/quiz/view.php?id=265999']")).click();
        //Test info page
        driver.findElement(By.className("btn-primary")).click();
        //Attempt page
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
//        driver.findElement(By.className("btn-primary")).click();
        driver.findElement(By.name("submitbutton")).click();
        //Quiz
        //First question
        driver.findElement(By.cssSelector("textarea")).sendKeys("Ivan Yeremenko 106 paralelka 11 cvičení");
        //Second question
        driver.findElement(By.cssSelector("input[type = 'text']")).sendKeys("86400");
        //Third question
        Select dropdown = new Select(driver.findElement(By.cssSelector("select")));
        dropdown.selectByVisibleText("Oberon");
        //Fourth question
        Select dropdown2 = new Select(driver.findElement(By.cssSelector("select[id$='4_p1']")));
        dropdown2.selectByVisibleText("Rumunsko");
        //Finish attempt
        driver.findElement(By.name("next")).click();
        //Go back to attempts
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.className("mod_quiz-next-nav")).click();
        //open profile dropdown
        driver.findElement(By.id("action-menu-toggle-1")).click();
        //Log out
        driver.findElement(By.cssSelector("a.dropdown-item.menu-action[href='https://moodle.fel.cvut.cz/login/logout.php']")).click();
        //Log out confirmation
        driver.findElement(By.className("btn-primary")).click();
    }

}