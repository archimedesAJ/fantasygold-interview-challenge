import DataUtils.ReadExcelFile;
import Page_Objects.RegisterPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
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

    @Test (dataProvider = "testdata1", priority = 1)
    public void testSuccessfulRegister(String username, String email, String password) throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.navigateToRegisterPage();
        registerPage.enterUsername(username);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        Thread.sleep(2000);
        registerPage.clickRegisterButton();
        Thread.sleep(2000);
        String message = driver.findElement(By.xpath("//*[@id=\"post-13\"]/div/div/div/div/div/div/div/div/div/div/p[1]")).getText();
        Assert.assertEquals(message, "Hello " + username + " (not " + username + "? Log out)");
        driver.findElement(By.xpath("//*[@id=\"post-13\"]/div/div/div/div/div/div/div/div/div/div/p[1]/a")).click();
        Thread.sleep(2000);
    }

    //Negative TCs
    @Test(priority = 2)
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

    //Negative TCs
    @Test (priority = 3)
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

    @DataProvider(name="testdata1")
    public Object[][] TestDataFeed() {
        ReadExcelFile config = new ReadExcelFile("TestData/Credentials.xls");

        int rows = config.getRowCount(1);
        System.out.println(rows);

        Object[][] credentials = new Object[rows][3];

        for (int i=0; i<rows; i++){
            credentials[i][0] = config.getData(1, i, 0);
            credentials[i][1] = config.getData(1, i, 1);
            credentials[i][2] = config.getData(1, i, 2);
        }
        return credentials;
    }

}

