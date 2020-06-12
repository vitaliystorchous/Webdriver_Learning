package ru.stqa.training.selenium.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AdminSiteNavigationTest extends TestBase {

    @BeforeClass
    public void openAdminPanel() {
        app.goTo().adminPanel();
    }

    @Test
    public void testAppearenceTab(){
        app.goTo().appearenceTab();
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
        app.goTo().catalogTab();
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
    public void testCSVImportExportNavigationLink() {
        app.goTo().csvImportExportPage();
        assertThat(app.csvImportExportPage().getPageTitle(), equalTo(" CSV Import/Export"));
    }
}
