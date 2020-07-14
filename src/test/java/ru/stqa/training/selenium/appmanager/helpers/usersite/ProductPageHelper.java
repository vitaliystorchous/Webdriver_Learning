package ru.stqa.training.selenium.appmanager.helpers.usersite;

import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.training.selenium.appmanager.ApplicationManager;
import ru.stqa.training.selenium.appmanager.helpers.HelperBase;
import ru.stqa.training.selenium.models.Product;

import java.net.MalformedURLException;

import static java.lang.Double.parseDouble;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

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

    public void addToCart() {
        wait.until(presenceOfElementLocated(By.cssSelector("h1.title")));
        int amountOfItemsInCart = Integer.parseInt(
                wd.findElement(By.cssSelector("span.quantity")).getText());

        if (isElementPresent(By.cssSelector("select[name=\"options[Size]\"]"))) {
            Select optionsSizeDropdown = new Select(wd.findElement(By.cssSelector("select[name=\"options[Size]\"]")));
            int numberOfOptions = optionsSizeDropdown.getOptions().size();
            optionsSizeDropdown.selectByIndex(RandomUtils.nextInt(1, numberOfOptions));
        }

        wd.findElement(By.cssSelector("button[name=\"add_cart_product\"]")).click();
        wait.until((WebDriver d) -> d.findElement(By.cssSelector("span.quantity"))
                .getText().equals(Integer.toString(amountOfItemsInCart + 1)));
    }
}
