package ru.stqa.training.selenium.tests.homework4;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.annotations.Test;
import ru.stqa.training.selenium.tests.TestBase;

import java.util.List;

import static java.lang.Integer.parseInt;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

public class PricesStylesTest extends TestBase {

    @Test
    public void testRegularPricesStylesInCampaignSection() {
        app.goTo().homepage();
        List<WebElement> regularPrices = app.homepage().allRegularPrices();

        for (WebElement price : regularPrices) {
            String color = price.getCssValue("color");
            assertThat(Color.fromString(color).asHex(), equalTo("#777777"));
            assertThat(price.getCssValue("text-decoration"), containsString("line-through"));
            assertThat(price.getCssValue("font-size"), equalTo("14.4px"));
        }
    }

    @Test
    public void testCampaignPricesStylesInCampaignSection() {
        app.goTo().homepage();
        List<WebElement> campaignPrices = app.homepage().allCampaignPrices();

        for (WebElement price : campaignPrices) {
            String color = price.getCssValue("color");
            assertThat(Color.fromString(color).asHex(), equalTo("#cc0000"));
            assertTrue(price.getCssValue("font-weight").contains("bold")
                    || (parseInt(price.getCssValue("font-weight")) >= 700));
            assertThat(price.getCssValue("font-size"), equalTo("18px"));
        }
    }

    @Test
    public void testRegularPriceStyleOnCampaignProductPage() {
        app.goTo().homepage();
        app.homepage().openRandomProductInCampaignsSection();
        WebElement regularPrice = app.productPage().regularPrice();

        String color = regularPrice.getCssValue("color");
        assertThat(Color.fromString(color).asHex(), equalTo("#666666"));
        assertThat(regularPrice.getCssValue("text-decoration"), containsString("line-through"));
        assertThat(regularPrice.getCssValue("font-size"), equalTo("16px"));
    }

    @Test
    public void testCampaignPriceStyleOnCampaignProductPage() {
        app.goTo().homepage();
        app.homepage().openRandomProductInCampaignsSection();
        WebElement campaignPrice = app.productPage().campaignPrice();

        String color = campaignPrice.getCssValue("color");
        assertThat(Color.fromString(color).asHex(), equalTo("#cc0000"));
        assertTrue(campaignPrice.getCssValue("font-weight").contains("bold")
                || (parseInt(campaignPrice.getCssValue("font-weight")) >= 700));
        assertThat(campaignPrice.getCssValue("font-size"), equalTo("22px"));
    }
}
