package ru.stqa.training.selenium.tests.homework5;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import ru.stqa.training.selenium.appmanager.helpers.database.DBHelper;
import ru.stqa.training.selenium.generators.Generator;
import ru.stqa.training.selenium.models.User;
import ru.stqa.training.selenium.tests.TestBase;

import java.io.IOException;
import java.sql.SQLException;

import static org.testng.Assert.assertTrue;

public class RegisterNewUserTest extends TestBase {

    @Test
    public void testNewUserCreation() throws IOException, SQLException {
        assertTrue(DBHelper.disableCaptcha(),"Captcha is not disabled!");

        app.homepage().createNewCustomer();

        Generator generator = new Generator();
        User newRandomUser = generator.generateRandomUser();

        app.createAccountPage().createUser(newRandomUser);
        app.homepage().logout();
        app.homepage().login(newRandomUser);
        assertTrue(app.homepage().userIsLoggedIn(), "The user is not logged in");
    }

    @AfterTest (alwaysRun = true)
    public void enableCapture() throws SQLException {
        assertTrue(DBHelper.enableCapture(), "Captcha is not enabled!");
    }
}
