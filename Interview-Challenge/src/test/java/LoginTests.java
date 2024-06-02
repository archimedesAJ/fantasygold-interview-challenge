import DataUtils.ReadExcelFile;
import Page_Objects.LoginPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests {

    ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter("target/Report.html");

    private WebDriver driver;

    //Setting up the page
    @BeforeClass
    public void setUp(){
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("My Report");
        extent.attachReporter(spark);

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void teardown(){
        extent.flush();
        driver.quit();

    }

    //Test to open the site
    @Test
    public void OpenSite() throws InterruptedException {
        ExtentTest test = extent.createTest("Confirm Page Title").assignAuthor("Abraham").assignCategory("Functional Test").assignDevice("Windows");
        driver.get("https://moreplexghana.com/");
        String page_title = driver.getTitle();
        if (page_title.equals("Moreplex")){
            test.pass("Test passed. Page titles match!");
        }
        else {
            test.fail("Test failed. Page titles mismatch!");
        }
//        boolean dec = Assert.assertEquals(driver.getTitle(), "Moreplex");
        Thread.sleep(2000);
    }

    //Test to verify user login with email and password (TC_01)
    @Test (dataProvider = "testdata", priority = 1)
    public void loginSuccesfulwithEmail(String username, String email, String password) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.enterUsernameOrEmail(email);
        loginPage.enterPassword(password);
        Thread.sleep(5000);
        loginPage.clickLoginButton();
        Thread.sleep(2000);
        String message = driver.findElement(By.xpath("//*[@id=\"post-13\"]/div/div/div/div/div/div/div/div/div/div/p[1]")).getText();
        Assert.assertEquals(message, "Hello" +" "+ username + " "+ "(not " + username + "? Log out)");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"post-13\"]/div/div/div/div/div/div/div/div/div/div/p[1]/a")).click();
        Thread.sleep(2000);
    }


    // Test to verify user login with username and password (TC_02)
    @Test(dataProvider = "testdata",priority = 2)
    public void loginSuccesfulwithUsername(String username, String email, String password) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.enterUsernameOrEmail(username);
        loginPage.enterPassword(password);
        Thread.sleep(5000);
        loginPage.clickLoginButton();
        Thread.sleep(2000);
        String message = driver.findElement(By.xpath("//*[@id=\"post-13\"]/div/div/div/div/div/div/div/div/div/div/p[1]")).getText();
        Assert.assertEquals(message, "Hello" +" "+ username + " "+ "(not " + username + "? Log out)");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"post-13\"]/div/div/div/div/div/div/div/div/div/div/p[1]/a")).click();
        Thread.sleep(2000);
    }

    /* Negative Test Cases*/

    //Test to verify user login with wrong email (TC_03)
    @Test(priority = 3)
    public void loginwithWrongEmail() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.enterUsernameOrEmail("mickey1kay@gmail.com");
        loginPage.enterPassword("48suma@ASU123");
        Thread.sleep(5000);
        loginPage.clickLoginButton();
        Thread.sleep(2000);
        String error_msg = driver.findElement(By.xpath("//*[@id=\"post-13\"]/div/div/div/div/div/div/div/div/div/div[1]/ul/li")).getText();
        Assert.assertEquals(error_msg, "Unknown email address. Check again or try your username.");
        Thread.sleep(3000);
    }

    //Test to verify user login with wrong username (TC_04)
    @Test(priority = 4)
    public void loginwithWrongUsername() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.enterUsernameOrEmail("mcikey okay");
        loginPage.enterPassword("48suma@ASU123");
        Thread.sleep(5000);
        loginPage.clickLoginButton();
        Thread.sleep(2000);
        String error_msg = driver.findElement(By.xpath("//*[@id=\"post-13\"]/div/div/div/div/div/div/div/div/div/div[1]/ul/li")).getText();
        Assert.assertEquals(error_msg, "Error: The username mcikey okay is not registered on this site. If you are unsure of your username, try your email address instead.");
        Thread.sleep(3000);
    }

    //Test to verify user login with correct email but wrong password (TC_05)
    @Test(dataProvider ="testdata", priority = 5)
    public void loginwithWrongPassword(String username, String email, String password) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.enterUsernameOrEmail(email);
        loginPage.enterPassword(password.substring(0, 3));
        Thread.sleep(5000);
        loginPage.clickLoginButton();
        Thread.sleep(2000);
        String error_msg = driver.findElement(By.xpath("//*[@id=\"post-13\"]/div/div/div/div/div/div/div/div/div/div[1]/ul/li")).getText();
        Assert.assertEquals(error_msg, "Error: The password you entered for the email address " + email + " is incorrect. Lost your password?");
        Thread.sleep(3000);
    }

    // //Method to retrieve test data to be used by the tests
    @DataProvider (name="testdata")
    public Object[][] TestDataFeed() {
        ReadExcelFile config = new ReadExcelFile("TestData/Credentials.xls");

        int rows = config.getRowCount(0);
        System.out.println(rows);

        Object[][] credentials = new Object[rows][3];

        for (int i=0; i<rows; i++){
            credentials[i][0] = config.getData(0, i, 0);
            credentials[i][1] = config.getData(0, i, 1);
            credentials[i][2] = config.getData(0, i, 2);

        }
        return credentials;
    }
}
