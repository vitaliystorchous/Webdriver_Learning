package ru.stqa.training.selenium.tests.homework2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class LoginTestInFirefoxNightly {

    public WebDriver wd;
    public WebDriverWait wait;

    @BeforeTest
    public void startBrowser() throws IOException {
        System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/msedgedriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Program Files\\Firefox Nightly\\firefox.exe");
        wd = new FirefoxDriver(options);
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(wd, 10);
    }

    @Test
    public void loginTest() {
        wd.navigate().to("http://localhost/litecart/en/");
        wd.findElement(By.name("email")).click();
        wd.findElement(By.name("email")).sendKeys("rufjtigk+1@gmail.com");
        wd.findElement(By.name("password")).click();
        wd.findElement(By.name("password")).sendKeys("er34ER#$");
        wd.findElement(By.name("login")).click();
        wait.until(presenceOfElementLocated(By.xpath("//a[contains(., 'Logout')]")));
    }

    @AfterTest
    public void stopBrowser() {
        wd.quit();
        wd = null;
    }
}
