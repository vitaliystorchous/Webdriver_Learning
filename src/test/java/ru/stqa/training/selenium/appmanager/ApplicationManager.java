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
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.appmanager.helpers.adminpanel.EditProductPageHelper;
import ru.stqa.training.selenium.appmanager.helpers.adminpanel.*;
import ru.stqa.training.selenium.appmanager.helpers.usersite.CheckoutPageHelper;
import ru.stqa.training.selenium.appmanager.helpers.usersite.CreateAccountPageHelper;
import ru.stqa.training.selenium.appmanager.helpers.usersite.HomepageHelper;
import ru.stqa.training.selenium.appmanager.helpers.usersite.ProductPageHelper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ApplicationManager {

    private final Properties properties;
    private WebDriver wd;
    private WebDriverWait wait;
    public int implicitWaitTimeAmount = 10;
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
    private CatalogCsvImportExportPageHelper catalogCsvImportExportPageHelper;
    private CountriesPageHelper countriesPageHelper;
    private CurrenciesPageHelper currenciesPageHelper;
    private CustomersPageHelper customersPageHelper;
    private CustomersCsvImportExportPageHelper customersCsvImportExportPageHelper;
    private NewsletterPageHelper newsletterPageHelper;
    private GeoZonesPageHelper geoZonesPageHelper;
    private LanguagesPageHelper languagesPageHelper;
    private StorageEncodingPageHelper storageEncodingPageHelper;
    private JobModulesPageHelper jobModulesPageHelper;
    private CustomerModulesPageHelper customerModulesPageHelper;
    private ShippingModulesPageHelper shippingModulesPageHelper;
    private PaymentModulesPageHelper paymentModulesPageHelper;
    private OrderTotalModulesPageHelper orderTotalModulesPageHelper;
    private OrderSuccessModulesPageHelper orderSuccessModulesPageHelper;
    private OrderActionModulesPageHelper orderActionModulesPageHelper;
    private OrdersPageHelper ordersPageHelper;
    private OrderStatusesPageHelper orderStatusesPageHelper;
    private PagesPageHelper pagesPageHelper;
    private MonthlySalesPageHelper monthlySalesPageHelper;
    private MostSoldProductsPageHelper mostSoldProductsPageHelper;
    private MostShoppingCustomersPageHelper mostShoppingCustomersPageHelper;
    private SettingsPageHelper settingsPageHelper;
    private DefaultsSettingsPageHelper defaultsSettingsPageHelper;
    private GeneralSettingsPageHelper generalSettingsPageHelper;
    private ListingSettingsPageHelper listingSettingsPageHelper;
    private ImagesSettingsPageHelper imagesSettingsPageHelper;
    private CheckoutSettingsPageHelper checkoutSettingsPageHelper;
    private AdvancedSettingsPageHelper advancedSettingsPageHelper;
    private SecuritySettingsPageHelper securitySettingsPageHelper;
    private SlidesPageHelper slidesPageHelper;
    private TaxClassesPageHelper taxClassesPageHelper;
    private TaxRatesPageHelper taxRatesPageHelper;
    private SearchTranslationsPageHelper searchTranslationsPageHelper;
    private ScanFilesForTranslationsPageHelper scanFilesForTranslationsPageHelper;
    private TranslationsCSVImportExportPageHelper translationsCSVImportExportPageHelper;
    private UsersPageHelper usersPageHelper;
    private VQmodsPageHelper vQmodsPageHelper;
    private HomepageHelper homepageHelper;
    private EditCountyPageHelper editCountyPageHelper;
    private EditGeoZonePageHelper editGeoZonePageHelper;
    private ProductPageHelper productPageHelper;
    private CreateAccountPageHelper createAccountPageHelper;
    private NewProductPageHelper newProductPageHelper;
    private EditProductPageHelper editProductPageHelper;
    private CheckoutPageHelper checkoutPageHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public WebDriver getDriver() {
        if (wd == null) {
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
                try {
                    wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }

            wd.manage().window().maximize();
            wd.manage().timeouts().implicitlyWait(implicitWaitTimeAmount, SECONDS);
            wait = new WebDriverWait(wd, 10);
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }

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
        if (navigationHelper == null) {
            navigationHelper = new NavigationHelper(this);
        }
        return navigationHelper;
    }

    public TemplatePageHelper templatePage() {
        if (templatePageHelper == null) {
            templatePageHelper = new TemplatePageHelper(this);
        }
        return templatePageHelper;
    }

    public LogotypePageHelper logotypePage() {
        if (logotypePageHelper == null) {
            logotypePageHelper = new LogotypePageHelper(this);
        }
        return logotypePageHelper;
    }

    public CatalogPageHelper catalogPage() {
        if (catalogPageHelper == null) {
            catalogPageHelper = new CatalogPageHelper(this);
        }
        return catalogPageHelper;
    }

    public ProductGroupsPageHelper productGroupsPage() {
        if (productGroupsPageHelper == null) {
            productGroupsPageHelper = new ProductGroupsPageHelper(this);
        }
        return productGroupsPageHelper;
    }

    public OptionGroupsPageHelper optionGroupsPage() {
        if (optionGroupsPageHelper == null) {
            optionGroupsPageHelper = new OptionGroupsPageHelper(this);
        }
        return optionGroupsPageHelper;
    }

    public ManufacturersPageHelper manufacturersPage() {
        if (manufacturersPageHelper == null) {
            manufacturersPageHelper = new ManufacturersPageHelper(this);
        }
        return manufacturersPageHelper;
    }

    public SuppliersPageHelper suppliersPage() {
        if (suppliersPageHelper == null) {
            suppliersPageHelper = new SuppliersPageHelper(this);
        }
        return suppliersPageHelper;
    }

    public DeliveryStatusesPageHelper deliveryStatusesPage() {
        if (deliveryStatusesPageHelper == null) {
            deliveryStatusesPageHelper = new DeliveryStatusesPageHelper(this);
        }
        return deliveryStatusesPageHelper;
    }

    public SoldOutStatusesPageHelper soldOutStatusesPage() {
        if (soldOutStatusesPageHelper == null) {
            soldOutStatusesPageHelper = new SoldOutStatusesPageHelper(this);
        }
        return soldOutStatusesPageHelper;
    }

    public QuantityUnitsPageHelper quantityUnitsPage() {
        if (quantityUnitsPageHelper == null) {
            quantityUnitsPageHelper = new QuantityUnitsPageHelper(this);
        }
        return quantityUnitsPageHelper;
    }

    public CatalogCsvImportExportPageHelper catalogCsvImportExportPage() {
        if (catalogCsvImportExportPageHelper == null) {
            catalogCsvImportExportPageHelper = new CatalogCsvImportExportPageHelper(this);
        }
        return catalogCsvImportExportPageHelper;
    }

    public CountriesPageHelper countriesPage() {
        if (countriesPageHelper == null) {
            countriesPageHelper = new CountriesPageHelper(this);
        }
        return countriesPageHelper;
    }

    public CurrenciesPageHelper currenciesPage() {
        if (currenciesPageHelper == null) {
            currenciesPageHelper = new CurrenciesPageHelper(this);
        }
        return currenciesPageHelper;
    }

    public CustomersPageHelper customersPage() {
        if (customersPageHelper == null) {
            customersPageHelper = new CustomersPageHelper(this);
        }
        return customersPageHelper;
    }

    public CustomersCsvImportExportPageHelper customersCsvImportExportPage() {
        if (customersCsvImportExportPageHelper == null) {
            customersCsvImportExportPageHelper = new CustomersCsvImportExportPageHelper(this);
        }
        return customersCsvImportExportPageHelper;
    }

    public NewsletterPageHelper newsletterPage() {
        if (newsletterPageHelper == null) {
            newsletterPageHelper = new NewsletterPageHelper(this);
        }
        return newsletterPageHelper;
    }

    public GeoZonesPageHelper geoZonesPage() {
        if (geoZonesPageHelper == null) {
            geoZonesPageHelper = new GeoZonesPageHelper(this);
        }
        return geoZonesPageHelper;
    }

    public LanguagesPageHelper languagesPage() {
        if (languagesPageHelper == null) {
            languagesPageHelper = new LanguagesPageHelper(this);
        }
        return languagesPageHelper;
    }

    public StorageEncodingPageHelper storageEncodingPage() {
        if (storageEncodingPageHelper == null) {
            storageEncodingPageHelper = new StorageEncodingPageHelper(this);
        }
        return storageEncodingPageHelper;
    }

    public JobModulesPageHelper jobModulesPage() {
        if (jobModulesPageHelper == null) {
            jobModulesPageHelper = new JobModulesPageHelper(this);
        }
        return jobModulesPageHelper;
    }

    public CustomerModulesPageHelper customerModulesPage() {
        if (customerModulesPageHelper == null) {
            customerModulesPageHelper = new CustomerModulesPageHelper(this);
        }
        return customerModulesPageHelper;
    }

    public ShippingModulesPageHelper shippingModulesPage() {
        if (shippingModulesPageHelper == null) {
            shippingModulesPageHelper = new ShippingModulesPageHelper(this);
        }
        return shippingModulesPageHelper;
    }

    public PaymentModulesPageHelper paymentModulesPage() {
        if (paymentModulesPageHelper == null) {
            paymentModulesPageHelper = new PaymentModulesPageHelper(this);
        }
        return paymentModulesPageHelper;
    }

    public OrderTotalModulesPageHelper orderTotalModulesPage() {
        if (orderTotalModulesPageHelper == null) {
            orderTotalModulesPageHelper = new OrderTotalModulesPageHelper(this);
        }
        return orderTotalModulesPageHelper;
    }

    public OrderSuccessModulesPageHelper orderSuccessModulesPage() {
        if (orderSuccessModulesPageHelper == null) {
            orderSuccessModulesPageHelper = new OrderSuccessModulesPageHelper(this);
        }
        return orderSuccessModulesPageHelper;
    }

    public OrderActionModulesPageHelper orderActionModulesPage() {
        if (orderActionModulesPageHelper == null) {
            orderActionModulesPageHelper = new OrderActionModulesPageHelper(this);
        }
        return orderActionModulesPageHelper;
    }

    public OrdersPageHelper ordersPage() {
        if (ordersPageHelper == null) {
            ordersPageHelper = new OrdersPageHelper(this);
        }
        return ordersPageHelper;
    }

    public OrderStatusesPageHelper orderStatusesPage() {
        if (orderStatusesPageHelper == null) {
            orderStatusesPageHelper = new OrderStatusesPageHelper(this);
        }
        return orderStatusesPageHelper;
    }

    public PagesPageHelper pagesPage() {
        if (pagesPageHelper == null) {
            pagesPageHelper = new PagesPageHelper(this);
        }
        return pagesPageHelper;
    }

    public MonthlySalesPageHelper monthlySalesPage() {
        if (monthlySalesPageHelper == null) {
            monthlySalesPageHelper = new MonthlySalesPageHelper(this);
        }
        return monthlySalesPageHelper;
    }

    public MostSoldProductsPageHelper mostSoldProductsPage() {
        if (mostSoldProductsPageHelper == null) {
            mostSoldProductsPageHelper = new MostSoldProductsPageHelper(this);
        }
        return mostSoldProductsPageHelper;
    }

    public MostShoppingCustomersPageHelper mostShoppingCustomersPage() {
        if (mostShoppingCustomersPageHelper == null) {
            mostShoppingCustomersPageHelper = new MostShoppingCustomersPageHelper(this);
        }
        return mostShoppingCustomersPageHelper;
    }

    public SettingsPageHelper settingsPage() {
        if (settingsPageHelper == null) {
            settingsPageHelper = new SettingsPageHelper(this);
        }
        return settingsPageHelper;
    }

    public DefaultsSettingsPageHelper defaultsSettingsPage() {
        if (defaultsSettingsPageHelper == null) {
            defaultsSettingsPageHelper = new DefaultsSettingsPageHelper(this);
        }
        return defaultsSettingsPageHelper;
    }

    public GeneralSettingsPageHelper generalSettingsPage() {
        if (generalSettingsPageHelper == null) {
            generalSettingsPageHelper = new GeneralSettingsPageHelper(this);
        }
        return generalSettingsPageHelper;
    }

    public ListingSettingsPageHelper listingSettingsPage() {
        if (listingSettingsPageHelper == null) {
            listingSettingsPageHelper = new ListingSettingsPageHelper(this);
        }
        return listingSettingsPageHelper;
    }

    public ImagesSettingsPageHelper imagesSettingsPage() {
        if (imagesSettingsPageHelper == null) {
            imagesSettingsPageHelper = new ImagesSettingsPageHelper(this);
        }
        return imagesSettingsPageHelper;
    }

    public CheckoutSettingsPageHelper checkoutSettingsPage() {
        if (checkoutSettingsPageHelper == null) {
            checkoutSettingsPageHelper = new CheckoutSettingsPageHelper(this);
        }
        return checkoutSettingsPageHelper;
    }

    public AdvancedSettingsPageHelper advancedSettingsPage() {
        if (advancedSettingsPageHelper == null) {
            advancedSettingsPageHelper = new AdvancedSettingsPageHelper(this);
        }
        return advancedSettingsPageHelper;
    }

    public SecuritySettingsPageHelper securitySettingsPage() {
        if (securitySettingsPageHelper == null) {
            securitySettingsPageHelper = new SecuritySettingsPageHelper(this);
        }
        return securitySettingsPageHelper;
    }

    public SlidesPageHelper slidesPage() {
        if (slidesPageHelper == null) {
            slidesPageHelper = new SlidesPageHelper(this);
        }
        return slidesPageHelper;
    }

    public TaxClassesPageHelper taxClassesPage() {
        if (taxClassesPageHelper == null) {
            taxClassesPageHelper = new TaxClassesPageHelper(this);
        }
        return taxClassesPageHelper;
    }

    public TaxRatesPageHelper taxRatesPage() {
        if (taxRatesPageHelper == null) {
            taxRatesPageHelper = new TaxRatesPageHelper(this);
        }
        return taxRatesPageHelper;
    }

    public SearchTranslationsPageHelper searchTranslationsPage() {
        if (searchTranslationsPageHelper == null) {
            searchTranslationsPageHelper = new SearchTranslationsPageHelper(this);
        }
        return searchTranslationsPageHelper;
    }

    public ScanFilesForTranslationsPageHelper scanFilesForTranslationsPage() {
        if (scanFilesForTranslationsPageHelper == null) {
            scanFilesForTranslationsPageHelper = new ScanFilesForTranslationsPageHelper(this);
        }
        return scanFilesForTranslationsPageHelper;
    }

    public TranslationsCSVImportExportPageHelper translationsCsvImportExportPage() {
        if (translationsCSVImportExportPageHelper == null) {
            translationsCSVImportExportPageHelper = new TranslationsCSVImportExportPageHelper(this);
        }
        return translationsCSVImportExportPageHelper;
    }

    public UsersPageHelper usersPage() {
        if (usersPageHelper == null) {
            usersPageHelper = new UsersPageHelper(this);
        }
        return usersPageHelper;
    }

    public VQmodsPageHelper vQmodsPage() {
        if (vQmodsPageHelper == null) {
            vQmodsPageHelper = new VQmodsPageHelper(this);
        }
        return vQmodsPageHelper;
    }

    public HomepageHelper homepage() {
        if (homepageHelper == null) {
            homepageHelper = new HomepageHelper(this);
        }
        return homepageHelper;
    }

    public EditCountyPageHelper editCountryPage() {
        if (editCountyPageHelper == null) {
            editCountyPageHelper = new EditCountyPageHelper(this);
        }
        return editCountyPageHelper;
    }

    public EditGeoZonePageHelper editGeoZonePage() {
        if (editGeoZonePageHelper == null) {
            editGeoZonePageHelper = new EditGeoZonePageHelper(this);
        }
        return editGeoZonePageHelper;
    }

    public ProductPageHelper productPage() {
        if (productPageHelper == null) {
            productPageHelper = new ProductPageHelper(this);
        }
        return productPageHelper;
    }

    public CreateAccountPageHelper createAccountPage() {
        if (createAccountPageHelper == null) {
            createAccountPageHelper = new CreateAccountPageHelper(this);
        }
        return createAccountPageHelper;
    }

    public NewProductPageHelper addNewProductPage() {
        if (newProductPageHelper == null) {
            newProductPageHelper = new NewProductPageHelper(this);
        }
        return newProductPageHelper;
    }

    public EditProductPageHelper editProductPage() {
        if (editProductPageHelper == null) {
            editProductPageHelper = new EditProductPageHelper(this);
        }
        return editProductPageHelper;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public Properties getProperties() {
        return properties;
    }

    public CheckoutPageHelper checkoutPage() {
        if (checkoutPageHelper == null) {
            checkoutPageHelper = new CheckoutPageHelper(this);
        }
        return checkoutPageHelper;
    }
}
