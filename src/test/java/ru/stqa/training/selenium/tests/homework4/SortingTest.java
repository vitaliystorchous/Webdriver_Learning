package ru.stqa.training.selenium.tests.homework4;

import org.testng.annotations.Test;
import ru.stqa.training.selenium.models.Country;
import ru.stqa.training.selenium.models.Country.Status;
import ru.stqa.training.selenium.models.Zone;
import ru.stqa.training.selenium.tests.TestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SortingTest extends TestBase {

    @Test
    public void testCountriesAreSortedAlphabetically() {
        app.goTo().adminPanel();
        app.goTo().countriesTab();
        List<Country> countriesOriginal = app.countriesPage().allCountries();

        List<Country> countriesEnabled = countriesOriginal.stream()
                .filter(c -> c.getStatus().equals(Status.ENABLED))
                .collect(Collectors.toList());
        List<Country> countriesDisabled = countriesOriginal.stream()
                .filter(c -> c.getStatus().equals(Status.DISABLED))
                .collect(Collectors.toList());

        countriesEnabled.sort(comparing(Country::getName, String::compareToIgnoreCase));
        countriesDisabled.sort(comparing(Country::getName, String::compareToIgnoreCase));

        List<Country> countriesSorted = new ArrayList<>();
        countriesSorted.addAll(countriesEnabled);
        countriesSorted.addAll(countriesDisabled);

        assertThat(countriesOriginal, equalTo(countriesSorted));
    }

    @Test
    public void testZonesOfCountriesAreSortedAlphabeticallyOnEditCountryPage() {
        app.goTo().adminPanel();
        app.goTo().countriesTab();
        List<Country> countries = app.countriesPage().allCountries();

        for (Country country : countries) {
            if (country.getZonesAmount() > 0) {
                app.goTo().countriesTab();
                app.countriesPage().openCountry(country.getName());
                List<Zone> zones = app.editCountryPage().allZones();
                List<Zone> zonesSorted = new ArrayList<>(zones);
                zonesSorted.sort(comparing(Zone::getName, String::compareToIgnoreCase));
                assertThat(zones, equalTo(zonesSorted));
            }
        }
    }

    @Test
    public void testZonesOfCountriesAreSortedAlphabeticallyOnEditGeoZonePage() {
        app.goTo().adminPanel();
        app.goTo().geoZonesTab();
        List<Country> countries = app.geoZonesPage().allCountries();

        for (Country country : countries) {
            app.goTo().geoZonesTab();
            app.geoZonesPage().openCountry(country.getName());
            List<Zone> zones = app.editGeoZonePage().allZones();
            List<Zone> zonesSorted= new ArrayList<>(zones);
            zonesSorted.sort(comparing(Zone::getName, String::compareToIgnoreCase));
            assertThat(zones, equalTo(zonesSorted));
        }
    }
}
