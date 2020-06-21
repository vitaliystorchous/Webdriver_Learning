package ru.stqa.training.selenium.tests.homework4;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.training.selenium.models.Product;
import ru.stqa.training.selenium.tests.TestBase;

import java.util.List;

import static java.lang.Integer.parseInt;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

public class RightProductOpenedTest extends TestBase {

    @Test
    public void testRightProductIsOpened() {
        app.goTo().homepage();
        Product randomProduct = app.homepage().openRandomProductInCampaignsSection();
        Product openedProduct = app.productPage().getProduct();
        assertThat(openedProduct, equalTo(randomProduct));
    }
}
