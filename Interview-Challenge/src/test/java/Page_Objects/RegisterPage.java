package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private WebDriver driver;

    //Constructor
    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    //Method to navigate to the register page
    public void navigateToRegisterPage() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"top-menu\"]/li[5]/a")).click();
        Thread.sleep(2000);
    }

    //Method to enter username
    public void enterUsername(String username){
        driver.findElement(By.id("reg_username")).sendKeys(username);
    }

    //Method to enter the email
    public void enterEmail(String emailaddress){
        driver.findElement(By.id("reg_email")).sendKeys(emailaddress);
    }

    //Method to enter the password
    public void enterPassword(String password){
        driver.findElement(By.id("reg_password")).sendKeys(password);
    }

    //Method to click register button
    public void clickRegisterButton(){
        driver.findElement(By.xpath("//*[@id=\"customer_login\"]/div[2]/form/p[4]/button")).click();
    }
}
