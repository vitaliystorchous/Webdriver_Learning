package ru.stqa.training.selenium.appmanager.helpers.usersite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.stqa.training.selenium.appmanager.ApplicationManager;
import ru.stqa.training.selenium.appmanager.helpers.HelperBase;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class CheckoutPageHelper extends HelperBase {

    @FindBy(css = "td.item")
    public List<WebElement> products;

    public int getNumberOfProducts() {
        return products.size();
    }

    @FindBy(css = "ul.shortcuts a")
    public List<WebElement> productsShortcuts;

    public CheckoutPageHelper clickProductShortcut(int productIndex) {
        productsShortcuts.get(productIndex).click();
        return this;
    }

    @FindBy(xpath = "//p[.=\"There are no items in your cart.\"]")
    public WebElement emptyCartMessage;

    @FindBy(css = "button[name=\"remove_cart_item\"]")
    public List<WebElement> removeProductButtons;

    public CheckoutPageHelper clickRemoveProductButton(int productIndex) {
        removeProductButtons.get(productIndex).click();
        return this;
    }

    @FindBy(css = "#box-checkout-summary")
    public WebElement orderSummaryTable;

    public CheckoutPageHelper(ApplicationManager app) {
        super(app);
        PageFactory.initElements(wd, this);
    }

    public void removeAllItemsFromCart() {
        if (isElementPresent(orderSummaryTable)) {

            for (int i = products.size(); i >= 0; i--) {
                if (areElementsPresent(productsShortcuts)) {
                    clickProductShortcut(0);
                }
                clickRemoveProductButton(0);
                int numberOfProductsAfterRemoving = i;
                wait.until((WebDriver d) -> products.size() == numberOfProductsAfterRemoving);
            }
        }
    }

    public boolean isCartEmpty() {
        return isElementPresent(emptyCartMessage);
    }
}
