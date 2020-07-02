package ru.stqa.training.selenium.appmanager.helpers.usersite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.training.selenium.appmanager.ApplicationManager;
import ru.stqa.training.selenium.appmanager.helpers.HelperBase;
import ru.stqa.training.selenium.models.User;

import java.net.MalformedURLException;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class CreateAccountPageHelper extends HelperBase {

    public CreateAccountPageHelper(ApplicationManager app) {
        super(app);
    }

    private void waitCreateAccountPageIsOpened() {
        wait.until(presenceOfElementLocated(By.cssSelector("#create-account")));
    }

    public void createUser(User user) {
        waitCreateAccountPageIsOpened();

        type(By.cssSelector("[name=tax_id]"), user.getTaxId());
        type(By.cssSelector("[name=company]"), user.getCompany());
        type(By.cssSelector("[name=firstname]"), user.getFirstName());
        type(By.cssSelector("[name=lastname]"), user.getLastName());
        type(By.cssSelector("[name=address1]"), user.getAddress1());
        type(By.cssSelector("[name=address2]"), user.getAddress2());
        type(By.cssSelector("[name=postcode]"), user.getPostcode());
        type(By.cssSelector("[name=city]"), user.getCity());

        Select countries = new Select(wd.findElement(By.cssSelector(".select2-hidden-accessible")));
        countries.selectByVisibleText(user.getCountry());

        wait.until(elementToBeClickable(By.cssSelector("select[name=zone_code]")));
        if (! user.getZone().equals("")) {
            Select zones = new Select(wd.findElement(By.cssSelector("select[name=zone_code]")));
            zones.selectByVisibleText(user.getZone());
        }

        type(By.cssSelector("[name=email]"), user.getEmail());
        type(By.cssSelector("[name=phone]"), user.getPhone());

        if (wd.findElement(By.cssSelector("[name=newsletter]")).getAttribute("checked").equals("true")) {
            click(By.cssSelector("[name=newsletter]"));
        }
        if (user.isNewsletter()) {
            click(By.cssSelector("[name=newsletter]"));
        }

        type(By.cssSelector("[name=password]"), user.getPassword());
        type(By.cssSelector("[name=confirmed_password]"), user.getPassword());

        click(By.cssSelector("[name=create_account]"));
        wait.until(presenceOfElementLocated(By.cssSelector("#box-account a[href*=logout]")));
    }
}
