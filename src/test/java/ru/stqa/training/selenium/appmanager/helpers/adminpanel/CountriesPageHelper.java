package ru.stqa.training.selenium.appmanager.helpers.adminpanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.stqa.training.selenium.appmanager.helpers.HelperBase;
import ru.stqa.training.selenium.models.Country;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

public class CountriesPageHelper extends HelperBase {
    public CountriesPageHelper(WebDriver wd) {
        super(wd);
    }

    public List<Country> allCountries() {
        List<Country> countriesJava = new ArrayList<>();
        wait.until(visibilityOfAllElementsLocatedBy(By.cssSelector(".row")));
        List<WebElement> countriesWeb = wd.findElements(By.cssSelector(".row"));

        for (WebElement country : countriesWeb) {
            String name = country.findElement(By.cssSelector("td:nth-child(5)")).getText();
            int zonesAmount = Integer.parseInt(country.findElement(By.cssSelector("td:nth-child(6)")).getText());

            countriesJava.add(new Country().withName(name).withZonesAmount(zonesAmount));
        }

        return countriesJava;
    }
}
