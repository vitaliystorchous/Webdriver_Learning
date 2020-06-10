package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class LoginTestInDifferentBrowsers {

    public WebDriver wd;
    public WebDriverWait wait;

    @Before
    public void startBrowser() throws IOException {
        System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/msedgedriver.exe");
        wd = new EdgeDriver();
//      wd = new ChromeDriver();
//      wd = new FirefoxDriver();
//      wd = new InternetExplorerDriver();
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

    @After
    public void stopBrowser() {
        wd.quit();
        wd = null;
    }
}
