package ru.stqa.training.selenium.models;

import ru.stqa.training.selenium.models.standardModels.Date;
import ru.stqa.training.selenium.models.standardModels.Image;

import java.io.File;
import java.sql.SQLException;
import java.util.*;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomUtils.nextBoolean;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static ru.stqa.training.selenium.models.standardModels.Date.getRandomDate;

public class Product extends ModelsHelper {

    public enum Sticker {
        NEW,
        SALE
    }

    String name;
    Sticker sticker;
    Double regularPrice;
    Double campaignPrice;
    Status status;
    String code;
    List<String> categories;
    String defaultCategory;
    List<String> productGroups;
    String quantity;
    String soldOutStatus;
    Image image;
    ru.stqa.training.selenium.models.standardModels.Date dateValidFrom;
    ru.stqa.training.selenium.models.standardModels.Date dateValidTo;
    String manufacturer;
    String keyword;
    String shortDescription;
    String description;
    String headTitle;
    String metaDescription;
    String purchasePrice;
    String purchasePriceCurrency;
    String priceUSD;
    String priceEUR;


    public String getName() {
        return name;
    }

    public Product withName(String name) {
        this.name = name;
        return this;
    }

    public Sticker getSticker() {
        return sticker;
    }

    public Product withSticker(Sticker sticker) {
        this.sticker = sticker;
        return this;
    }

    public Double getRegularPrice() {
        return regularPrice;
    }

    public Product withRegularPrice(Double regularPrice) {
        this.regularPrice = regularPrice;
        return this;
    }

    public Double getCampaignPrice() {
        return campaignPrice;
    }

