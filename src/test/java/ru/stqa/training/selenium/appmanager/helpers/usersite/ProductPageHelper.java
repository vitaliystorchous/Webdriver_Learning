package ru.stqa.training.selenium.appmanager.helpers.usersite;

import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.training.selenium.appmanager.ApplicationManager;
import ru.stqa.training.selenium.appmanager.helpers.HelperBase;
import ru.stqa.training.selenium.models.Product;

import java.net.MalformedURLException;

import static java.lang.Double.parseDouble;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class ProductPageHelper extends HelperBase {

    @FindBy(css = "h1.title")
    public WebElement productTitle;

    @FindBy(css = "span.quantity")
    public WebElement counterOfItemsInCart;

    @FindBy(css = "select[name=\"options[Size]\"]")
    public WebElement sizeDropdown;

    public ProductPageHelper selectRandomSize() {
        Select sizeSelect = new Select(sizeDropdown);
        int amountOfOptions = sizeSelect.getOptions().size();
        sizeSelect.selectByIndex(RandomUtils.nextInt(1, amountOfOptions));
        return this;
    }

    @FindBy(css = "button[name=\"add_cart_product\"]")
    public WebElement addToCartButton;

    public ProductPageHelper clickAddToCartButton() {
        addToCartButton.click();
        return this;
    }


    public ProductPageHelper(ApplicationManager app) {
        super(app);
        PageFactory.initElements(wd, this);
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
        wait.until(visibilityOf(productTitle));
        int amountOfItemsInCart = Integer.parseInt(
                counterOfItemsInCart.getText());

        if (isElementPresent(sizeDropdown)) {
            selectRandomSize();
        }

        clickAddToCartButton();
        wait.until(textToBePresentInElement(counterOfItemsInCart, Integer.toString(amountOfItemsInCart + 1)));
    }
}
