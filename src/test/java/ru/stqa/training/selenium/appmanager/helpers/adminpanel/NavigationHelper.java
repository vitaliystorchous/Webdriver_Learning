package ru.stqa.training.selenium.appmanager.helpers.adminpanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.stqa.training.selenium.appmanager.ApplicationManager;
import ru.stqa.training.selenium.appmanager.helpers.HelperBase;

import java.util.Properties;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class NavigationHelper extends HelperBase {

    private final Properties properties;
    private final String firstWindowHandle;

    @FindBy(name = "username")
    public WebElement usernameField;

    public NavigationHelper enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
        return this;
    }

    @FindBy(name = "password")
    public WebElement passwordField;

    public NavigationHelper enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    @FindBy(name = "login")
    public WebElement loginButton;

    public void clickLoginButton() {
        loginButton.click();
    }

    @FindBy(css = "#box-apps-menu #app- > [href*=appearance]")
    public WebElement appearanceTab;

    public NavigationHelper clickAppearanceTab() {
        appearanceTab.click();
        return this;
    }

    @FindBy(css = "#box-apps-menu #doc-template")
    public WebElement templateButton;

    public NavigationHelper clickTemplateButton() {
        templateButton.click();
        return this;
    }

    @FindBy(css = "#box-apps-menu #doc-logotype")
    public WebElement logotypeButton;

    public NavigationHelper clickLogotypeButton() {
        logotypeButton.click();
        return this;
    }

    @FindBy(css = "#box-apps-menu #app- > [href*=catalog]")
    public WebElement catalogTab;

    public NavigationHelper clickCatalogTab() {
        catalogTab.click();
        return this;
    }

    @FindBy(css = "#box-apps-menu #doc-catalog")
    public WebElement catalogButton;

    public NavigationHelper clickCatalogButton() {
        catalogButton.click();
        return this;
    }

    @FindBy(css = "#logotype-wrapper")
    public WebElement logotype;

    public NavigationHelper clickLogotype() {
        logotype.click();
        return this;
    }

    @FindBy(xpath = "//a[.=\"Checkout »\"]")
    public WebElement checkoutLink;

    public void clickCheckoutLink() {
        checkoutLink.click();
    }

    public NavigationHelper(ApplicationManager app) {
        super(app);
        PageFactory.initElements(wd, this);
        properties = app.getProperties();
        firstWindowHandle = app.getFirstWindowHandle();
    }


    public void adminPanel() {
        wd.navigate().to("http://localhost/litecart/admin/");
        if (areElementsPresent(By.cssSelector("[name=login_form]"))) {
            enterUsername(properties.getProperty("web.adminUsername"))
                    .enterPassword(properties.getProperty("web.adminPassword"))
                    .clickLoginButton();
        }
    }

    public WebElement navigationMenu() {
        return wd.findElement(By.cssSelector("#box-apps-menu"));
    }

    public void templatePageByClickingAppearanceTab() {
        clickAppearanceTab();
    }

    public void templatePage() {
        clickAppearanceTab().clickTemplateButton();
    }

    public void logotypePage() {
        clickAppearanceTab().clickLogotypeButton();
    }

    public void catalogPageByClickingCatalogTab() {
        clickCatalogTab();
    }

    public void catalogPage() {
        clickCatalogTab().clickCatalogButton();
    }

    public void productGroupsPage() {
        catalogPageByClickingCatalogTab();
        navigationMenu().findElement(By.cssSelector("#doc-product_groups")).click();
    }

    public void optionGroupsPage() {
        catalogPageByClickingCatalogTab();
        navigationMenu().findElement(By.cssSelector("#doc-option_groups")).click();
    }

    public void manufacturersPage() {
        catalogPage();
        navigationMenu().findElement(By.cssSelector("#doc-manufacturers")).click();
    }

    public void suppliersPage() {
        catalogPage();
        navigationMenu().findElement(By.cssSelector("#doc-suppliers")).click();
    }

    public void deliveryStatusesPage() {
        catalogPage();
        navigationMenu().findElement(By.cssSelector("#doc-delivery_statuses")).click();
    }

    public void soldOutStatusesPage() {
        catalogPage();
        navigationMenu().findElement(By.cssSelector("#doc-sold_out_statuses")).click();
    }

    public void quantityUnitsPage() {
        catalogPage();
        navigationMenu().findElement(By.cssSelector("#doc-quantity_units")).click();
    }

    public void catalogCsvImportExportPage() {
        catalogPage();
        navigationMenu().findElement(By.cssSelector("#doc-csv")).click();
    }

    public void countriesTab() {
        navigationMenu().findElement(By.cssSelector("#app- > [href*=countries]")).click();
    }

    public void currenciesTab() {
        navigationMenu().findElement(By.cssSelector("#app- > [href*=currencies]")).click();
    }

    public void customersTab() {
        navigationMenu().findElement(By.cssSelector("#app- > [href*=customers]")).click();
    }

    public void customersPage() {
        customersTab();
        navigationMenu().findElement(By.cssSelector("#doc-customers")).click();
    }

    public void customersCsvImportExportPage() {
        customersTab();
        navigationMenu().findElement(By.cssSelector("#doc-csv")).click();
    }

    public void newsletterPage() {
        customersTab();
        navigationMenu().findElement(By.cssSelector("#doc-newsletter")).click();
    }

    public void geoZonesTab() {
        navigationMenu().findElement(By.cssSelector("#app- > [href*=geo_zones]")).click();
    }

    public void languagesTab() {
        navigationMenu().findElement(By.cssSelector("#app- > [href*=languages]")).click();
    }

    public void languagesPage() {
        languagesTab();
        navigationMenu().findElement(By.cssSelector("#doc-languages")).click();
    }

    public void storageEncodingPage() {
        languagesTab();
        navigationMenu().findElement(By.cssSelector("#doc-storage_encoding")).click();
    }

    public void modulesTab() {
        navigationMenu().findElement(By.cssSelector("#app- > [href*=jobs]")).click();
    }

    public void jobModulesPage() {
        modulesTab();
        navigationMenu().findElement(By.cssSelector("#doc-jobs")).click();
    }

    public void customerModulesPage() {
        modulesTab();
        navigationMenu().findElement(By.cssSelector("#doc-customer")).click();
    }

    public void shippingModulesPage() {
        modulesTab();
        navigationMenu().findElement(By.cssSelector("#doc-shipping")).click();
    }

    public void paymentModulesPage() {
        modulesTab();
        navigationMenu().findElement(By.cssSelector("#doc-payment")).click();
    }

    public void orderTotalModules() {
        modulesTab();
        navigationMenu().findElement(By.cssSelector("#doc-order_total")).click();
    }

    public void orderSuccessModulesPage() {
        modulesTab();
        navigationMenu().findElement(By.cssSelector("#doc-order_success")).click();
    }

    public void orderActionModulesPage() {
        modulesTab();
        navigationMenu().findElement(By.cssSelector("#doc-order_action")).click();
    }

    public void ordersTab() {
        navigationMenu().findElement(By.cssSelector("#app- > [href*=orders]")).click();
    }

    public void ordersPage() {
        ordersTab();
        navigationMenu().findElement(By.cssSelector("#doc-orders")).click();
    }

    public void orderStatusesPage() {
        ordersTab();
        navigationMenu().findElement(By.cssSelector("#doc-order_statuses")).click();
    }

    public void pagesTab() {
        navigationMenu().findElement(By.cssSelector("#app- > [href*=pages]")).click();
    }

    public void reportsTab() {
        navigationMenu().findElement(By.cssSelector("#app- > [href*=reports]")).click();
    }

    public void monthlySalesPage() {
        reportsTab();
        navigationMenu().findElement(By.cssSelector("#doc-monthly_sales")).click();
    }

    public void mostSoldProductsPage() {
        reportsTab();
        navigationMenu().findElement(By.cssSelector("#doc-most_sold_products")).click();
    }

    public void mostShoppingCustomersPage() {
        reportsTab();
        navigationMenu().findElement(By.cssSelector("#doc-most_shopping_customers")).click();
    }

    public void settingsTab() {
        navigationMenu().findElement(By.cssSelector("#app- > [href*=settings]")).click();
    }

    public void storeInfoSettingsPage() {
        settingsTab();
        navigationMenu().findElement(By.cssSelector("#doc-store_info")).click();
    }

    public void defaultsSettingsPage() {
        settingsTab();
        navigationMenu().findElement(By.cssSelector("#doc-defaults")).click();
    }

    public void generalSettingsPage() {
        settingsTab();
        navigationMenu().findElement(By.cssSelector("#doc-general")).click();
    }

    public void listingSettingsPage() {
        settingsTab();
        navigationMenu().findElement(By.cssSelector("#doc-listings")).click();
    }

    public void imagesSettingsPage() {
        settingsTab();
        navigationMenu().findElement(By.cssSelector("#doc-images")).click();
    }

    public void checkoutSettingsPage() {
        settingsTab();
        navigationMenu().findElement(By.cssSelector("#doc-checkout")).click();
    }

    public void advancedSettingsPage() {
        settingsTab();
        navigationMenu().findElement(By.cssSelector("#doc-advanced")).click();
    }

    public void securitySettingsPage() {
        settingsTab();
        navigationMenu().findElement(By.cssSelector("#doc-security")).click();
    }

    public void slidesTab() {
        navigationMenu().findElement(By.cssSelector("#app- > [href*=slides]")).click();
    }

    public void taxTab() {
        navigationMenu().findElement(By.cssSelector("#app- > [href*=tax]")).click();
    }

    public void taxClassesPage() {
        taxTab();
        navigationMenu().findElement(By.cssSelector("#doc-tax_classes")).click();
    }

    public void taxRatesPage() {
        taxTab();
        navigationMenu().findElement(By.cssSelector("#doc-tax_rates")).click();
    }

    public void translationsTab() {
        navigationMenu().findElement(By.cssSelector("#app- > [href*=translations]")).click();
    }

    public void searchTranslationsPage() {
        translationsTab();
        navigationMenu().findElement(By.cssSelector("#doc-search")).click();
    }

    public void scanFilesForTranslationPage() {
        translationsTab();
        navigationMenu().findElement(By.cssSelector("#doc-scan")).click();
    }

    public void translationsCsvImportExportPage() {
        translationsTab();
        navigationMenu().findElement(By.cssSelector("#doc-csv")).click();
    }

    public void usersTab() {
        navigationMenu().findElement(By.cssSelector("#app- > [href*=users]")).click();
    }

    public void vQmodsTab() {
        navigationMenu().findElement(By.cssSelector("#app- > [href*=vqmods]")).click();
    }

    public void vQmodsPage() {
        vQmodsTab();
        navigationMenu().findElement(By.cssSelector("#doc-vqmods")).click();
    }

    public void homepage() {
        clickLogotype();
    }

    public void addNewProductPage() {
        wait.until(elementToBeClickable(By.cssSelector("[href*=product].button"))).click();
    }

    public void checkoutPage() {
        clickCheckoutLink();
    }

    public void closeCurrentWindowAndGoBackToMainOne() {
        wd.close();
        wd.switchTo().window(firstWindowHandle);
    }
}
