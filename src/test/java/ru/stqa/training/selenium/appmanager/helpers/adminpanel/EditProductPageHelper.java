package ru.stqa.training.selenium.appmanager.helpers.adminpanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.training.selenium.appmanager.ApplicationManager;
import ru.stqa.training.selenium.appmanager.helpers.HelperBase;
import ru.stqa.training.selenium.models.standardModels.Date;
import ru.stqa.training.selenium.models.Product;
import ru.stqa.training.selenium.models.Status;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
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
                              boolean optionsStocksTab) throws IOException {
        Status status = null;
        String name = null;
        String code = null;
        List<String> categories = null;
        String defaultCategory = null;
        List<String> productGroups = null;
        String quantity = null;
        String soldOutStatus = null;
        File image = null;
        Date dateValidFrom = null;
        Date dateValidTo = null;

        if (generalTab) {
            status = getProductStatus();
            name = getProductName();
            code = getProductCode();
            categories = getProductCategories();
            defaultCategory = getProductDefaultCategory();
            productGroups = getProductGroups();
            quantity = getProductQuantity();
            soldOutStatus = getSoldOutStatus();
            image = getProductImage();
            dateValidFrom = getDateFrom(By.cssSelector("[name=date_valid_from]"));
            dateValidTo = getDateFrom(By.cssSelector("[name='date_valid_to']"));
        }


        String manufacturer = null;
        String keyword = null;
        String shortDescription = null;
        String description = null;
        String headTitle = null;
        String metaDescription = null;

        if (informationTab) {
            wd.findElement(By.cssSelector("[href='#tab-information']")).click();
            wait.until(presenceOfElementLocated(By.cssSelector("[name=\"manufacturer_id\"]")));

            manufacturer = getManufacturer();
            keyword = getKeyword();
            shortDescription = getShortDescription();
            description = getDescription();
            headTitle = getHeadTitle();
            metaDescription = getMetaDescription();
        }

        if (dataTab) {

        }


        String purchasePrice = null;
        String purchasePriceCurrency = null;
        String priceUSD = null;
        String priceEUR = null;

        if (pricesTab) {
            wd.findElement(By.cssSelector("[href=\"#tab-prices\"]")).click();
            wait.until(presenceOfElementLocated(By.cssSelector("[name=\"purchase_price\"]")));

            purchasePrice = getPurchasePrice();
            purchasePriceCurrency = getPurchasePriceCurrency();
            priceUSD = getPriceUSD();
            priceEUR = getPriceEUR();
        }

        if (optionsTab) {

        }

        if (optionsStocksTab) {

        }

        return new Product()
                .withStatus(status)
                .withName(name)
                .withCode(code)
                .withCategories(categories)
                .withDefaultCategory(defaultCategory)
                .withProductGroups(productGroups)
                .withQuantity(quantity)
                .withSoldOutStatus(soldOutStatus)
                .withImage(image)
                .withDateValidFrom(dateValidFrom)
                .withDateValidTo(dateValidTo)
                .withManufacturer(manufacturer)
                .withKeyword(keyword)
                .withShortDescription(shortDescription)
                .withDescription(description)
                .withHeadTitle(headTitle)
                .withMetaDescription(metaDescription)
                .withPurchasePrice(purchasePrice)
                .withPurchasePriceCurrency(purchasePriceCurrency)
                .withPriceUSD(priceUSD)
                .withPriceEUR(priceEUR);
    }

    private String getSoldOutStatus() {
        Select soldOutStatusDropdown = new Select(wd.findElement(By.cssSelector("[name=\"sold_out_status_id\"]")));
        return soldOutStatusDropdown.getFirstSelectedOption().getText();
    }

    private String getPriceEUR() {
        return wd.findElement(By.cssSelector("[name=\"prices[EUR]\"]")).getAttribute("value");
    }

    private String getPriceUSD() {
        return wd.findElement(By.cssSelector("[name=\"prices[USD]\"]")).getAttribute("value");
    }

    private String getPurchasePriceCurrency() {
        Select purchasePriceCurrencyDropdown = new Select(wd.findElement(By.cssSelector("[name=\"purchase_price_currency_code\"]")));
        return purchasePriceCurrencyDropdown.getFirstSelectedOption().getText();
    }

    private String getPurchasePrice() {
        return wd.findElement(By.cssSelector("[name=\"purchase_price\"]")).getAttribute("value");
    }

    private String getMetaDescription() {
        return wd.findElement(By.cssSelector("[name=\"meta_description[en]\"]")).getAttribute("value");
    }

    private String getHeadTitle() {
        return wd.findElement(By.cssSelector("[name=\"head_title[en]\"]")).getAttribute("value");
    }

    private String getDescription() {
        return wd.findElement(By.cssSelector(".trumbowyg-editor")).getText();
    }

    private String getShortDescription() {
        return wd.findElement(By.cssSelector("[name=\"short_description[en]\"]")).getAttribute("value");
    }

    private String getKeyword() {
        return wd.findElement(By.cssSelector("[name=\"keywords\"]")).getAttribute("value");
    }

    private String getManufacturer() {
        Select manufacturer = new Select(wd.findElement(By.cssSelector("[name=\"manufacturer_id\"]")));
        return manufacturer.getFirstSelectedOption().getText();
    }

    private Date getDateFrom(By locator) {
        Date date = new Date();

        String dateS = wait.until(presenceOfElementLocated(locator)).getAttribute("value");
        int year = parseInt(dateS.substring(0, 4));
        int month = parseInt(dateS.substring(5, 7));
        int day = parseInt(dateS.substring(8));

        date.withYear(year);
        date.withMonth(month);
        date.withDay(day);

        return date;
    }

    private File getProductImage() throws IOException {
        WebElement imageWeb = wd.findElement(By.cssSelector("#table-images img"));
        String j = imageWeb.getAttribute("currentSrc");
        URL imageURL = null;
        try {
            imageURL = new URL(j);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BufferedImage saveImage = ImageIO.read(imageURL);
        File downloadedImage = new File("src/test/resources/for_tests/ImagesForProductCreationTest/downloadedFile.jpg");
        if (downloadedImage.exists()) { downloadedImage.delete(); }
        ImageIO.write(saveImage, "jpg", downloadedImage);
        return  downloadedImage;
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
