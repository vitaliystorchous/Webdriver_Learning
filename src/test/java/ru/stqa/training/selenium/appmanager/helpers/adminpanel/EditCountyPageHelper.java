package ru.stqa.training.selenium.appmanager.helpers.adminpanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.stqa.training.selenium.appmanager.ApplicationManager;
import ru.stqa.training.selenium.appmanager.helpers.HelperBase;
import ru.stqa.training.selenium.models.Zone;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

public class EditCountyPageHelper extends HelperBase {

    public EditCountyPageHelper(ApplicationManager app) {
        super(app);
    }

    public List<Zone> allZones() {
        List<Zone> zonesJava = new ArrayList<>();
        wait.until(visibilityOfAllElementsLocatedBy(By.cssSelector("#table-zones tr")));
        List<WebElement> zonesWeb = wd.findElements(By.cssSelector("#table-zones tr"));

        for (int i = 0; i < zonesWeb.size(); i++) {
            if (i == 0 || i == zonesWeb.size() - 1) { continue; }

            String name = zonesWeb.get(i).findElement(By.cssSelector("td:nth-child(3)")).getText();
            zonesJava.add(new Zone().withName(name));
        }

        return zonesJava;
    }
}
