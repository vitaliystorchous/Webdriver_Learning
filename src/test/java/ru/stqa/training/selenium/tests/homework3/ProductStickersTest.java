package ru.stqa.training.selenium.tests.homework3;

import org.testng.annotations.Test;
import ru.stqa.training.selenium.models.Product;
import ru.stqa.training.selenium.tests.TestBase;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class ProductStickersTest extends TestBase {

    @Test
    public void testProductStickers() {
        List<Product> products = app.homepage().allProducts();
        assertTrue(products.stream().allMatch(p -> p.getSticker() != null),
                "Not all products contain stickers or unexpected sticker was found");
    }
}
