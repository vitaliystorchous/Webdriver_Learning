package ru.stqa.training.selenium;

import ru.stqa.training.selenium.appmanager.ApplicationManager;
import ru.stqa.training.selenium.models.Product;

import java.io.IOException;
import java.util.List;

public class Sandbox {

    public static void main(String[] args) throws IOException {
        ApplicationManager app = new ApplicationManager("chrome");
        app.init();
        List<Product> products = app.homepage().allProducts();
        System.out.println(products.size());
        System.out.println(products);
        app.stop();
    }
}
