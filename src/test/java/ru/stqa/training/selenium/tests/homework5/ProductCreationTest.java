package ru.stqa.training.selenium.tests.homework5;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.training.selenium.generators.Generator;
import ru.stqa.training.selenium.models.Product;
import ru.stqa.training.selenium.tests.TestBase;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductCreationTest extends TestBase {

    @Test
    public void testProductionCreation() throws SQLException, IOException {
        app.goTo().adminPanel();
        app.goTo().catalogPage();
        app.goTo().addNewProductPage();

        Product randomProduct = new Product().generateRandomProduct(new File("src/test/resources/for_tests/ImagesForProductCreationTest/Image_For_Product_Creation.jpg"));

        app.addNewProductPage().createProduct(randomProduct);
        app.catalogPage().openCategory(randomProduct.getDefaultCategory());
        app.catalogPage().openProduct(randomProduct.getName());
        Product createdProduct = app.editProductPage().getProduct(
                true,
                true,
                false,
                true,
                false,
                false);

        Collections.sort(randomProduct.getCategories());
        Collections.sort(createdProduct.getCategories());
        Collections.sort(randomProduct.getProductGroups());
        Collections.sort(createdProduct.getProductGroups());

        Assert.assertEquals(randomProduct.withImage(new File("src/test/resources/for_tests/ImagesForProductCreationTest/Image_for_comparison.jpg")),
                createdProduct,
                "Entered data while creating the product does not match data of created product");
    }
}
