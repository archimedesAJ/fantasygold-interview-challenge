import Page_Objects.ShopPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductFilterTests {
    private WebDriver driver;

    //Setting up page
    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    //Test to open site
    @Test
    public void OpenSite() throws InterruptedException {
        driver.get("https://moreplexghana.com/");
        Assert.assertEquals(driver.getTitle(), "Moreplex");
        Thread.sleep(2000);
    }

    //Test to verify product filter by hair (TC_01)
    @Test (priority = 1)
    public void productFilterByHair() throws InterruptedException {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.navigateToShopPage();
        shopPage.productfilterByHair();
        Thread.sleep(2000);
        shopPage.pressFilterButton();
    }

    //Test to verify product filter by skin care (TC_02)
    @Test (priority = 2)
    public void productFilterBySkinCar() throws InterruptedException{
        ShopPage shopPage = new ShopPage(driver);
        shopPage.navigateToShopPage();
        shopPage.productfilterBySkinCare();
        Thread.sleep(2000);
        shopPage.pressFilterButton();
    }

    //Test to verify product filter by home car (TC_03)
    @Test (priority = 3)
    public void productFilterByHomeCare() throws InterruptedException {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.navigateToShopPage();
        shopPage.productfilterByHomeCare();
        Thread.sleep(2000);
        shopPage.pressFilterButton();
    }

    //Test to verify product filter by instock (TC_04)
    @Test (priority = 4)
    public void productByInstock() throws InterruptedException {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.navigateToShopPage();
        shopPage.productfilterByInstock();
        Thread.sleep(2000);
        shopPage.pressFilterButton();
        Thread.sleep(10000);
    }



}
