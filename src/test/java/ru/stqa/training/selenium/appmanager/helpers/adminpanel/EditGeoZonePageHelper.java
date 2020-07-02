package ru.stqa.training.selenium.appmanager.helpers.adminpanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.training.selenium.appmanager.ApplicationManager;
import ru.stqa.training.selenium.appmanager.helpers.HelperBase;
import ru.stqa.training.selenium.models.Zone;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

public class EditGeoZonePageHelper extends HelperBase {

    public EditGeoZonePageHelper(ApplicationManager app) {
        super(app);
    }

    public List<Zone> allZones() {
        List<Zone> zonesJava = new ArrayList<>();
        wait.until(visibilityOfAllElementsLocatedBy(By.cssSelector("#table-zones tr")));
        List<WebElement> zonesWeb = wd.findElements(By.cssSelector("#table-zones tr"));

        for (int i = 0; i < zonesWeb.size(); i++) {
            if (i == 0 || i == zonesWeb.size() - 1) { continue; }

            WebElement zoneDropdown = zonesWeb.get(i).findElement(By.cssSelector("td:nth-of-type(3)"));
            WebElement selectedValue = zoneDropdown.findElement(By.cssSelector("[selected=selected]"));
            String name = selectedValue.getText();

            zonesJava.add(new Zone().withName(name));
        }

        return zonesJava;
    }
}
