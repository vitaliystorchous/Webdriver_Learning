package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.stqa.training.selenium.appmanager.helpers.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ApplicationManager {

    private final Properties properties;
    private WebDriver wd;
    private String browser;
    private NavigationHelper navigationHelper;
    private TemplatePageHelper templatePageHelper;
    private LogotypePageHelper logotypePageHelper;
    private CatalogPageHelper catalogPageHelper;
    private ProductGroupsPageHelper productGroupsPageHelper;
    private OptionGroupsPageHelper optionGroupsPageHelper;
    private ManufacturersPageHelper manufacturersPageHelper;
    private SuppliersPageHelper suppliersPageHelper;
    private DeliveryStatusesPageHelper deliveryStatusesPageHelper;
    private SoldOutStatusesPageHelper soldOutStatusesPageHelper;
    private QuantityUnitsPageHelper quantityUnitsPageHelper;
    private CSVImportExportPageHelper csvImportExportPageHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        if ("".equals(properties.getProperty("selenium.server"))) {
            switch (browser) {
                case BrowserType.CHROME: {
                    File file = new File("src/test/resources/drivers/chromedriver.exe");
                    System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
                    wd = new ChromeDriver();
                    break;
                }
                case BrowserType.FIREFOX: {
                    File file = new File("src/test/resources/drivers/geckodriver.exe");
                    System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
                    wd = new FirefoxDriver();
                    break;
                }
                case BrowserType.IE: {
                    File file = new File("src/test/resources/drivers/IEDriverServer.exe");
                    System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
                    wd = new InternetExplorerDriver();
                    break;
                }
                case BrowserType.EDGE: {
                    File file = new File("src/test/resources/drivers/msedgedriver.exe");
                    System.setProperty("webdriver.edge.driver", file.getAbsolutePath());
                    wd = new EdgeDriver();
                    break;
                }
                case BrowserType.OPERA_BLINK: {
                    File file = new File("src/test/resources/drivers/operadriver.exe");
                    System.setProperty("webdriver.opera.driver", file.getAbsolutePath());
                    wd = new OperaDriver();
                    break;
                }
                case "mobile":
                    Map<String, String> mobileEmulation = new HashMap<>();
                    mobileEmulation.put("deviceName", System.getProperty("deviceName", "iPhone 6"));

                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
                    wd = new ChromeDriver(chromeOptions);
                    break;
            }
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
            capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "ANY")));
            wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
        }

        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(1, SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));

        navigationHelper = new NavigationHelper(wd, properties);
        templatePageHelper = new TemplatePageHelper(wd);
        logotypePageHelper = new LogotypePageHelper(wd);
        catalogPageHelper = new CatalogPageHelper(wd);
        productGroupsPageHelper = new ProductGroupsPageHelper(wd);
        optionGroupsPageHelper = new OptionGroupsPageHelper(wd);
        manufacturersPageHelper = new ManufacturersPageHelper(wd);
        suppliersPageHelper = new SuppliersPageHelper(wd);
        deliveryStatusesPageHelper = new DeliveryStatusesPageHelper(wd);
        soldOutStatusesPageHelper = new SoldOutStatusesPageHelper(wd);
        quantityUnitsPageHelper = new QuantityUnitsPageHelper(wd);
        csvImportExportPageHelper = new CSVImportExportPageHelper(wd);
    }

    public void stop() {
        wd.quit();
    }

    public WebDriver getDriver() { return wd;}

    public byte[] takeScreenshot() {
        return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
    }

    public Dimension getWindowSize() {
        return wd.manage().window().getSize();
    }

    public Dimension getViewportSize() {
        Long width = (Long) ((JavascriptExecutor) wd).executeScript(" return document.documentElement.clientWidth;");
        Long height = (Long) ((JavascriptExecutor) wd).executeScript(" return document.documentElement.clientHeight;");
        return new Dimension(width.intValue(), height.intValue());
    }

    public void refresh() {
        wd.navigate().refresh();
    }


    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public TemplatePageHelper templatePage() {
        return templatePageHelper;
    }

    public LogotypePageHelper logotypePage() {
        return logotypePageHelper;
    }

    public CatalogPageHelper catalogPage() {
        return catalogPageHelper;
    }

    public ProductGroupsPageHelper productGroupsPage() {
        return productGroupsPageHelper;
    }

    public OptionGroupsPageHelper optionGroupsPage() {
        return optionGroupsPageHelper;
    }

    public ManufacturersPageHelper manufacturersPage() {
        return manufacturersPageHelper;
    }

    public SuppliersPageHelper suppliersPage() {
        return suppliersPageHelper;
    }

    public DeliveryStatusesPageHelper deliveryStatusesPage() {
        return deliveryStatusesPageHelper;
    }

    public SoldOutStatusesPageHelper soldOutStatusesPage() {
        return soldOutStatusesPageHelper;
    }

    public QuantityUnitsPageHelper quantityUnitsPage() {
        return quantityUnitsPageHelper;
    }

    public CSVImportExportPageHelper csvImportExportPage() {
        return csvImportExportPageHelper;
    }
}
