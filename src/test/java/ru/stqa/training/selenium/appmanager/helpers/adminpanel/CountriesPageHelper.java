package ru.stqa.training.selenium.appmanager.helpers.adminpanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.stqa.training.selenium.appmanager.helpers.HelperBase;
import ru.stqa.training.selenium.models.Country;
import ru.stqa.training.selenium.models.Country.Status;

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
            Status status;
            WebElement statusIndicator = country.findElement(By.cssSelector("td:nth-child(2) i"));
            String color = statusIndicator.getCssValue("color");
            switch (statusIndicator.getCssValue("color")) {
                case "rgba(153, 204, 102, 1)": status = Status.ENABLED; break;
                case "rgba(255, 102, 102, 1)": status = Status.DISABLED; break;
                default: status = null; break;
            }

            countriesJava.add(new Country().withName(name).withZonesAmount(zonesAmount).withStatus(status));
        }

        return countriesJava;
    }

    public void openCountry(String name) {
        wd.findElement(By.linkText(name)).click();
    }
}
