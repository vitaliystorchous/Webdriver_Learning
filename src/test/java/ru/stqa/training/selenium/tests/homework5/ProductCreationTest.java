package ru.stqa.training.selenium.tests.homework5;

import org.testng.annotations.Test;
import ru.stqa.training.selenium.generators.Generator;
import ru.stqa.training.selenium.models.Product;
import ru.stqa.training.selenium.tests.TestBase;

import java.sql.SQLException;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductCreationTest extends TestBase {

    @Test
    public void testProductionCreation() throws SQLException {
        app.goTo().adminPanel();
        app.goTo().catalogPage();
        app.goTo().addNewProductPage();

        Product randomProduct = new Product().generateRandomProduct();

        app.addNewProductPage().createProduct(randomProduct);
        app.catalogPage().openCategory(randomProduct.getDefaultCategory());
        app.catalogPage().openProduct(randomProduct.getName());
        Product createdProduct = app.editProductPage().getProduct(true, true, false, true, false, false);
        Collections.sort(randomProduct.getCategories());
        Collections.sort(createdProduct.getCategories());
        assertThat(randomProduct, equalTo(createdProduct));
    }
}
