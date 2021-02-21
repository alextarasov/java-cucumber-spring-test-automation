package generalfunctionality;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class SeleniumWrapper {
    private ChromeDriverService service;
    private WebDriver driver;
    private WebDriverWait wait;

    @Value("${base.url}")
    private String baseUrl;

    @Value("${wait.timeout}")
    private int waitTimeOut;

    @Value("${chromedriver.path}")
    private String chromedriverPath;

    public void createAndStartService() throws IOException {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(chromedriverPath))
                .usingAnyFreePort()
                .build();
        service.start();
    }

    public ChromeDriverService getService() {
        return service;
    }

    public void setUpWebDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        driver = new RemoteWebDriver(service.getUrl(), chromeOptions);
        wait = new WebDriverWait(driver, waitTimeOut);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void openHomePage() {
        driver.get(baseUrl);
    }

    public void openPage(String prefix) {
        driver.get(baseUrl + prefix);
    }

    public void waitThenFillIn(By selector, String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        driver.findElement(selector).click();
        driver.findElement(selector).sendKeys(value);
    }

    public void waitThenClick(By selector) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        wait.until(ExpectedConditions.elementToBeClickable(selector));
        driver.findElement(selector).click();
    }

    public void waitUntilVisible(By selector) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public String waitAndGetElementText(By selector) {
        waitUntilVisible(selector);
        return driver.findElement(selector).getText();
    }

    public List<WebElement> waitAndFindElements(By selector) {
        waitUntilVisible(selector);
        return driver.findElements(selector);
    }

    public void driverTearDown() {
        driver.quit();
    }

    public void waitAndAcceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public void takeScreenshot(String screenshotName) throws IOException {
        String screenshotPath = "target/screenshots/" + screenshotName +
                ".png";
        File screenshotFile = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File(screenshotPath));
    }

    public void takeAndAttachScreenshot(Scenario scenario) {
        String screenshotName = "Screenshot on error of scenario '"
                + scenario.getName() + "'";
        byte[] screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", screenshotName);
    }
}
