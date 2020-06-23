package ru.stqa.training.selenium.tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.Color;
import org.testng.annotations.Test;
import ru.stqa.training.selenium.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;
import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class Sandbox {



    public static void main(String[] args) throws FileNotFoundException {
        String path = System.getProperty("user.dir") + "/src/test/resources/users.json";

    }

}
