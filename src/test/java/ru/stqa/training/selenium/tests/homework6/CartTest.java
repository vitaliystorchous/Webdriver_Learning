package ru.stqa.training.selenium.tests.homework6;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.training.selenium.tests.TestBase;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CartTest extends TestBase {

    @Test
    public void testCart() {

        for (int i = 0; i < 3; i++) {
            app.goTo().homepage();
            app.homepage().openRandomProduct();
            app.productPage().addToCart();
        }

        app.goTo().checkoutPage();

        assertFalse(app.checkoutPage().isCartEmpty(),
                "Cart is empty after adding products to it");

        app.checkoutPage().removeAllItemsFromCart();

        assertTrue(app.checkoutPage().isCartEmpty(),
                "Cart is not empty after removing all products from it");
    }
}
