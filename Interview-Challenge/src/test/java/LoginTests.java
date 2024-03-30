import DataUtils.ReadExcelFile;
import Page_Objects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests {
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

    //Negative Test Cases
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
