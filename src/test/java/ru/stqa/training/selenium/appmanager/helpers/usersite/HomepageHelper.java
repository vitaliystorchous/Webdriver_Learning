package ru.stqa.training.selenium.appmanager.helpers.usersite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.training.selenium.appmanager.helpers.HelperBase;
import ru.stqa.training.selenium.models.Product;
import ru.stqa.training.selenium.models.Product.Sticker;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Double.parseDouble;

public class HomepageHelper extends HelperBase {
    public HomepageHelper(WebDriver wd) {
        super(wd);
    }

    public List<Product> allProducts() {
        List<Product> products = new ArrayList<>();

        products.addAll(mostPopularProducts());
        products.addAll(campaignsProducts());
        products.addAll(latestProducts());

        return products;
    }

    public List<Product> latestProducts() {
        List<WebElement> products = wd.findElements(By.cssSelector("#box-latest-products .product"));
        return new ArrayList<>(getProducts(products));
    }

    public List<Product> campaignsProducts() {
        List<WebElement> products = wd.findElements(By.cssSelector("#box-campaigns .product"));
        return new ArrayList<>(getProducts(products));
    }

    public List<Product> mostPopularProducts() {
        List<WebElement> products = wd.findElements(By.cssSelector("#box-most-popular .product"));
        return new ArrayList<>(getProducts(products));
    }

    private List<Product> getProducts(List<WebElement> productsWeb) {
        List<Product> productsJava = new ArrayList<>();

        for (WebElement product : productsWeb) {
            String name = product.findElement(By.cssSelector(".name"))
                    .getAttribute("innerText");

            Sticker sticker;
            try {
                sticker = Sticker.valueOf(product
                        .findElement(By.cssSelector(".sticker"))
                        .getAttribute("innerText")
                        .toUpperCase());
            } catch (IllegalArgumentException e) {
                sticker = null;
            }

            productsJava.add(new Product().withName(name).withSticker(sticker));
        }

        return productsJava;
    }

    public Product openRandomProductInCampaignsSection() {
        List<WebElement> products = wd.findElements(By.cssSelector("#box-campaigns .product"));
        int randomProduct = ThreadLocalRandom.current().nextInt(0, products.size());
        String name = products.get(randomProduct)
                .findElement(By.cssSelector(".name")).getText();
        Double regularPrice = parseDouble(products.get(randomProduct)
                .findElement(By.cssSelector(".regular-price"))
                .getText()
                .substring(1));
        Double campaignPrice = parseDouble(products.get(randomProduct)
                .findElement(By.cssSelector(".campaign-price"))
                .getText()
                .substring(1));
        products.get(randomProduct).click();

        return new Product()
                .withName(name)
                .withRegularPrice(regularPrice)
                .withCampaignPrice(campaignPrice);
    }
}
