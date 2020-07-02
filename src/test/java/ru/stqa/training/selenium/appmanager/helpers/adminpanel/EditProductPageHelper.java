package ru.stqa.training.selenium.appmanager.helpers.adminpanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.training.selenium.appmanager.ApplicationManager;
import ru.stqa.training.selenium.appmanager.helpers.HelperBase;
import ru.stqa.training.selenium.models.Product;
import ru.stqa.training.selenium.models.Status;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class EditProductPageHelper extends HelperBase {

    public EditProductPageHelper(ApplicationManager app) {
        super(app);
    }

    public Product getProduct(boolean generalTab,
                              boolean informationTab,
                              boolean dataTab,
                              boolean pricesTab,
                              boolean optionsTab,
                              boolean optionsStocksTab) {
        Status status = null;
        String name = null;
        String code = null;
        List<String> categories = null;
        String defaultCategory = null;
        List<String> productGroups = null;
        String quantity = null;

        if (generalTab) {
            status = getProductStatus();
            name = getProductName();
            code = getProductCode();
            categories = getProductCategories();
            defaultCategory = getProductDefaultCategory();
            productGroups = getProductGroups();
            quantity = getProductQuantity();


            //continue from image getting. You need to rewrite the equals method of Product class so it compares
            //images' checksums. checksum of product's image you can get in the DB (lc_products_images table) or
            //you can try to download the image and get checksum form downloaded one (or try to get it without
            //downloading if it possible

            //get day, month, year of the field with date and create a GregorianCalendar instance using those values
        }

        if (informationTab) {

        }

        if (dataTab) {

        }

        if (pricesTab) {

        }

        if (optionsTab) {

        }

        if (optionsStocksTab) {

        }

        return new Product()
                .withStatus(status);
    }

    private String getProductQuantity() {
        return wd.findElement(By.cssSelector("[name='quantity']")).getAttribute("value");
    }

    private List<String> getProductGroups() {
        List<String > productGroups = new ArrayList<>();
        List<WebElement> productGroupsWeb = wd.findElements(By.cssSelector("[name='product_groups[]']"));
        for (WebElement productGroupWeb : productGroupsWeb) {
            boolean checked = Boolean.parseBoolean(productGroupWeb.getAttribute("checked"));
            if (checked) { productGroups.add(productGroupWeb.findElement(By.xpath("./../../td[2]")).getText()); }
        }
        return productGroups;
    }

    private String getProductDefaultCategory() {
        Select defaultCategoryDropdown = new Select(wd.findElement(By.cssSelector("[name='default_category_id']")));
        return defaultCategoryDropdown.getFirstSelectedOption().getAttribute("innerText");
    }

    private List<String> getProductCategories() {
        List<String> categories = new ArrayList<>();
        List<WebElement> categoriesWeb = wd.findElements(By.cssSelector("[name='categories[]']"));
        for (WebElement categoryWeb : categoriesWeb) {
            boolean checked = Boolean.parseBoolean(categoryWeb.getAttribute("checked"));
            if (checked) { categories.add(categoryWeb.getAttribute("data-name")); }
        }
        return categories;
    }

    private String getProductCode() {
        return wd.findElement(By.cssSelector("[name=code]")).getAttribute("value");
    }

    private String getProductName() {
        return wd.findElement(By.cssSelector("[name='name[en]']")).getAttribute("value");
    }

    private Status getProductStatus() {
        String statusToggle = wait.until(presenceOfElementLocated(By.cssSelector("[name=status][checked=checked]")))
                .getAttribute("value");
        if (statusToggle.equals("1")) { return Status.ENABLED; }
        else if (statusToggle.equals("0")) { return Status.DISABLED; }
        System.out.println("Status is not got");
        return null;
    }
}
