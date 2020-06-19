package ru.stqa.training.selenium.tests.homework4;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.training.selenium.models.Country;
import ru.stqa.training.selenium.tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
}
