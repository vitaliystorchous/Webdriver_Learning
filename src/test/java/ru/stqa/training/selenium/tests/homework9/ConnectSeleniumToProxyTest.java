package ru.stqa.training.selenium.tests.homework9;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ConnectSeleniumToProxyTest {

    BrowserMobProxy proxy;
    WebDriver driver;

    @BeforeTest
    public void init() {
        // start the proxy
        proxy = new BrowserMobProxyServer();
        proxy.start(0);

        // get the Selenium proxy object
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        // configure it as a desired capability
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.PROXY, seleniumProxy);

        // start the browser up
        driver = new ChromeDriver(options);
    }

    @Test
    public void test() {
        // create a new HAR with the label "w3schools.com"
        proxy.newHar("w3schools.com");

        // open w3schools.com
        driver.get("https://www.w3schools.com/");
        driver.findElement(By.cssSelector("#details-button")).click();
        driver.findElement(By.cssSelector("#proceed-link")).click();
        driver.findElement(By.cssSelector("#mySidenav a[href=\"/html/default.asp\"]")).click();

        // get the HAR data
        Har har = proxy.endHar();

        har.getLog().getEntries()
                .forEach(l -> System.out.println(l.getResponse().getStatus() + ":" + l.getRequest().getUrl()));
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
