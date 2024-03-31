package TestListenerUtil;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotListener extends TestListenerAdapter {
    private WebDriver driver;

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        captureScreenshot(result.getName());
    }

    private void captureScreenshot(String testName) {
        if (driver instanceof TakesScreenshot) {
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
            File screenshotFile = screenshotDriver.getScreenshotAs(OutputType.FILE);
            try {
                Path destinationPath = Paths.get("screenshots", testName + ".png");
                Files.createDirectories(destinationPath.getParent());
                Files.move(screenshotFile.toPath(), destinationPath);
                System.out.println("Screenshot saved: " + destinationPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

