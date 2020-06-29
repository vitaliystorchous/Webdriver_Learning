package ru.stqa.training.selenium.appmanager.helpers.adminpanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.training.selenium.appmanager.helpers.HelperBase;
import ru.stqa.training.selenium.models.Product;

import java.util.Calendar;

import static java.lang.Boolean.parseBoolean;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class NewProductPageHelper extends HelperBase {
    public NewProductPageHelper(WebDriver wd) {
        super(wd);
    }

    public void createProduct(Product product) {
        wait.until(elementToBeClickable(By.cssSelector("[href*=tab-general]"))).click();

        switch (product.getStatus()) {
            case ENABLED: {
                wait.until(elementToBeClickable(By.cssSelector("[name=status][value='1']"))).click();
                break;
            }
            case DISABLED: {
                wait.until(elementToBeClickable(By.cssSelector("[name=status][value='0']"))).click();
                break;
            }
        }

        type(By.cssSelector("[name*=name]"), product.getName());
        type(By.cssSelector("[name=code]"), product.getCode());

        for (String category : product.getCategories()) {
            WebElement categoryCheckbox = wd.findElement(
                    By.cssSelector("[name*=categories][data-name='" + category + "']"));
            boolean checked = parseBoolean(categoryCheckbox.getAttribute("checked"));
            if (! checked) {
                categoryCheckbox.click();
            }
        }

        Select defaultCategory = new Select(wd.findElement(By.cssSelector("[name=default_category_id]")));
        defaultCategory.selectByVisibleText(product.getDefaultCategory());

        for (String productGroup : product.getProductGroups()) {
            WebElement productGroupCheckbox = wd.findElement(
                    By.xpath("//td[.='" + productGroup + "']/..//input[@type='checkbox']"));
            boolean checked = parseBoolean(productGroupCheckbox.getAttribute("checked"));
            if (! checked) {
                productGroupCheckbox.click();
            }
        }

        type(By.cssSelector("[name=quantity]"), product.getQuantity());

        Select soldOutStatus = new Select(wd.findElement(By.cssSelector("[name=sold_out_status_id]")));
        soldOutStatus.selectByVisibleText(product.getSoldOutStatus());

        wd.findElement(By.cssSelector("[name*=new_images]")).sendKeys(product.getImage().getPath());

        StringBuilder dateValidFrom = new StringBuilder();
        dateValidFrom.append(product.getDateValidFrom().get(Calendar.DAY_OF_MONTH));
        int monthFrom = product.getDateValidFrom().get(Calendar.MONTH) + 1;
        if (monthFrom < 10) { dateValidFrom.append(0).append(monthFrom); }
        else { dateValidFrom.append(monthFrom); }
        dateValidFrom.append(product.getDateValidFrom().get(Calendar.YEAR));
        wd.findElement(By.cssSelector("[name=date_valid_from]")).sendKeys(dateValidFrom.toString());

        StringBuilder dateValidTo = new StringBuilder();
        dateValidTo.append(product.getDateValidTo().get(Calendar.DAY_OF_MONTH));
        int monthTo = product.getDateValidTo().get(Calendar.MONTH) + 1;
        if (monthTo < 10) { dateValidTo.append(0).append(monthTo); }
        else { dateValidTo.append(monthTo); }
        dateValidTo.append(product.getDateValidTo().get(Calendar.YEAR));
        wd.findElement(By.cssSelector("[name=date_valid_to]")).sendKeys(dateValidTo.toString());

        click(By.cssSelector("[href*=tab-information]"));
        wait.until(presenceOfElementLocated(By.cssSelector("[name=manufacturer_id]")));

        Select manufacturer = new Select(wd.findElement(By.cssSelector("[name=manufacturer_id]")));
        manufacturer.selectByVisibleText(product.getManufacturer());

        type(By.cssSelector("[name=keywords]"), product.getKeyword());
        type(By.cssSelector("[name*=short_description]"), product.getShortDescription());
        type(By.cssSelector(".trumbowyg-editor"), product.getDescription());
        type(By.cssSelector("[name*=head_title]"), product.getHeadTitle());
        type(By.cssSelector("[name*=meta_description]"), product.getMetaDescription());

        click(By.cssSelector("[href*=tab-prices]"));
        wait.until(elementToBeClickable(By.cssSelector("[name=purchase_price]")));

        type(By.cssSelector("[name=purchase_price]"), product.getPurchasePrice());

        Select currency = new Select(wd.findElement(By.cssSelector("[name=purchase_price_currency_code]")));
        currency.selectByVisibleText(product.getPurchasePriceCurrency());

        type(By.cssSelector("[name='prices[USD]']"), product.getPriceUSD());
        type(By.cssSelector("[name='prices[EUR]']"), product.getPriceEUR());

        click(By.cssSelector("[name=save]"));
    }
}
