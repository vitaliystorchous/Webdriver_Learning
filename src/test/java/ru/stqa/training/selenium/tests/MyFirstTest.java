package ru.stqa.training.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void start() {
        System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/msedgedriver.exe");
        driver = new ChromeDriver();
        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstTest() {
        driver.navigate().to("https://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        wait.until(elementToBeClickable(By.name("btnK")));
        driver.findElement(By.name("btnK")).click();
        wait.until(titleIs("webdriver - Поиск в Google"));
    }

    @AfterTest
    public void stop() {
        driver.quit();
        driver = null;
    }
}
