package ru.stqa.training.selenium.tests.homework7;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.training.selenium.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PageOpenedInNewWindowTest extends TestBase {

    @BeforeClass
    public void ensurePreconditions() {
        app.goTo().adminPanel();
        app.goTo().countriesTab();
        app.countriesPage().openRandomCountry();
    }

    @Test (alwaysRun = true)
    public void testISOAlpha2PageOpenedInNewWindow() {
        app.editCountryPage().openISOAlpha2Page();
        String pageTitle = app.getDriver().getTitle();
        assertThat(pageTitle, equalTo("ISO 3166-1 alpha-2 - Wikipedia"));
    }

    @Test (alwaysRun = true)
    public void testISOAlpha3PageOpenedInNewWindow() {
        app.editCountryPage().openISOAlpha3Page();
        String pageTitle = app.getDriver().getTitle();
        assertThat(pageTitle, equalTo("ISO 3166-1 alpha-3 - Wikipedia"));
    }

    @Test (alwaysRun = true)
    public void testRegularExpressionPageOpenedInNewWindowForTaxIDFormatField() {
        app.editCountryPage().openRegularExpressionPageNearTaxIDFormatFiled();
        String pageTitle = app.getDriver().getTitle();
        assertThat(pageTitle, equalTo("Regular expression - Wikipedia"));
    }

    @Test (alwaysRun = true)
    public void testAddressFormatsPageOpenedInNewWindow() {
        app.editCountryPage().openAddressFormatsPage();
        String pageTitle = app.getDriver().getTitle();
        assertThat(pageTitle,
                equalTo("International Address Format Validator: Verify Mailing Formats | Informatica"));
    }

    @Test (alwaysRun = true)
    public void testRegularExpressionPageOpenedInNewWindowForPostcodeFormatField() {
        app.editCountryPage().openRegularExpressionPageNearPostcodeFormatField();
        String pageTitle = app.getDriver().getTitle();
        assertThat(pageTitle, equalTo("Regular expression - Wikipedia"));
    }

    @Test (alwaysRun = true)
    public void testListOfCountriesPageOpenedInNewWindow() {
        app.editCountryPage().openListOfCountriesPage();
        String pageTitle = app.getDriver().getTitle();
        assertThat(pageTitle, equalTo("List of countries and capitals with currency and language - Wikipedia"));
    }

    @Test (alwaysRun = true)
    public void testListOfCountryCallingCodesPageOpenedInNewWindow() {
        app.editCountryPage().openListOfCountryCallingCodesPage();
        String pageTitle = app.getDriver().getTitle();
        assertThat(pageTitle, equalTo("List of country calling codes - Wikipedia"));
    }

    @AfterMethod(alwaysRun = true)
    public void goBackToMainWindow() {
        if (app.getNumberOfWindows() > 1) {
            app.goTo().closeCurrentWindowAndGoBackToMainOne();
        }
    }
}
