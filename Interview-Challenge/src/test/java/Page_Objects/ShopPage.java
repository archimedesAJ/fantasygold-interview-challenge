package Page_Objects;

import org.openqa.selenium.*;
import org.openqa.selenium.devtools.v122.domsnapshot.model.StringIndex;

public class ShopPage {
    private WebDriver driver;

    //Constructor
    public ShopPage(WebDriver driver){
        this.driver = driver;
    }

    //Method to navigate to the shop page
    public void navigateToShopPage() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"top-menu\"]/li[2]/a")).click();
        Thread.sleep(2000);
    }

    //Method to select filter by hair
    public void productfilterByHair() throws InterruptedException {
        WebElement checkbox = driver.findElement(By.xpath("//*[@id=\"wpfBlock_2\"]/div[2]/div/ul/li[1]/label/span[1]"));
        Thread.sleep(2000);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        Thread.sleep(6000);

    }

    //Method to select filter by home car
    public void productfilterByHomeCare() throws InterruptedException {
        WebElement checkbox = driver.findElement(By.xpath("//*[@id=\"wpfBlock_2\"]/div[2]/div/ul/li[2]/label/span[1]"));
        Thread.sleep(2000);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        Thread.sleep(6000);

    }

    //Method to select filter by skin care
    public void productfilterBySkinCare() throws InterruptedException {
        WebElement checkbox = driver.findElement(By.xpath("//*[@id=\"wpfBlock_2\"]/div[2]/div/ul/li[3]/label/span[1]"));
        Thread.sleep(2000);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        Thread.sleep(6000);

    }

    //Method to select filter by instock
    public void productfilterByInstock() throws InterruptedException {
        WebElement checkbox = driver.findElement(By.xpath("//*[@id=\"wpfBlock_5\"]/div[2]/ul/li[1]/label/span[1]"));
        Thread.sleep(2000);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        Thread.sleep(6000);
    }

    //Method to search for a product or item
    public void searchProduct(String productname) throws InterruptedException {
        Thread.sleep(7000);
        WebElement search =  driver.findElement(By.xpath("//span[@id='et_search_icon']"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", search);
        Thread.sleep(3000);
        WebElement search_input = driver.findElement(By.xpath("//*[@id=\"main-header\"]/div[2]/div/form/input"));

        search_input.sendKeys(productname);
        Thread.sleep(2000);

        search_input.sendKeys(Keys.ENTER);

        //create a JavaScriptExecutor instance
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        Thread.sleep(4000);
    }

    //Method to add a product to basket
    public void addproductToBasket() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"post-646\"]/h2/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[2]/div/div/div[2]/div[2]/div[4]/div/form/button")).click();
        Thread.sleep(2000);

    }

    //Method to view cart items
    public void viewCart(){
        driver.findElement(By.xpath("//*[@id=\"et-secondary-menu\"]/a")).click();
    }

    //Method to place order and checkout
    public void checkout(String first_name, String last_name, String company, String address1, String address2, String city, String postcode, String phone, String email, String username, String password) throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"post-11\"]/div/div/div/div/div[2]/div/div/div/div/div/a")).click();
        driver.findElement(By.id("billing_first_name")).sendKeys(first_name);
        driver.findElement(By.id("billing_last_name")).sendKeys(last_name);
        driver.findElement(By.id("billing_company")).sendKeys(company);
        driver.findElement(By.id("billing_address_1")).sendKeys(address1);
        driver.findElement(By.id("billing_address_2")).sendKeys(address2);
        driver.findElement(By.id("billing_city")).sendKeys(city);
        driver.findElement(By.id("billing_postcode")).sendKeys(postcode);
        driver.findElement(By.id("billing_phone")).sendKeys(phone);
        driver.findElement(By.id("billing_email")).sendKeys(email);
        driver.findElement(By.id("account_username")).sendKeys(username);
        driver.findElement(By.id("account_password")).sendKeys(password);
        Thread.sleep(3000);
        driver.findElement(By.id("place_order")).click();

    }

    //Method to press filter button
    public void pressFilterButton(){
        driver.findElement(By.xpath("//*[@id=\"wpfMainWrapper-1_443891\"]/div[6]/button")).click();
    }

}
