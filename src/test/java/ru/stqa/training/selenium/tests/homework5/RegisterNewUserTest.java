package ru.stqa.training.selenium.tests.homework5;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.training.selenium.models.User;
import ru.stqa.training.selenium.tests.TestBase;

import java.io.FileNotFoundException;

public class RegisterNewUserTest extends TestBase {

    @Test
    public void testNewUserCreation() throws FileNotFoundException {
        app.homepage().createNewCustomer();
        User newRandomUser = app.createAccountPage()
                .createNewRandomUser(System.getProperty("user.dir") + "/src/test/resources/users.json");
        app.homepage().logout();
        app.homepage().login(newRandomUser);
        Assert.assertTrue(app.homepage().userIsLoggedIn(), "The user is not logged in");
    }
}
