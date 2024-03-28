package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private WebDriver driver;

    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    public void navigateToRegisterPage() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"top-menu\"]/li[5]/a")).click();
        Thread.sleep(2000);
    }

    public void enterUsername(String username){
        driver.findElement(By.id("reg_username")).sendKeys(username);
    }

    public void enterEmail(String emailaddress){
        driver.findElement(By.id("reg_email")).sendKeys(emailaddress);
    }

    public void enterPassword(String password){
        driver.findElement(By.id("reg_password")).sendKeys(password);
    }

    public void clickRegisterButton(){
        driver.findElement(By.xpath("//*[@id=\"customer_login\"]/div[2]/form/p[4]/button")).click();
    }
}
