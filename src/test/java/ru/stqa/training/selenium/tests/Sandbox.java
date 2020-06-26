package ru.stqa.training.selenium.tests;

import ru.stqa.training.selenium.appmanager.helpers.database.DBHelper;
import ru.stqa.training.selenium.generators.Generator;
import ru.stqa.training.selenium.models.User;

import java.io.IOException;
import java.sql.SQLException;

public class Sandbox {



    public static void main(String[] args) throws IOException, SQLException {
        DBHelper.enableCapture();
    }

}
