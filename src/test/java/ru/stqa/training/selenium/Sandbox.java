package ru.stqa.training.selenium;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import ru.stqa.training.selenium.appmanager.ApplicationManager;
import ru.stqa.training.selenium.models.Country;
import ru.stqa.training.selenium.models.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Sandbox {

    public static void main(String[] args) throws IOException {
        List<Country> listOriginal = new ArrayList<>();
        listOriginal.add(new Country().withName("Aqwer").withZonesAmount(1));
        listOriginal.add(new Country().withName("Cqwer").withZonesAmount(2));
        listOriginal.add(new Country().withName("Bqwer").withZonesAmount(3));

        List<Country> listSorted = new ArrayList<>(listOriginal);
        listSorted.sort(Comparator.comparing(Country::getName));
        assertThat(listOriginal, equalTo(listSorted));
    }
}
