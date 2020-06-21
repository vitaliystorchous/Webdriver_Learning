package ru.stqa.training.selenium.tests.homework4;

import org.testng.annotations.Test;
import ru.stqa.training.selenium.models.Product;
import ru.stqa.training.selenium.tests.TestBase;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RightProductOpenedTest extends TestBase {

    @Test
    public void testRightProductIsOpened() {
        Product randomProduct = app.homepage().openRandomProductInCampaignsSection();
        Product openedProduct = app.productPage().getProduct();
        assertThat(openedProduct, equalTo(randomProduct));
    }

    @Test
    public void testPriceStylesInCampaignSection() {

    }

    @Test
    public void testPriceStylesOnCampaignProductPage() {
        
    }
}
