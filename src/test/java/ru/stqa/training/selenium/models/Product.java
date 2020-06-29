package ru.stqa.training.selenium.models;

import ru.stqa.training.selenium.generators.GeneratorHelper;

import java.io.File;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomUtils.nextBoolean;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class Product extends GeneratorHelper {

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
    File image;
    GregorianCalendar dateValidFrom;
    GregorianCalendar dateValidTo;
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

    public File getImage() {
        return image;
    }

    public Product withImage(File image) {
        this.image = image;
        return this;
    }

    public GregorianCalendar getDateValidFrom() {
        return dateValidFrom;
    }

    public Product withDateValidFrom(GregorianCalendar dateValidFrom) {
        this.dateValidFrom = dateValidFrom;
        return this;
    }

    public GregorianCalendar getDateValidTo() {
        return dateValidTo;
    }

    public Product withDateValidTo(GregorianCalendar dateValidTo) {
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

    public Product generateRandomProduct() throws SQLException {
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
        String quantity = Integer.toString(nextInt(1, 100000));
        String soldOutStatus = getRandomStringValueFromDB(
                "litecart", "root", "", "lc_sold_out_statuses_info", "name");
        File image = new File(System.getProperty("user.dir") +
                "/src/test/resources/test_files/road-1072823__340.jpg");
        GregorianCalendar dateValidFrom = getRandomDate(2000, 2015);
        GregorianCalendar dateValidTo = getRandomDate(2016, 2030);
        String manufacturer = getRandomStringValueFromDB(
                "litecart", "root", "", "lc_manufacturers", "name");
        String keyword = random(nextInt(4, 10), true, true);
        String shortDescription = generateRandomTextWithSpaces(3, 10);
        String description = generateRandomTextWithSpaces(10, 30);
        String headTitle = generateRandomTextWithSpaces(3, 5);
        String metaDescription = generateRandomTextWithSpaces(5, 10);
        String purchasePrice = generatePrice(0.01, 1000000);
        String currency = getRandomStringValueFromDB(
                "litecart", "root", "", "lc_currencies", "name");
        String priceUSD = generatePrice(0.1, 1000000);
        String priceEUR = generatePrice(0.1, 1000000);

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
        return campaignPrice != null ? campaignPrice.equals(product.campaignPrice) : product.campaignPrice == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (sticker != null ? sticker.hashCode() : 0);
        result = 31 * result + (regularPrice != null ? regularPrice.hashCode() : 0);
        result = 31 * result + (campaignPrice != null ? campaignPrice.hashCode() : 0);
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
