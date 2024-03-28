import Page_Objects.RegisterPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.chrono.ThaiBuddhistEra;

public class RegisterTests {
    private WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void OpenSite() throws InterruptedException {
        driver.get("https://moreplexghana.com/");
        Assert.assertEquals(driver.getTitle(), "Moreplex");
        Thread.sleep(2000);
    }

    @Test (priority = 1)
    public void testSuccessfulRegister() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.navigateToRegisterPage();
        registerPage.enterUsername("kusi berko");
        registerPage.enterEmail("kusiberko@gmail.com");
        registerPage.enterPassword("34@magnaASU12");
        Thread.sleep(2000);
        registerPage.clickRegisterButton();
        Thread.sleep(2000);
        String message = driver.findElement(By.xpath("//*[@id=\"post-13\"]/div/div/div/div/div/div/div/div/div/div/p[1]")).getText();
        Assert.assertEquals(message, "Hello kusi berko (not kusi berko? Log out)");
    }

    @Test(enabled = false)
    public void registerwithAlreadyExistingUsername() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.navigateToRegisterPage();
        registerPage.enterUsername("mickey kay");
        registerPage.enterEmail("mickeykay@gmail.com");
        registerPage.enterPassword("48suma@ASU123");
        Thread.sleep(2000);
        registerPage.clickRegisterButton();
        Thread.sleep(5000);
        String error_message = driver.findElement(By.xpath("//*[@id=\"post-13\"]/div/div/div/div/div/div/div/div/div/div[1]/ul/li")).getText();
        Assert.assertEquals(error_message, "Error: An account is already registered with that username. Please choose another.");
    }

    @Test (enabled = false)
    public void registerwithAlreadyExistingEmail() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.navigateToRegisterPage();
        registerPage.enterUsername("mickey ekay");
        registerPage.enterEmail("mickey.kay@gmail.com");
        registerPage.enterPassword("48suma@ASU123");
        Thread.sleep(2000);
        registerPage.clickRegisterButton();
        Thread.sleep(5000);
        String error_message = driver.findElement(By.xpath("//*[@id=\"post-13\"]/div/div/div/div/div/div/div/div/div/div[1]/ul/li")).getText();
        Assert.assertEquals(error_message, "Error: An account is already registered with your email address. Please log in.");
        //The error message says please log in at the end of the message. But that's not shown to the user at the frontend
    }

}


/*
    Username       Email                           Password
    mickey kay     mickey.kay@gmail.com            48suma@ASU123
    kusi berko     kusiberko@gmail.com             34@magnaASU12

 */