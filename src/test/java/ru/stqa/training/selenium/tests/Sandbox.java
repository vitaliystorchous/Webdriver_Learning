package ru.stqa.training.selenium.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.Test;
import ru.stqa.training.selenium.generators.Generator;
import ru.stqa.training.selenium.models.Product;
import ru.stqa.training.selenium.models.Status;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class Sandbox extends TestBase {

    @Test
    public void test() throws SQLException {
        app.goTo().adminPanel();
        app.goTo().catalogPage();
        app.catalogPage().openCategory("Subcategory");
    }
}