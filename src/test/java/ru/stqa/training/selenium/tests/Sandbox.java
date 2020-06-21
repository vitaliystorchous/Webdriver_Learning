package ru.stqa.training.selenium.tests;

import java.io.IOException;

import static java.lang.Double.parseDouble;

public class Sandbox {

    public static void main(String[] args) throws IOException {
        String price = "$12";
        System.out.println(parseDouble(price.substring(1)));
    }
}
