package ru.stqa.training.selenium.appmanager.helpers.usersite;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.training.selenium.appmanager.helpers.HelperBase;
import ru.stqa.training.selenium.models.User;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class CreateAccountPageHelper extends HelperBase {
    public CreateAccountPageHelper(WebDriver wd) {
        super(wd);
    }

    public User createNewRandomUser(String pathToUsersFile) throws FileNotFoundException {
        waitCreateAccountPageIsOpened();

        String taxId = random(5, false, true);
        wd.findElement(By.cssSelector("[name=tax_id]")).click();
        wd.findElement(By.cssSelector("[name=tax_id]")).sendKeys(taxId);

        String company = random(nextInt(5, 10), true, true) + " "
                + random(nextInt(3, 6), true, true);
        wd.findElement(By.cssSelector("[name=company]")).click();
        wd.findElement(By.cssSelector("[name=company]")).sendKeys(company);

        String firstName = random(nextInt(5, 10), true, true);
        wd.findElement(By.cssSelector("[name=firstname]")).click();
        wd.findElement(By.cssSelector("[name=firstname]")).sendKeys(firstName);

        String lastName = random(nextInt(5, 10), true, true);
        wd.findElement(By.cssSelector("[name=lastname]")).click();
        wd.findElement(By.cssSelector("[name=lastname]")).sendKeys(lastName);

        String address1 = random(nextInt(5, 11), true, true) + ", "
                + random(nextInt(1, 5), false, true);
        wd.findElement(By.cssSelector("[name=address1]")).click();
        wd.findElement(By.cssSelector("[name=address1]")).sendKeys(address1);

        String address2 = random(nextInt(5, 11), true, true) + ", "
                + random(nextInt(1, 5), false, true);
        wd.findElement(By.cssSelector("[name=address2]")).click();
        wd.findElement(By.cssSelector("[name=address2]")).sendKeys(address2);

        String postcode = random(4, false, true);
        wd.findElement(By.cssSelector("[name=postcode]")).click();
        wd.findElement(By.cssSelector("[name=postcode]")).sendKeys(postcode);

        String city = random(nextInt(5, 11), true, true);
        wd.findElement(By.cssSelector("[name=city]")).click();
        wd.findElement(By.cssSelector("[name=city]")).sendKeys(city);

        Select countries = new Select(wd.findElement(By.cssSelector(".select2-hidden-accessible")));
        int randomCountryIndex = nextInt(1, countries.getOptions().size() - 1);
        WebElement randomCountry = countries.getOptions().get(randomCountryIndex);
        String country = randomCountry.getAttribute("innerText");
        countries.selectByIndex(randomCountryIndex);
        wait.until(elementToBeSelected(randomCountry));

        wait.until(elementToBeClickable(By.cssSelector("select[name=zone_code]")));
        Select zones = new Select(wd.findElement(By.cssSelector("select[name=zone_code]")));
        String zone;
        if (zones.getOptions().size() > 0) {
            int randomZoneIndex = nextInt(0, zones.getOptions().size() - 1);
            WebElement randomZone = zones.getOptions().get(randomZoneIndex);
            zone = randomZone.getAttribute("innerText");
            zones.selectByIndex(randomZoneIndex);
            wait.until(elementToBeSelected(randomZone));
        } else {
            zone = null;
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToUsersFile));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<User> users = gson.fromJson(bufferedReader, new TypeToken<List<User>>() {
        }.getType());
        int indexOfLastUser = users.size() - 1;
        User lastUser = users.get(indexOfLastUser);
        int indexOfPlus = lastUser.getEmail().indexOf("+");
        int indexOfAt = lastUser.getEmail().indexOf("@");
        int emailCounter = Integer.parseInt(lastUser.getEmail().substring(indexOfPlus + 1, indexOfAt)) + 1;
        String email = "rufjtigk+" + emailCounter + "@gmail.com";
        wd.findElement(By.cssSelector("[name=email]")).click();
        wd.findElement(By.cssSelector("[name=email]")).sendKeys(email);

        String phone = random(10, false, true);
        wd.findElement(By.cssSelector("[name=phone]")).click();
        wd.findElement(By.cssSelector("[name=phone]")).sendKeys(phone);

        boolean newsletter = RandomUtils.nextBoolean();
        if (wd.findElement(By.cssSelector("[name=newsletter]")).getAttribute("checked").equals("true")) {
            wd.findElement(By.cssSelector("[name=newsletter]")).click();
        }
        if (newsletter) {
            wd.findElement(By.cssSelector("[name=newsletter]")).click();
        }

        String password = random(nextInt(8, 11), true, true);
        wd.findElement(By.cssSelector("[name=password]")).click();
        wd.findElement(By.cssSelector("[name=password]")).sendKeys(password);
        wd.findElement(By.cssSelector("[name=confirmed_password]")).click();
        wd.findElement(By.cssSelector("[name=confirmed_password]")).sendKeys(password);

        wd.findElement(By.cssSelector("[name=create_account]")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("#box-account a[href*=logout]")));

        User newUser = new User()
                .withTaxId(taxId)
                .withCompany(company)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withAddress1(address1)
                .withAddress2(address2)
                .withPostcode(postcode)
                .withCity(city)
                .withCountry(country)
                .withZone(zone)
                .withEmail(email)
                .withPhone(phone)
                .withNewsletter(newsletter)
                .withPassword(password);

        users.add(newUser);

        try (FileWriter writer = new FileWriter(System.getProperty("user.dir") + "/src/test/resources/users.json")) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newUser;
    }

    private void waitCreateAccountPageIsOpened() {
        wait.until(presenceOfElementLocated(By.cssSelector("#create-account")));
    }
}