    public Product withCampaignPrice(Double campaignPrice) {
        this.campaignPrice = campaignPrice;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Product withStatus(Status status) {
        this.status = status;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Product withCode(String code) {
        this.code = code;
        return this;
    }

    public List<String> getCategories() {
        return categories;
    }

    public Product withCategories(List<String> categories) {
        this.categories = categories;
        return this;
    }

    public String getDefaultCategory() {
        return defaultCategory;
    }

    public Product withDefaultCategory(String defaultCategory) {
        this.defaultCategory = defaultCategory;
        return this;
    }

    public List<String> getProductGroups() {
        return productGroups;
    }

    public Product withProductGroups(List<String> productGroups) {
        this.productGroups = productGroups;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public Product withQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getSoldOutStatus() {
        return soldOutStatus;
    }

    public Product withSoldOutStatus(String soldOutStatus) {
        this.soldOutStatus = soldOutStatus;
        return this;
    }

    public Image getImage() {
        return image;
    }

    public Product withImage(File image) {
        this.image = new Image(image);
        return this;
    }

    public ru.stqa.training.selenium.models.standardModels.Date getDateValidFrom() {
        return dateValidFrom;
    }

    public Product withDateValidFrom(ru.stqa.training.selenium.models.standardModels.Date dateValidFrom) {
        this.dateValidFrom = dateValidFrom;
        return this;
    }

    public ru.stqa.training.selenium.models.standardModels.Date getDateValidTo() {
        return dateValidTo;
    }

    public Product withDateValidTo(ru.stqa.training.selenium.models.standardModels.Date dateValidTo) {
        this.dateValidTo = dateValidTo;
        return this;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Product withManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public String getKeyword() {
        return keyword;
    }

    public Product withKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public Product withShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getHeadTitle() {
        return headTitle;
    }

    public Product withHeadTitle(String headTitle) {
        this.headTitle = headTitle;
        return this;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public Product withMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
        return this;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public Product withPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
        return this;
    }

    public String getPurchasePriceCurrency() {
        return purchasePriceCurrency;
    }

    public Product withPurchasePriceCurrency(String currency) {
        this.purchasePriceCurrency = currency;
        return this;
    }

    public String getPriceUSD() {
        return priceUSD;
    }

    public Product withPriceUSD(String priceUSD) {
        this.priceUSD = priceUSD;
        return this;
    }

    public String getPriceEUR() {
        return priceEUR;
    }

    public Product withPriceEUR(String priceEUR) {
        this.priceEUR = priceEUR;
        return this;
    }

    public Product generateRandomProduct(File image) throws SQLException {
        Status status = Status.randomStatus();
        String name = random(nextInt(5, 10), true, true);
        String code = random(nextInt(3, 10), false, true);
        List<String> categories = getRandomStringValuesFromDB(
                "litecart", "root", "", "lc_categories_info", "name");
        String defaultCategory = categories.get(nextInt(0, categories.size()));
        if (nextBoolean()) {
            categories.add("Root");
        }
        List<String> productGroups = getRandomStringValuesFromDB(
                "litecart", "root", "", "lc_product_groups_values_info", "name");
        String quantity = generateTwoDecimalNumber(1, 1000);
        String soldOutStatus = getRandomStringValueFromDB(
                "litecart", "root", "", "lc_sold_out_statuses_info", "name");
        ru.stqa.training.selenium.models.standardModels.Date dateValidFrom = getRandomDate(2000, 2015);
        Date dateValidTo = getRandomDate(2016, 2030);
        String manufacturer = getRandomStringValueFromDB(
                "litecart", "root", "", "lc_manufacturers", "name");
        String keyword = random(nextInt(4, 10), true, true);
        String shortDescription = generateRandomTextWithSpaces(3, 10);
        String description = generateRandomTextWithSpaces(10, 30);
        String headTitle = generateRandomTextWithSpaces(3, 5);
        String metaDescription = generateRandomTextWithSpaces(5, 10);
        String purchasePrice = generateTwoDecimalNumber(0.01, 1000);
        String currency = getRandomStringValueFromDB(
                "litecart", "root", "", "lc_currencies", "name");
        String priceUSD = generateTwoDecimalNumber(0.1, 1000);
        String priceEUR = generateTwoDecimalNumber(0.1, 1000);

        return new Product().withStatus(status)
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
                .withPurchasePriceCurrency(currency)
                .withPriceUSD(priceUSD)
                .withPriceEUR(priceEUR);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (sticker != product.sticker) return false;
        if (regularPrice != null ? !regularPrice.equals(product.regularPrice) : product.regularPrice != null)
            return false;
        if (campaignPrice != null ? !campaignPrice.equals(product.campaignPrice) : product.campaignPrice != null)
            return false;
        if (status != product.status) return false;
        if (code != null ? !code.equals(product.code) : product.code != null) return false;
        if (categories != null ? !categories.equals(product.categories) : product.categories != null) return false;
        if (defaultCategory != null ? !defaultCategory.equals(product.defaultCategory) : product.defaultCategory != null)
            return false;
        if (productGroups != null ? !productGroups.equals(product.productGroups) : product.productGroups != null)
            return false;
        if (quantity != null ? !quantity.equals(product.quantity) : product.quantity != null) return false;
        if (soldOutStatus != null ? !soldOutStatus.equals(product.soldOutStatus) : product.soldOutStatus != null)
            return false;
        if (image != null ? !image.equals(product.image) : product.image != null)
            return false;
        if (dateValidFrom != null ? !dateValidFrom.equals(product.dateValidFrom) : product.dateValidFrom != null)
            return false;
        if (dateValidTo != null ? !dateValidTo.equals(product.dateValidTo) : product.dateValidTo != null)
            return false;
        if (manufacturer != null ? !manufacturer.equals(product.manufacturer) : product.manufacturer != null)
            return false;
        if (keyword != null ? !keyword.equals(product.keyword) : product.keyword != null) return false;
        if (shortDescription != null ? !shortDescription.equals(product.shortDescription) : product.shortDescription != null)
            return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (headTitle != null ? !headTitle.equals(product.headTitle) : product.headTitle != null) return false;
        if (metaDescription != null ? !metaDescription.equals(product.metaDescription) : product.metaDescription != null)
            return false;
        if (purchasePrice != null ? !purchasePrice.equals(product.purchasePrice) : product.purchasePrice != null)
            return false;
        if (purchasePriceCurrency != null ? !purchasePriceCurrency.equals(product.purchasePriceCurrency) : product.purchasePriceCurrency != null)
            return false;
        if (priceUSD != null ? !priceUSD.equals(product.priceUSD) : product.priceUSD != null) return false;
        return priceEUR != null ? priceEUR.equals(product.priceEUR) : product.priceEUR == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (sticker != null ? sticker.hashCode() : 0);
        result = 31 * result + (regularPrice != null ? regularPrice.hashCode() : 0);
        result = 31 * result + (campaignPrice != null ? campaignPrice.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        result = 31 * result + (defaultCategory != null ? defaultCategory.hashCode() : 0);
        result = 31 * result + (productGroups != null ? productGroups.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (soldOutStatus != null ? soldOutStatus.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (dateValidFrom != null ? dateValidFrom.hashCode() : 0);
        result = 31 * result + (dateValidTo != null ? dateValidTo.hashCode() : 0);
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + (keyword != null ? keyword.hashCode() : 0);
        result = 31 * result + (shortDescription != null ? shortDescription.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (headTitle != null ? headTitle.hashCode() : 0);
        result = 31 * result + (metaDescription != null ? metaDescription.hashCode() : 0);
        result = 31 * result + (purchasePrice != null ? purchasePrice.hashCode() : 0);
        result = 31 * result + (purchasePriceCurrency != null ? purchasePriceCurrency.hashCode() : 0);
        result = 31 * result + (priceUSD != null ? priceUSD.hashCode() : 0);
        result = 31 * result + (priceEUR != null ? priceEUR.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", sticker=" + sticker +
                ", regularPrice=" + regularPrice +
                ", campaignPrice=" + campaignPrice +
                ", status=" + status +
                ", code='" + code + '\'' +
                ", categories=" + categories +
                ", defaultCategory='" + defaultCategory + '\'' +
                ", productGroups=" + productGroups +
                ", quantity='" + quantity + '\'' +
                ", soldOutStatus='" + soldOutStatus + '\'' +
                ", image=" + image +
                ", dateValidFrom=" + dateValidFrom +
                ", dateValidTo=" + dateValidTo +
                ", manufacturer='" + manufacturer + '\'' +
                ", keyword='" + keyword + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", description='" + description + '\'' +
                ", headTitle='" + headTitle + '\'' +
                ", metaDescription='" + metaDescription + '\'' +
                ", purchasePrice='" + purchasePrice + '\'' +
                ", currency='" + purchasePriceCurrency + '\'' +
                ", priceUSD='" + priceUSD + '\'' +
                ", priceEUR='" + priceEUR + '\'' +
                '}';
    }
}
