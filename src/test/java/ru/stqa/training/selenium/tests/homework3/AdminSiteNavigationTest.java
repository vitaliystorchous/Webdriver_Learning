package ru.stqa.training.selenium.tests.homework3;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.training.selenium.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AdminSiteNavigationTest extends TestBase {

    @BeforeClass
    public void openAdminPanel() {
        app.goTo().adminPanel();
    }

    @Test
    public void testAppearenceTab(){
        app.goTo().templatePageByClickingAppearanceTab();
        assertThat(app.templatePage().getPageTitle(), equalTo(" Template"));
    }

    @Test
    public void testTemplateNavigationLink() {
        app.goTo().templatePage();
        assertThat(app.templatePage().getPageTitle(), equalTo(" Template"));
    }

    @Test
    public void testLogotypeNavigationLink() {
        app.goTo().logotypePage();
        assertThat(app.logotypePage().getPageTitle(), equalTo(" Logotype"));
    }

    @Test
    public void testCatalogTab() {
        app.goTo().catalogPageByClickingCatalogTab();
        assertThat(app.catalogPage().getPageTitle(), equalTo(" Catalog"));
    }

    @Test
    public void testCatalogNavigationLink() {
        app.goTo().catalogPage();
        assertThat(app.catalogPage().getPageTitle(), equalTo(" Catalog"));
    }

    @Test
    public void testProductGroupsNavigationLink() {
        app.goTo().productGroupsPage();
        assertThat(app.productGroupsPage().getPageTitle(), equalTo(" Product Groups"));
    }

    @Test
    public void testOptionGroupsNavigationLink() {
        app.goTo().optionGroupsPage();
        assertThat(app.optionGroupsPage().getPageTitle(), equalTo(" Option Groups"));
    }

    @Test
    public void testManufacturersNavigationLink() {
        app.goTo().manufacturersPage();
        assertThat(app.manufacturersPage().getPageTitle(), equalTo(" Manufacturers"));
    }

    @Test
    public void testSuppliersNavigationLink() {
        app.goTo().suppliersPage();
        assertThat(app.suppliersPage().getPageTitle(), equalTo(" Suppliers"));
    }

    @Test
    public void testDeliveryStatusesNavigationLink() {
        app.goTo().deliveryStatusesPage();
        assertThat(app.deliveryStatusesPage().getPageTitle(), equalTo(" Delivery Statuses"));
    }

    @Test
    public void testSoldOutStatusesNavigationLink() {
        app.goTo().soldOutStatusesPage();
        assertThat(app.soldOutStatusesPage().getPageTitle(), equalTo(" Sold Out Statuses"));
    }

    @Test
    public void testQuantityUnitsNavigationLink() {
        app.goTo().quantityUnitsPage();
        assertThat(app.quantityUnitsPage().getPageTitle(), equalTo(" Quantity Units"));
    }

    @Test
    public void testCSVImportExportNavigationLinkUnderCatalogTab() {
        app.goTo().catalogCsvImportExportPage();
        assertThat(app.catalogCsvImportExportPage().getPageTitle(), equalTo(" CSV Import/Export"));
    }

    @Test
    public void testCountriesTab() {
        app.goTo().countriesTab();
        assertThat(app.countriesPage().getPageTitle(), equalTo(" Countries"));
    }

    @Test
    public void testCurrenciesTab() {
        app.goTo().currenciesTab();
        assertThat(app.currenciesPage().getPageTitle(), equalTo(" Currencies"));
    }

    @Test
    public void testCustomersTab() {
        app.goTo().customersTab();
        assertThat(app.customersPage().getPageTitle(), equalTo(" Customers"));
    }

    @Test
    public void testCustomersNavigationLink() {
        app.goTo().customersPage();
        assertThat(app.customersPage().getPageTitle(), equalTo(" Customers"));
    }

    @Test
    public void testCSVImportExportNavigationLinkUnderCustomersTab() {
        app.goTo().customersCsvImportExportPage();
        assertThat(app.customersCsvImportExportPage().getPageTitle(), equalTo(" CSV Import/Export"));
    }

    @Test
    public void testNewsletterNavigationLink() {
        app.goTo().newsletterPage();
        assertThat(app.newsletterPage().getPageTitle(), equalTo(" Newsletter"));
    }

    @Test
    public void testGeoZonesTab() {
        app.goTo().geoZonesTab();
        assertThat(app.geoZonesPage().getPageTitle(), equalTo(" Geo Zones"));
    }

    @Test
    public void testLanguagesTag() {
        app.goTo().languagesTab();
        assertThat(app.languagesPage().getPageTitle(), equalTo(" Languages"));
    }

    @Test
    public void testLanguagesNavigationLink() {
        app.goTo().languagesPage();
        assertThat(app.languagesPage().getPageTitle(), equalTo(" Languages"));
    }

    @Test
    public void testStorageEncodingNavigationLink() {
        app.goTo().storageEncodingPage();
        assertThat(app.storageEncodingPage().getPageTitle(), equalTo(" Storage Encoding"));
    }

    @Test
    public void testModulesTab() {
        app.goTo().modulesTab();
        assertThat(app.jobModulesPage().getPageTitle(), equalTo(" Job Modules"));
    }

    @Test
    public void testBackgroundJobsNavigationLink() {
        app.goTo().jobModulesPage();
        assertThat(app.jobModulesPage().getPageTitle(), equalTo(" Job Modules"));
    }

    @Test
    public void testCustomerNavigationLink() {
        app.goTo().customerModulesPage();
        assertThat(app.customerModulesPage().getPageTitle(), equalTo(" Customer Modules"));
    }

    @Test
    public void testShippingNavigationLink() {
        app.goTo().shippingModulesPage();
        assertThat(app.shippingModulesPage().getPageTitle(), equalTo(" Shipping Modules"));
    }

    @Test
    public void testPaymentNavigationLink() {
        app.goTo().paymentModulesPage();
        assertThat(app.paymentModulesPage().getPageTitle(), equalTo(" Payment Modules"));
    }

    @Test
    public void testOrderTotalNavigationLink() {
        app.goTo().orderTotalModules();
        assertThat(app.orderTotalModulesPage().getPageTitle(), equalTo(" Order Total Modules"));
    }

    @Test
    public void testOrderSuccessNavigationLink() {
        app.goTo().orderSuccessModulesPage();
        assertThat(app.orderSuccessModulesPage().getPageTitle(), equalTo(" Order Success Modules"));
    }

    @Test
    public void testOrderActionNavigationLink() {
        app.goTo().orderActionModulesPage();
        assertThat(app.orderActionModulesPage().getPageTitle(), equalTo(" Order Action Modules"));
    }

    @Test
    public void testOrdersTab() {
        app.goTo().ordersTab();
        assertThat(app.ordersPage().getPageTitle(), equalTo(" Orders"));
    }

    @Test
    public void testOrdersNavigationLink() {
        app.goTo().ordersPage();
        assertThat(app.ordersPage().getPageTitle(), equalTo(" Orders"));
    }

    @Test
    public void testOrderStatusesNavigationLink() {
        app.goTo().orderStatusesPage();
        assertThat(app.orderStatusesPage().getPageTitle(), equalTo(" Order Statuses"));
    }

    @Test
    public void testPagesTab() {
        app.goTo().pagesTab();
        assertThat(app.pagesPage().getPageTitle(), equalTo(" Pages"));
    }

    @Test
    public void testReportsTab() {
        app.goTo().reportsTab();
        assertThat(app.monthlySalesPage().getPageTitle(), equalTo(" Monthly Sales"));
    }

    @Test
    public void testMonthlySalesNavigationLink() {
        app.goTo().monthlySalesPage();
        assertThat(app.monthlySalesPage().getPageTitle(), equalTo(" Monthly Sales"));
    }

    @Test
    public void testMostSoldProductsNavigationLink() {
        app.goTo().mostSoldProductsPage();
        assertThat(app.mostSoldProductsPage().getPageTitle(), equalTo(" Most Sold Products"));
    }

    @Test
    public void testMostShoppingCustomersNavigationLink() {
        app.goTo().mostShoppingCustomersPage();
        assertThat(app.mostShoppingCustomersPage().getPageTitle(), equalTo(" Most Shopping Customers"));
    }

    @Test
    public void testSettingsTab() {
        app.goTo().settingsTab();
        assertThat(app.settingsPage().getPageTitle(), equalTo(" Settings"));
    }

    @Test
    public void testStoreInfoNavigationLink() {
        app.goTo().storeInfoSettingsPage();
        assertThat(app.settingsPage().getPageTitle(), equalTo(" Settings"));
    }

    @Test
    public void testDefaultsNavigationLink() {
        app.goTo().defaultsSettingsPage();
        assertThat(app.defaultsSettingsPage().getPageTitle(), equalTo(" Settings"));
    }

    @Test
    public void testGeneralNavigationLink() {
        app.goTo().generalSettingsPage();
        assertThat(app.generalSettingsPage().getPageTitle(), equalTo(" Settings"));
    }

    @Test
    public void testListingNavigationLink() {
        app.goTo().listingSettingsPage();
        assertThat(app.listingSettingsPage().getPageTitle(), equalTo(" Settings"));
    }

    @Test
    public void testImagesNavigationLink() {
        app.goTo().imagesSettingsPage();
        assertThat(app.imagesSettingsPage().getPageTitle(), equalTo(" Settings"));
    }

    @Test
    public void testCheckoutNavigationLink() {
        app.goTo().checkoutSettingsPage();
        assertThat(app.checkoutSettingsPage().getPageTitle(), equalTo(" Settings"));
    }

    @Test
    public void testAdvancedNavigationLink() {
        app.goTo().advancedSettingsPage();
        assertThat(app.advancedSettingsPage().getPageTitle(), equalTo(" Settings"));
    }

    @Test
    public void testSecurityNavigationLink() {
        app.goTo().securitySettingsPage();
        assertThat(app.securitySettingsPage().getPageTitle(), equalTo(" Settings"));
    }

    @Test
    public void testSlidesTab() {
        app.goTo().slidesTab();
        assertThat(app.slidesPage().getPageTitle(), equalTo(" Slides"));
    }

    @Test
    public void testTaxTab() {
        app.goTo().taxTab();
        assertThat(app.taxClassesPage().getPageTitle(), equalTo(" Tax Classes"));
    }

    @Test
    public void testTaxClassesNavigationLink() {
        app.goTo().taxClassesPage();
        assertThat(app.taxClassesPage().getPageTitle(), equalTo(" Tax Classes"));
    }

    @Test
    public void testTaxRatesNavigationLink() {
        app.goTo().taxRatesPage();
        assertThat(app.taxRatesPage().getPageTitle(), equalTo(" Tax Rates"));
    }

    @Test
    public void testTranslationsTab() {
        app.goTo().translationsTab();
        assertThat(app.searchTranslationsPage().getPageTitle(), equalTo(" Search Translations"));
    }

    @Test
    public void testSearchTranslationsNavigationLink() {
        app.goTo().searchTranslationsPage();
        assertThat(app.searchTranslationsPage().getPageTitle(), equalTo(" Search Translations"));
    }

    @Test
    public void testScanFilesNavigationLink() {
        app.goTo().scanFilesForTranslationPage();
        assertThat(app.scanFilesForTranslationsPage().getPageTitle(), equalTo(" Scan Files For Translations"));
    }

    @Test
    public void testCSVImportExportNavigationLinkUnderTranslationsTab() {
        app.goTo().translationsCsvImportExportPage();
        assertThat(app.translationsCsvImportExportPage().getPageTitle(), equalTo(" CSV Import/Export"));
    }

    @Test
    public void testUsersTab() {
        app.goTo().usersTab();
        assertThat(app.usersPage().getPageTitle(), equalTo(" Users"));
    }

    @Test
    public void testVQmodsTab() {
        app.goTo().vQmodsTab();
        assertThat(app.vQmodsPage().getPageTitle(), equalTo(" vQmods"));
    }

    @Test
    public void testVQmodsNavigationLink() {
        app.goTo().vQmodsPage();
        assertThat(app.vQmodsPage().getPageTitle(), equalTo(" vQmods"));
    }
}
