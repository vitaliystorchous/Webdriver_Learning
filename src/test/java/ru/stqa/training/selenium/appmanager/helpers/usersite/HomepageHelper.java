package ru.stqa.training.selenium.appmanager.helpers.usersite;

import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.stqa.training.selenium.appmanager.ApplicationManager;
import ru.stqa.training.selenium.appmanager.helpers.HelperBase;
import ru.stqa.training.selenium.models.Product;
import ru.stqa.training.selenium.models.Product.Sticker;
import ru.stqa.training.selenium.models.User;

import java.net.MalformedURLException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Double.parseDouble;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class HomepageHelper extends HelperBase {

    @FindBy(css = ".product")
    List<WebElement> products;

    public void clickRandomProduct() {
        products.get(RandomUtils.nextInt(0, products.size())).click();
    }

    public HomepageHelper(ApplicationManager app) {
        super(app);
        PageFactory.initElements(wd, this);
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
        waitHomepageIsOpened();

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
        wait.until(presenceOfElementLocated(By.cssSelector("h1")));

        return new Product()
                .withName(name)
                .withRegularPrice(regularPrice)
                .withCampaignPrice(campaignPrice);
    }

    public List<WebElement> allRegularPrices() {
        waitHomepageIsOpened();
        return wd.findElements(By.cssSelector(".regular-price"));
    }

    public List<WebElement> allCampaignPrices() {
        waitHomepageIsOpened();
        return wd.findElements(By.cssSelector(".campaign-price"));
    }

    public void createNewCustomer() {
        waitHomepageIsOpened();
        wd.findElement(By.cssSelector("#box-account-login a")).click();
    }

    private void waitHomepageIsOpened() {
        wait.until(presenceOfElementLocated(By.cssSelector("#slider-wrapper")));
    }

    public void logout() {
        wait.until(elementToBeClickable(By.cssSelector("#box-account a[href*=logout]"))).click();
    }

    public void login(User user) {
        wait.until(elementToBeClickable(By.cssSelector("[name=email]"))).click();
        wd.findElement(By.cssSelector("[name=email]")).sendKeys(user.getEmail());

        wd.findElement(By.cssSelector("[name=password]")).click();
        wd.findElement(By.cssSelector("[name=password]")).sendKeys(user.getPassword());

        wd.findElement(By.cssSelector("[name=login]")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("#box-account a[href*=logout]")));
    }

    public boolean userIsLoggedIn() {
        try {
            wait.until(presenceOfElementLocated(By.cssSelector("#box-account a[href*=logout]")));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public void openRandomProduct() {
        waitHomepageIsOpened();
        clickRandomProduct();
    }
}
