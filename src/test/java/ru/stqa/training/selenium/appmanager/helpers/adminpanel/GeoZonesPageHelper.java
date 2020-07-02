package ru.stqa.training.selenium.appmanager.helpers.adminpanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.stqa.training.selenium.appmanager.ApplicationManager;
import ru.stqa.training.selenium.appmanager.helpers.HelperBase;
import ru.stqa.training.selenium.models.Country;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

public class GeoZonesPageHelper extends HelperBase {

    public GeoZonesPageHelper(ApplicationManager app) {
        super(app);
    }

    public void openCountry(String name) {
        wd.findElement(By.linkText(name)).click();
    }

    public List<Country> allCountries() {
        List<Country> countriesJava = new ArrayList<>();
        wait.until(visibilityOfAllElementsLocatedBy(By.cssSelector(".row")));
        List<WebElement> countriesWeb = wd.findElements(By.cssSelector(".row"));

        for (WebElement country : countriesWeb) {
            String name = country.findElement(By.cssSelector("td:nth-child(3)")).getText();
            int zonesAmount = parseInt(country.findElement(By.cssSelector("td:nth-child(4)")).getText());

            countriesJava.add(new Country().withName(name).withZonesAmount(zonesAmount));
        }

        return countriesJava;
    }
}
