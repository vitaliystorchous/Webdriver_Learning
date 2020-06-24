package ru.stqa.training.selenium.generators;

import org.apache.commons.lang3.RandomUtils;
import ru.stqa.training.selenium.models.Country;

import java.sql.*;

public class UserGenerator {

    public static void main(String[] args) throws SQLException {

        Country randomCountry = getRandomCountry();
        System.out.println(randomCountry.getName() + "\n" +
                randomCountry.getCountryCode() + "\n" +
                randomCountry.getTaxIdFormat() + "\n" +
                randomCountry.getPostcodeFormat());

        String randomZone = getRandomZone(randomCountry);
        System.out.println(randomZone);
    }

    public static Country getRandomCountry() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/litecart?user=root&password=&serverTimezone=UTC");
        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = st.executeQuery("select * from lc_countries where not status = 0");

        rs.last();
        int amountOfCountries = rs.getRow();
        int randomCountryPosition = RandomUtils.nextInt(1, amountOfCountries + 1);
        rs.absolute(randomCountryPosition);

        String name = rs.getString("name");
        String countryCode = rs.getString("iso_code_2");
        String taxIdFormat = rs.getString("tax_id_format");
        if (taxIdFormat.equals("")) { taxIdFormat = null; }
        String postcodeFormat = rs.getString("postcode_format");
        if (postcodeFormat.equals("")) { postcodeFormat = null; }

        rs.close();
        st.close();
        conn.close();

        return new Country()
                .withName(name)
                .withCountryCode(countryCode)
                .withTaxIdFormat(taxIdFormat)
                .withPostcodeFormat(postcodeFormat);
    }

    public static String getRandomZone(Country randomCountry) throws SQLException {

        String countryCode = randomCountry.getCountryCode();

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/litecart?user=root&password=&serverTimezone=UTC");
        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = st.executeQuery("SELECT * FROM lc_zones WHERE country_code = '" + countryCode + "'");

        rs.last();
        int amountOfZones = rs.getRow();

        String randomZone;
        if (amountOfZones > 0) {
            int randomZonePosition = RandomUtils.nextInt(1, amountOfZones + 1);
            rs.absolute(randomZonePosition);

            randomZone = rs.getString("name");
        } else {
            randomZone = null;
        }

        rs.close();
        st.close();
        conn.close();

        return randomZone;
    }
}
