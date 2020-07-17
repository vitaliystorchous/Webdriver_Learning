package ru.stqa.training.selenium.tests.homework9;

import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import ru.stqa.training.selenium.tests.TestBase;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class CheckBrowserLogsTest extends TestBase {

    String productURL;
    List<LogEntry> logs;

    @Factory(dataProvider = "getAllProductNames")
    public CheckBrowserLogsTest(String productURL) {
        this.productURL = productURL;
    }


    @DataProvider
    public static Object[][] getAllProductNames() throws IOException {
        app.init();
        app.goTo().adminPanel();
        app.goTo().catalogPage();
        List<String> productURLs = app.catalogPage().getAllProductsURLs();

        String[][] arrayOfURLs = new String[productURLs.size()][];
        for (int i = 0; i < productURLs.size(); i++) {
            String[] URL = { productURLs.get(i) };
            arrayOfURLs[i] = URL;
        }

        return arrayOfURLs;
    }


    @Test
    public void testBrowserLogs() {
        app.goTo().catalogPage();
        app.catalogPage().openProductByURL(productURL);
        logs = app.logs().getBrowserLogs();
        assertEquals(logs.size(), 0);
    }

    @AfterMethod (alwaysRun = true)
    public void printLogs() {
        if (logs.size() > 0) {
            for (LogEntry l : logs) {
                System.out.println(l);
            }
        }
    }
}
