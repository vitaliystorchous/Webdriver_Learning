package ru.stqa.training.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.Color;
import org.testng.annotations.Test;

import java.io.IOException;

import static java.lang.Double.parseDouble;

public class Sandbox extends TestBase {

    @Test
    public void test() {
        String color = app.getDriver().findElement(By.cssSelector("#box-campaigns .regular-price"))
                .getCssValue("color");
        System.out.println(color);
        String hex = Color.fromString(color).asHex();
        System.out.println(hex);
    }
}
