package ru.stqa.training.selenium.tests.homework4;

import org.testng.annotations.Test;
import ru.stqa.training.selenium.models.Country;
import ru.stqa.training.selenium.models.Zone;
import ru.stqa.training.selenium.tests.TestBase;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CountriesSortingTest extends TestBase {

    @Test
    public void testCountriesAreSortedAlphabetically() {
        app.goTo().adminPanel();
        app.goTo().countriesTab();
        List<Country> countriesOriginal = app.countriesPage().allCountries();
        List<Country> countriesSorted = new ArrayList<>(countriesOriginal);
        countriesSorted.sort(comparing(Country::getName));
        assertThat(countriesOriginal, equalTo(countriesSorted));
    }

    @Test
    public void testZonesOfCountriesAreSortedAlphabetically() {
        app.goTo().adminPanel();
        app.goTo().countriesTab();
        List<Country> countries = app.countriesPage().allCountries();

        for (Country country : countries) {
            if (country.getZonesAmount() > 0) {
                app.goTo().countriesTab();
                app.countriesPage().openCountry(country.getName());
                List<Zone> zones = app.editCountryPage().allZones();
                List<Zone> zonesSorted = new ArrayList<>(zones);
                zonesSorted.sort(comparing(Zone::getName));
                assertThat(zones, equalTo(zonesSorted));
            }
        }
    }
}
