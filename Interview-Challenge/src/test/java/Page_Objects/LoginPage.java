package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void navigateToLoginPage() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"top-menu\"]/li[5]/a")).click();
        Thread.sleep(2000);
    }

    public void enterUsernameOrEmail(String usernameoremail){
        driver.findElement(By.id("username")).sendKeys(usernameoremail);
    }

    public void enterPassword(String password){
        driver.findElement(By.id("password")).sendKeys(password);
    }

    public void clickLoginButton(){
        driver.findElement(By.xpath("//*[@id=\"customer_login\"]/div[1]/form/p[3]/button")).click();
    }
}
