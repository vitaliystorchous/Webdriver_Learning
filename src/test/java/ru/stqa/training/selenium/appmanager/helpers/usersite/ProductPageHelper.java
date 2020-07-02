package ru.stqa.training.selenium.appmanager.helpers.usersite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.training.selenium.appmanager.ApplicationManager;
import ru.stqa.training.selenium.appmanager.helpers.HelperBase;
import ru.stqa.training.selenium.models.Product;

import java.net.MalformedURLException;

import static java.lang.Double.parseDouble;

public class ProductPageHelper extends HelperBase {

    public ProductPageHelper(ApplicationManager app) {
        super(app);
    }

    public Product getProduct() {
        String name = wd.findElement(By.cssSelector("#box-product .title")).getText();
        Double regularPrice;
        Double campaignPrice;
        if (areElementsPresent(By.cssSelector("#box-product .price"))) {
            regularPrice = parseDouble(wd.findElement(By.cssSelector("#box-product .price"))
                    .getText()
                    .substring(1));
            campaignPrice = null;
        } else {
            regularPrice = parseDouble(wd.findElement(By.cssSelector("#box-product .regular-price"))
                    .getText()
                    .substring(1));
            campaignPrice = parseDouble(wd.findElement(By.cssSelector("#box-product .campaign-price"))
                    .getText()
                    .substring(1));
        }


        return new Product().withName(name)
                .withRegularPrice(regularPrice)
                .withCampaignPrice(campaignPrice);
    }

    public WebElement regularPrice() {
        return wd.findElement(By.cssSelector("#box-product .regular-price"));
    }

    public WebElement campaignPrice() {
        return wd.findElement(By.cssSelector("#box-product .campaign-price"));
    }
}
