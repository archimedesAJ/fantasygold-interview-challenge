import Page_Objects.ShopPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.bouncycastle.pqc.crypto.newhope.NHSecretKeyProcessor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckoutTests {
    private WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void OpenSite() throws InterruptedException {
        driver.get("https://moreplexghana.com/");
        Assert.assertEquals(driver.getTitle(), "Moreplex");
        Thread.sleep(2000);
    }

    @Test (priority = 7)
    public void viewCart(){
        ShopPage shopPage = new ShopPage(driver);
        shopPage.viewCart();
    }

    @Test (priority = 8)
    public void checkout(){
        ShopPage shopPage = new ShopPage(driver);
        shopPage.checkout("Abraham", "Abbey", "Fantasy", "Hildas Street, Ashongman", "Ashongman Estates", "Bridge", "00233", "0247234117");
    }



}
