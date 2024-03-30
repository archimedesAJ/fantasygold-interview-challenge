import Page_Objects.ShopPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.plaf.TableHeaderUI;

public class ProductFilterSearchTests {
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

    @Test (priority = 1)
    public void productFilterByHair() throws InterruptedException {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.navigateToShopPage();
        shopPage.productfilterByHair();
        Thread.sleep(2000);
        shopPage.pressFilterButton();
    }

    @Test (priority = 2)
    public void productFilterBySkinCar() throws InterruptedException{
        ShopPage shopPage = new ShopPage(driver);
        shopPage.navigateToShopPage();
        shopPage.productfilterBySkinCare();
        Thread.sleep(2000);
        shopPage.pressFilterButton();
    }

    @Test (priority = 3)
    public void productFilterByHomeCare() throws InterruptedException {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.navigateToShopPage();
        shopPage.productfilterByHomeCare();
        Thread.sleep(2000);
        shopPage.pressFilterButton();
    }

    @Test (priority = 4)
    public void productByInstock() throws InterruptedException {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.navigateToShopPage();
        shopPage.productfilterByInstock();
        Thread.sleep(2000);
        shopPage.pressFilterButton();
    }

    @Test (priority = 5)
    public void productSearch() throws InterruptedException {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.navigateToShopPage();
        shopPage.searchProduct("hair");
    }

    @Test (priority = 6)
    public void addToCart() throws InterruptedException {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.addproductToBasket();
    }

}
