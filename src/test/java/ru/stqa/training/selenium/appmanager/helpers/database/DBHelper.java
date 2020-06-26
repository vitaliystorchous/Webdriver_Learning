package ru.stqa.training.selenium.appmanager.helpers.database;

import org.apache.commons.lang3.RandomUtils;

import java.sql.*;

public class DBHelper {

    public static boolean disableCaptcha() throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/litecart?user=root&password=&serverTimezone=UTC");
        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        ResultSet rsBefore = st.executeQuery("SELECT * FROM `lc_settings` WHERE `key` = 'captcha_enabled'");
        rsBefore.last();
        if (rsBefore.getRow() > 1) {
            System.out.println("More than one 'captcha_enabled' settings parameters are returned");
            rsBefore.close();
            st.close();
            conn.close();
            return false;
        }

        int toggleValueBefore = Integer.parseInt(rsBefore.getString("value"));
        if (toggleValueBefore == 0) {
            System.out.println("Captcha is already disabled");
            rsBefore.close();
            st.close();
            conn.close();
            return true;
        }

        st.executeUpdate("UPDATE `lc_settings` SET `value`=0 WHERE `key`='captcha_enabled'");
        ResultSet rsAfter = st.executeQuery("SELECT * FROM `lc_settings` WHERE `key` = 'captcha_enabled'");
        rsAfter.last();
        int toggleValueAfter = Integer.parseInt(rsAfter.getString("value"));

        rsAfter.close();
        st.close();
        conn.close();

        return toggleValueAfter == 0;
    }

    public static boolean enableCapture() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/litecart?user=root&password=&serverTimezone=UTC");
        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        ResultSet rsBefore = st.executeQuery("SELECT * FROM `lc_settings` WHERE `key` = 'captcha_enabled'");
        rsBefore.last();
        if (rsBefore.getRow() > 1) {
            System.out.println("More than one 'captcha_enabled' settings parameters are returned");
            rsBefore.close();
            st.close();
            conn.close();
            return false;
        }

        int toggleValueBefore = Integer.parseInt(rsBefore.getString("value"));
        if (toggleValueBefore > 0) {
            System.out.println("Captcha is already enabled");
            rsBefore.close();
            st.close();
            conn.close();
            return true;
        }

        st.executeUpdate("UPDATE `lc_settings` SET `value`=1 WHERE `key`='captcha_enabled'");
        ResultSet rsAfter = st.executeQuery("SELECT * FROM `lc_settings` WHERE `key` = 'captcha_enabled'");
        rsAfter.last();
        int toggleValueAfter = Integer.parseInt(rsAfter.getString("value"));

        rsAfter.close();
        st.close();
        conn.close();

        return toggleValueAfter > 0;
    }
}
