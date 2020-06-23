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
import ru.stqa.training.selenium.appmanager.helpers.adminpanel.*;
import ru.stqa.training.selenium.appmanager.helpers.usersite.CreateAccountPageHelper;
import ru.stqa.training.selenium.appmanager.helpers.usersite.HomepageHelper;
import ru.stqa.training.selenium.appmanager.helpers.usersite.ProductPageHelper;

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
    private ScanFilesForTranslationsPage scanFilesForTranslationsPage;
    private TranslationsCSVImportExportPageHelper translationsCSVImportExportPageHelper;
    private UsersPageHelper usersPageHelper;
    private VQmodsPageHelper vQmodsPageHelper;
    private HomepageHelper homepageHelper;
    private EditCountyPageHelper editCountyPageHelper;
    private EditGeoZonePageHelper editGeoZonePageHelper;
    private ProductPageHelper productPageHelper;
    private CreateAccountPageHelper createAccountPageHelper;

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
        catalogCsvImportExportPageHelper = new CatalogCsvImportExportPageHelper(wd);
        countriesPageHelper = new CountriesPageHelper(wd);
        currenciesPageHelper = new CurrenciesPageHelper(wd);
        customersPageHelper = new CustomersPageHelper(wd);
        customersCsvImportExportPageHelper = new CustomersCsvImportExportPageHelper(wd);
        newsletterPageHelper = new NewsletterPageHelper(wd);
        geoZonesPageHelper = new GeoZonesPageHelper(wd);
        languagesPageHelper = new LanguagesPageHelper(wd);
        storageEncodingPageHelper = new StorageEncodingPageHelper(wd);
        jobModulesPageHelper = new JobModulesPageHelper(wd);
        customerModulesPageHelper = new CustomerModulesPageHelper(wd);
        shippingModulesPageHelper = new ShippingModulesPageHelper(wd);
        paymentModulesPageHelper = new PaymentModulesPageHelper(wd);
        orderTotalModulesPageHelper = new OrderTotalModulesPageHelper(wd);
        orderSuccessModulesPageHelper = new OrderSuccessModulesPageHelper(wd);
        orderActionModulesPageHelper = new OrderActionModulesPageHelper(wd);
        ordersPageHelper = new OrdersPageHelper(wd);
        orderStatusesPageHelper = new OrderStatusesPageHelper(wd);
        pagesPageHelper = new PagesPageHelper(wd);
        monthlySalesPageHelper = new MonthlySalesPageHelper(wd);
        mostSoldProductsPageHelper = new MostSoldProductsPageHelper(wd);
        mostShoppingCustomersPageHelper = new MostShoppingCustomersPageHelper(wd);
        settingsPageHelper = new SettingsPageHelper(wd);
        defaultsSettingsPageHelper = new DefaultsSettingsPageHelper(wd);
        generalSettingsPageHelper = new GeneralSettingsPageHelper(wd);
        listingSettingsPageHelper = new ListingSettingsPageHelper(wd);
        imagesSettingsPageHelper = new ImagesSettingsPageHelper(wd);
        checkoutSettingsPageHelper = new CheckoutSettingsPageHelper(wd);
        advancedSettingsPageHelper = new AdvancedSettingsPageHelper(wd);
        securitySettingsPageHelper = new SecuritySettingsPageHelper(wd);
        slidesPageHelper = new SlidesPageHelper(wd);
        taxClassesPageHelper = new TaxClassesPageHelper(wd);
        taxRatesPageHelper = new TaxRatesPageHelper(wd);
        searchTranslationsPageHelper = new SearchTranslationsPageHelper(wd);
        scanFilesForTranslationsPage = new ScanFilesForTranslationsPage(wd);
        translationsCSVImportExportPageHelper = new TranslationsCSVImportExportPageHelper(wd);
        usersPageHelper = new UsersPageHelper(wd);
        vQmodsPageHelper = new VQmodsPageHelper(wd);
        homepageHelper = new HomepageHelper(wd);
        editCountyPageHelper = new EditCountyPageHelper(wd);
        editGeoZonePageHelper = new EditGeoZonePageHelper(wd);
        productPageHelper = new ProductPageHelper(wd);
        createAccountPageHelper = new CreateAccountPageHelper(wd);
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

    public CatalogCsvImportExportPageHelper catalogCsvImportExportPage() {
        return catalogCsvImportExportPageHelper;
    }

    public CountriesPageHelper countriesPage() {
        return countriesPageHelper;
    }

    public CurrenciesPageHelper currenciesPage() {
        return currenciesPageHelper;
    }

    public CustomersPageHelper customersPage() {
        return customersPageHelper;
    }

    public CustomersCsvImportExportPageHelper customersCsvImportExportPage() {
        return customersCsvImportExportPageHelper;
    }

    public NewsletterPageHelper newsletterPage() {
        return newsletterPageHelper;
    }

    public GeoZonesPageHelper geoZonesPage() {
        return geoZonesPageHelper;
    }

    public LanguagesPageHelper languagesPage() {
        return languagesPageHelper;
    }

    public StorageEncodingPageHelper storageEncodingPage() {
        return storageEncodingPageHelper;
    }

    public JobModulesPageHelper jobModulesPage() {
        return jobModulesPageHelper;
    }

    public CustomerModulesPageHelper customerModulesPage() {
        return customerModulesPageHelper;
    }

    public ShippingModulesPageHelper shippingModulesPage() {
        return shippingModulesPageHelper;
    }

    public PaymentModulesPageHelper paymentModulesPage() {
        return paymentModulesPageHelper;
    }

    public OrderTotalModulesPageHelper orderTotalModulesPage() {
        return orderTotalModulesPageHelper;
    }

    public OrderSuccessModulesPageHelper orderSuccessModulesPage() {
        return orderSuccessModulesPageHelper;
    }

    public OrderActionModulesPageHelper orderActionModulesPage() {
        return orderActionModulesPageHelper;
    }

    public OrdersPageHelper ordersPage() {
        return ordersPageHelper;
    }

    public OrderStatusesPageHelper orderStatusesPage() {
        return orderStatusesPageHelper;
    }

    public PagesPageHelper pagesPage() {
        return pagesPageHelper;
    }

    public MonthlySalesPageHelper monthlySalesPage() {
        return monthlySalesPageHelper;
    }

    public MostSoldProductsPageHelper mostSoldProductsPage() {
        return mostSoldProductsPageHelper;
    }

    public MostShoppingCustomersPageHelper mostShoppingCustomersPage() {
        return mostShoppingCustomersPageHelper;
    }

    public SettingsPageHelper settingsPage() {
        return settingsPageHelper;
    }

    public DefaultsSettingsPageHelper defaultsSettingsPage() {
        return defaultsSettingsPageHelper;
    }

    public GeneralSettingsPageHelper generalSettingsPage() {
        return generalSettingsPageHelper;
    }

    public ListingSettingsPageHelper listingSettingsPage() {
        return listingSettingsPageHelper;
    }

    public ImagesSettingsPageHelper imagesSettingsPage() {
        return imagesSettingsPageHelper;
    }

    public CheckoutSettingsPageHelper checkoutSettingsPage() {
        return checkoutSettingsPageHelper;
    }

    public AdvancedSettingsPageHelper advancedSettingsPage() {
        return advancedSettingsPageHelper;
    }

    public SecuritySettingsPageHelper securitySettingsPage() {
        return securitySettingsPageHelper;
    }

    public SlidesPageHelper slidesPage() {
        return slidesPageHelper;
    }

    public TaxClassesPageHelper taxClassesPage() {
        return taxClassesPageHelper;
    }

    public TaxRatesPageHelper taxRatesPage() {
        return taxRatesPageHelper;
    }

    public SearchTranslationsPageHelper searchTranslationsPage() {
        return searchTranslationsPageHelper;
    }

    public ScanFilesForTranslationsPage scanFilesForTranslationsPage() {
        return scanFilesForTranslationsPage;
    }

    public TranslationsCSVImportExportPageHelper translationsCsvImportExportPage() {
        return translationsCSVImportExportPageHelper;
    }

    public UsersPageHelper usersPage() {
        return usersPageHelper;
    }

    public VQmodsPageHelper vQmodsPage() {
        return vQmodsPageHelper;
    }

    public HomepageHelper homepage() {
        return homepageHelper;
    }

    public EditCountyPageHelper editCountryPage() {
        return editCountyPageHelper;
    }

    public EditGeoZonePageHelper editGeoZonePage() {
        return editGeoZonePageHelper;
    }

    public ProductPageHelper productPage() {
        return productPageHelper;
    }

    public CreateAccountPageHelper createAccountPage() {
        return createAccountPageHelper;
    }
}
