package ru.stqa.training.selenium.appmanager.helpers.usersite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.stqa.training.selenium.appmanager.ApplicationManager;
import ru.stqa.training.selenium.appmanager.helpers.HelperBase;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class CheckoutPageHelper extends HelperBase {
    public CheckoutPageHelper(ApplicationManager app) {
        super(app);
    }

    public void removeAllItemsFromCart() {
        if (isElementPresent(By.cssSelector("#box-checkout-summary"))) {
            List<WebElement> products = wd.findElements(By.cssSelector("td.item"));

            for (int i = products.size(); i >= 0; i--) {
                if (isElementPresent(By.cssSelector("ul.shortcuts a"))) {
                    wd.findElement(By.cssSelector("ul.shortcuts a")).click();
                }
                wd.findElement(By.cssSelector("button[name=\"remove_cart_item\"]")).click();
                int numberOfProductsAfterRemoving = i;
                wait.until((WebDriver d) ->
                        d.findElements(By.cssSelector("td.item")).size() == numberOfProductsAfterRemoving);
            }
        }
    }

    public boolean isCartEmpty() {
        return isElementPresent(By.xpath("//p[.=\"There are no items in your cart.\"]"));
    }
}
