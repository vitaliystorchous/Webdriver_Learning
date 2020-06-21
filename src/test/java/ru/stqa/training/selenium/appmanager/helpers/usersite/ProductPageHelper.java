package ru.stqa.training.selenium.appmanager.helpers.usersite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.training.selenium.appmanager.helpers.HelperBase;
import ru.stqa.training.selenium.models.Product;

import static java.lang.Double.parseDouble;

public class ProductPageHelper extends HelperBase {
    public ProductPageHelper(WebDriver wd) {
        super(wd);
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
}
