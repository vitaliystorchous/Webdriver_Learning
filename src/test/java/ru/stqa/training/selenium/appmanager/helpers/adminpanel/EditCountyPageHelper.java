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
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
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

    public void openISOAlpha2Page() {
        Set<String> oldWindows = wd.getWindowHandles();
        wait.until(elementToBeClickable(By.xpath("//td[@id=\"content\"]//tr[contains(., \"(ISO 3166-1 alpha-2)\")]//a")))
                .click();
        String newWindow = wait.until(anyWindowOtherThan(oldWindows));
        wd.switchTo().window(newWindow);
    }

    public void openISOAlpha3Page() {
        Set<String> oldWindows = wd.getWindowHandles();
        wait.until(elementToBeClickable(By.xpath("//td[@id=\"content\"]//tr[contains(., \"(ISO 3166-1 alpha-3)\")]//a")))
                .click();
        String newWindow = wait.until(anyWindowOtherThan(oldWindows));
        wd.switchTo().window(newWindow);
    }

    public void openRegularExpressionPageNearTaxIDFormatFiled() {
        Set<String> oldWindows = wd.getWindowHandles();
        wait.until(elementToBeClickable(By.xpath("//td[@id=\"content\"]//tr[contains(., \"Tax ID Format\")]//a")))
                .click();
        String newWindow = wait.until(anyWindowOtherThan(oldWindows));
        wd.switchTo().window(newWindow);
    }

    public void openAddressFormatsPage() {
        Set<String> oldWindows = wd.getWindowHandles();
        wait.until(elementToBeClickable(By.xpath("//td[@id=\"content\"]//tr[contains(., \"Address Format\")]//a[@target]")))
                .click();
        String newWindow = wait.until(anyWindowOtherThan(oldWindows));
        wd.switchTo().window(newWindow);
    }

    public void openRegularExpressionPageNearPostcodeFormatField() {
        Set<String> oldWindows = wd.getWindowHandles();
        wait.until(elementToBeClickable(By.xpath("//td[@id=\"content\"]//tr[contains(., \"Postcode Format\")]//a[@target]")))
                .click();
        String newWindow = wait.until(anyWindowOtherThan(oldWindows));
        wd.switchTo().window(newWindow);
    }

    public void openListOfCountriesPage() {
        Set<String> oldWindows = wd.getWindowHandles();
        wait.until(elementToBeClickable(By.xpath("//td[@id=\"content\"]//tr[contains(., \"Currency Code\")]//a[@target]")))
                .click();
        String newWindow = wait.until(anyWindowOtherThan(oldWindows));
        wd.switchTo().window(newWindow);
    }

    public void openListOfCountryCallingCodesPage() {
        Set<String> oldWindows = wd.getWindowHandles();
        wait.until(elementToBeClickable(By.xpath("//td[@id=\"content\"]//tr[contains(., \"Phone Country Code\")]//a[@target]")))
                .click();
        String newWindow = wait.until(anyWindowOtherThan(oldWindows));
        wd.switchTo().window(newWindow);
    }
}
