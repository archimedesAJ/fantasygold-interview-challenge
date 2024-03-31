import Page_Objects.ShopPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.bouncycastle.pqc.crypto.newhope.NHSecretKeyProcessor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckoutTests {
    private WebDriver driver;

    //Setting up the page
    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    //Test to open the site
    @Test
    public void OpenSite() throws InterruptedException {
        driver.get("https://moreplexghana.com/");
        Assert.assertEquals(driver.getTitle(), "Moreplex");
        Thread.sleep(2000);
    }

    //Test to verify product search (TC_01)
    @Test (priority =  1)
    public void productSearch() throws InterruptedException {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.navigateToShopPage();
        shopPage.searchProduct("hair");
        Thread.sleep(3000);
    }

    //Test to verify adding product to cart (TC_02)
    @Test (priority = 2)
    public void addToCart() throws InterruptedException {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.addproductToBasket();
    }

    //Test to verify cart items (TC_03)
    @Test (priority = 3)
    public void viewCart(){
        ShopPage shopPage = new ShopPage(driver);
        shopPage.viewCart();
    }

    //Test to verify checkout (TC_04)
    @Test (priority = 4)
    public void checkout() throws InterruptedException {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.checkout("Abraham", "Abbey", "Fantasy", "Hildas Street, Ashongman",
                "Ashongman Estates", "Bridge", "00233", "0247234117",
                "fantasy.gold@gmail.com", "fantasy gold", "47fbsf@AUVB12");
        Thread.sleep(200);

    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
