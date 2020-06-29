package ru.stqa.training.selenium.generators;

import org.apache.commons.lang3.RandomUtils;

import java.sql.*;
import java.util.*;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class GeneratorHelper {
    public String generateRandomTextWithSpaces(int amountOfWordsFrom, int amountOfWordsTo) {
        StringBuilder description = new StringBuilder(random(nextInt(3, 8), true, true));
        for (int i = 0; i < nextInt(amountOfWordsFrom, amountOfWordsTo); i++) {
            description.append(" ").append(random(nextInt(3, 8), true, true));
        }
        return description.toString();
    }

    public GregorianCalendar getRandomDate(int fromYearIncluding, int toYearExluding) {

        GregorianCalendar gc = new GregorianCalendar();

        int year = nextInt(fromYearIncluding, toYearExluding);

        gc.set(Calendar.YEAR, year);

        int dayOfYear = nextInt(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));

        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

        return gc;
    }

    public String getRandomStringValueFromDB(String db, String user, String password, String table, String column)
            throws SQLException {
        Connection conn = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/" + db +
                        "?user=" + user +
                        "&password=" + password +
                        "&serverTimezone=UTC");
        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = st.executeQuery("SELECT * FROM `" + table + "`");

        rs.last();
        int amountOfRows = rs.getRow();

        rs.absolute(nextInt(1, amountOfRows + 1));
        String randomValue = rs.getString(column);

        rs.close();
        st.close();
        conn.close();

        return randomValue;
    }

    public String generatePrice(double valueFromIncluding, double valueToExcluding) {
        double randomDouble = RandomUtils.nextDouble(valueFromIncluding, valueToExcluding);
        String price = Double.toString(randomDouble);
        int indexOfDot = price.indexOf(".");
        return price.substring(0, indexOfDot + 3);
    }

    public List<String> getRandomStringValuesFromDB(String db, String user, String password, String table, String column) throws SQLException {
        Connection conn = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/" + db +
                        "?user=" + user +
                        "&password=" + password +
                        "&serverTimezone=UTC");
        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = st.executeQuery("SELECT * FROM `" + table +"`");

        rs.last();
        int amountOfRows = rs.getRow();

        List<String> randomValues = new ArrayList<>();
        for (int i = 1; i <= nextInt(1, amountOfRows + 1); i++) {
            rs.absolute(nextInt(1, amountOfRows + 1));
            randomValues.add(rs.getString(column));
        }

        rs.close();
        st.close();
        conn.close();

        return new ArrayList<String>(new HashSet<String>(randomValues));
    }
}
