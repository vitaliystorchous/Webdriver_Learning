package ru.stqa.training.selenium.appmanager.helpers.adminpanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.stqa.training.selenium.appmanager.ApplicationManager;
import ru.stqa.training.selenium.appmanager.helpers.HelperBase;

import java.net.MalformedURLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class CatalogPageHelper extends HelperBase {

    public CatalogPageHelper(ApplicationManager app) {
        super(app);
    }

    public void openCategory(String defaultCategory) throws SQLException {
        wait.until(presenceOfElementLocated(By.cssSelector("[name=catalog_form]")));

        List<String> categories = new ArrayList<>();
        categories.add(defaultCategory);

        String currentCategory = defaultCategory;
        int categoryId = 1;

        Connection conn = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/litecart?user=root&password=&serverTimezone=UTC");
        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        while (categoryId > 0) {
            ResultSet rsWithId = st.executeQuery("SELECT lc_categories_info.name, lc_categories_info.category_id, lc_categories.id, lc_categories.parent_id\n" +
                    "FROM lc_categories_info\n" +
                    "INNER JOIN lc_categories ON lc_categories_info.category_id = lc_categories.id\n" +
                    "WHERE lc_categories_info.name=\"" + currentCategory + "\"");
            rsWithId.next();
            categoryId = rsWithId.getInt("parent_id");
            rsWithId.close();

            if (categoryId > 0) {
                ResultSet rsWithName = st.executeQuery("SELECT lc_categories_info.name, lc_categories_info.category_id, lc_categories.id, lc_categories.parent_id\n" +
                        "FROM lc_categories_info\n" +
                        "INNER JOIN lc_categories ON lc_categories_info.category_id = lc_categories.id\n" +
                        "WHERE lc_categories_info.category_id =" + categoryId);
                rsWithName.next();
                currentCategory = rsWithName.getString("name");
                categories.add(currentCategory);
                rsWithName.close();
            }
        }
        st.close();
        conn.close();

        System.out.println(categories);
        for (int i = categories.size() - 1; i >= 0; i--) {
            wait.until(elementToBeClickable(By.linkText(categories.get(i)))).click();
        }
    }

    public void openProduct(String name) {
        wait.until(elementToBeClickable(By.linkText(name))).click();
    }
}
