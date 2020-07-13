package ru.stqa.training.selenium.models;

import org.apache.commons.lang3.RandomUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class ModelsHelper {
    public String generateRandomTextWithSpaces(int amountOfWordsFrom, int amountOfWordsTo) {
        StringBuilder description = new StringBuilder(random(nextInt(3, 8), true, true));
        for (int i = 0; i < nextInt(amountOfWordsFrom, amountOfWordsTo); i++) {
            description.append(" ").append(random(nextInt(3, 8), true, true));
        }
        return description.toString();
    }

    public static GregorianCalendar getRandomGCDate(int fromYearIncluding, int toYearExcluding) {

        GregorianCalendar gc = new GregorianCalendar();

        int year = nextInt(fromYearIncluding, toYearExcluding);

        gc.set(Calendar.YEAR, year);

        int dayOfYear = nextInt(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));

        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

        return gc;
    }

    public String getRandomStringValueFromDB(String db,
                                             String user,
                                             String password,
                                             String table,
                                             String column) throws SQLException {
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

    public String generateTwoDecimalNumber(double valueFromIncluding, double valueToExcluding) {
        double randomDouble = RandomUtils.nextDouble(valueFromIncluding, valueToExcluding);
        String price = Double.toString(randomDouble);
        int indexOfDot = price.indexOf(".");
        return price.substring(0, indexOfDot + 3);
    }

    public List<String> getRandomStringValuesFromDB(String db,
                                                    String user,
                                                    String password,
                                                    String table,
                                                    String column) throws SQLException {
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

    public double getPercentOfDifferenceOfTwoImages(File fileA, File fileB) {
        BufferedImage imgA = null;
        BufferedImage imgB = null;

        try {
            imgA = ImageIO.read(fileA);
            imgB = ImageIO.read(fileB);
        } catch (IOException e) {
            System.out.println(e);
        }
        int width1 = imgA.getWidth();
        int width2 = imgB.getWidth();
        int height1 = imgA.getHeight();
        int height2 = imgB.getHeight();

        if ((width1 != width2) || (height1 != height2)) {
            System.out.println("Error: Images dimensions" +
                    " mismatch");
            return 100;
        } else {
            long difference = 0;
            for (int y = 0; y < height1; y++) {
                for (int x = 0; x < width1; x++) {
                    int rgbA = imgA.getRGB(x, y);
                    int rgbB = imgB.getRGB(x, y);
                    int redA = (rgbA >> 16) & 0xff;
                    int greenA = (rgbA >> 8) & 0xff;
                    int blueA = (rgbA) & 0xff;
                    int redB = (rgbB >> 16) & 0xff;
                    int greenB = (rgbB >> 8) & 0xff;
                    int blueB = (rgbB) & 0xff;
                    difference += Math.abs(redA - redB);
                    difference += Math.abs(greenA - greenB);
                    difference += Math.abs(blueA - blueB);
                }
            }

            // Total number of red pixels = width * height
            // Total number of blue pixels = width * height
            // Total number of green pixels = width * height
            // So total number of pixels = width * height * 3
            double total_pixels = width1 * height1 * 3;

            // Normalizing the value of different pixels
            // for accuracy(average pixels per color
            // component)
            double avg_different_pixels = difference /
                    total_pixels;

            // There are 255 values of pixels in total
            double percentage = (avg_different_pixels /
                    255) * 100;

            return percentage;
        }
    }
}
