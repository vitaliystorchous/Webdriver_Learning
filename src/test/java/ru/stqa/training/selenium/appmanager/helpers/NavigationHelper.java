package ru.stqa.training.selenium.appmanager.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class NavigationHelper extends HelperBase {

    private final Properties properties;

    public NavigationHelper(WebDriver wd, Properties properties) {
        super(wd);
        this.properties = properties;
    }

    public void adminPanel() {
        wd.navigate().to("http://localhost/litecart/admin/");
        if (areElementsPresent(By.cssSelector("[name=login_form]"))) {
            type(By.cssSelector("[name=username]"), properties.getProperty("web.adminUsername"));
            type(By.cssSelector("[name=password]"), properties.getProperty("web.adminPassword"));
            click(By.cssSelector("[name=login]"));
        }
    }

    public void appearenceTab() {
        click(By.cssSelector("#box-apps-menu [href='http://localhost/litecart/admin/?app=appearance&doc=template']"));
    }

    public void templatePage() {
        appearenceTab();
        click(By.cssSelector("#box-apps-menu #doc-template"));
    }

    public void logotypePage() {
        appearenceTab();
        click(By.cssSelector("#box-apps-menu #doc-logotype"));
    }

    public void catalogTab() {
        click(By.cssSelector("#box-apps-menu #app- > [href=\"http://localhost/litecart/admin/?app=catalog&doc=catalog\"]"));
    }

    public void catalogPage() {
        catalogTab();
        click(By.cssSelector("#box-apps-menu #doc-catalog"));
    }

    public void productGroupsPage() {
        catalogTab();
        click(By.cssSelector("#box-apps-menu #doc-product_groups"));
    }

    public void optionGroupsPage() {
        catalogTab();
        click(By.cssSelector("#box-apps-menu #doc-option_groups"));
    }

    public void manufacturersPage() {
        catalogPage();
        click(By.cssSelector("#box-apps-menu #doc-manufacturers"));
    }

    public void suppliersPage() {
        catalogPage();
        click(By.cssSelector("#box-apps-menu #doc-suppliers"));
    }

    public void deliveryStatusesPage() {
        catalogPage();
        click(By.cssSelector("#box-apps-menu #doc-delivery_statuses"));
    }

    public void soldOutStatusesPage() {
        catalogPage();
        click(By.cssSelector("#box-apps-menu #doc-sold_out_statuses"));
    }

    public void quantityUnitsPage() {
        catalogPage();
        click(By.cssSelector("#box-apps-menu #doc-quantity_units"));
    }

    public void csvImportExportPage() {
        catalogPage();
        click(By.cssSelector("#box-apps-menu #doc-csv"));
    }
}
