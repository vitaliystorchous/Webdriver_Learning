package ru.stqa.training.selenium.tests.homework5;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.training.selenium.generators.Generator;
import ru.stqa.training.selenium.models.User;
import ru.stqa.training.selenium.tests.TestBase;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterNewUserTest extends TestBase {

    @Test
    public void testNewUserCreation() throws IOException, SQLException {
        app.homepage().createNewCustomer();

        Generator generator = new Generator();
        User newRandomUser = generator.generateRandomUser();

        app.createAccountPage().createUser(newRandomUser);
        app.homepage().logout();
        app.homepage().login(newRandomUser);
        Assert.assertTrue(app.homepage().userIsLoggedIn(), "The user is not logged in");
    }
}
