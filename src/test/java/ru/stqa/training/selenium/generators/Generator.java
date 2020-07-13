package ru.stqa.training.selenium.generators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mifmif.common.regex.Generex;
import org.apache.commons.lang3.RandomUtils;
import ru.stqa.training.selenium.models.*;

import java.io.*;
import java.sql.*;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class Generator extends ModelsHelper {

    public User generateRandomUser() throws SQLException, IOException {

        List<User> users = existingUsers(System.getProperty("user.dir") + "/src/test/resources/users.json");
        String nextEmail = getNextEmail(users);

        String randomFirstName = generateRandomName();
        String randomLastName = generateRandomName();
        String randomPassword = generateRandomPassword();
        String randomCompany = generateRandomCompany();
        Country randomCountry = getRandomCountry();
        String randomZone = getRandomZone(randomCountry);
        String randomCity = generateRandomCity();
        String randomAddress1 = generateRandomAddress();
        String randomAddress2 = generateRandomAddress();
        String randomTaxId = generateRandomTaxId(randomCountry);
        String randomPostcode = generateRandomPostcode(randomCountry);
        String randomPhone = generateRandomPhone();
        boolean randomNewsletter = generateRandomNewsletter();

        User newRandomUser = new User()
                .withFirstName(randomFirstName)
                .withLastName(randomLastName)
                .withEmail(nextEmail)
                .withPassword(randomPassword)
                .withCompany(randomCompany)
                .withCountry(randomCountry.getName())
                .withZone(randomZone)
                .withCity(randomCity)
                .withAddress1(randomAddress1)
                .withAddress2(randomAddress2)
                .withTaxId(randomTaxId)
                .withPostcode(randomPostcode)
                .withPhone(randomPhone)
                .withNewsletter(randomNewsletter);

        users.add(newRandomUser);
        writeToUsersFile(users, System.getProperty("user.dir") + "/src/test/resources/users.json");

        return newRandomUser;
    }

    private String generateRandomPostcode(Country country) {
        if (! country.getPostcodeFormat().equals("")) {
            String regex = country.getPostcodeFormat().replaceAll("[\\^$]", "");
            Generex generex = new Generex(regex);
            return generex.random();
        } else {
            return random(5, true, true);
        }
    }

    public String generateRandomTaxId(Country country) {
        if (! country.getTaxIdFormat().equals("")) {
            String regex = country.getTaxIdFormat().replaceAll("[\\^$]", "");
            Generex generex = new Generex(regex);
            return generex.random();
        } else {
            return random(10, true, true);
        }
    }

    private List<User> existingUsers(String pathToFileWithUsers) throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToFileWithUsers));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(bufferedReader, new TypeToken<List<User>>() {}.getType());
    }

    private void writeToUsersFile(List<User> users, String pathToUsersFile) throws IOException {
        try (FileWriter writer = new FileWriter(pathToUsersFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public String getNextEmail(List<User> users) {
        int indexOfLastUser = users.size() - 1;
        User lastUser = users.get(indexOfLastUser);
        int indexOfPlus = lastUser.getEmail().indexOf("+");
        int indexOfAt = lastUser.getEmail().indexOf("@");
        int emailCounter = Integer.parseInt(lastUser.getEmail().substring(indexOfPlus + 1, indexOfAt)) + 1;
        return "rufjtigk+" + emailCounter + "@gmail.com";
    }

    public boolean generateRandomNewsletter() {
        return RandomUtils.nextBoolean();
    }

    public String generateRandomPhone() {
        return random(10, false, true);
    }

    public String generateRandomAddress() {
        return random(nextInt(5, 11), true, true) + ", "
                + random(nextInt(1, 5), false, true);
    }

    public String generateRandomCity() {
        return random(nextInt(5, 11), true, true);
    }

    public String generateRandomCompany() {
        return random(nextInt(5, 10), true, true) + " "
                    + random(nextInt(3, 6), true, true);
    }

    public String generateRandomPassword() {
        return random(nextInt(8, 11), true, true);
    }

    public String generateRandomName() {
        return random(nextInt(5, 10), true, true);
    }

    public Country getRandomCountry() throws SQLException {
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
        String postcodeFormat = rs.getString("postcode_format");

        rs.close();
        st.close();
        conn.close();

        return new Country()
                .withName(name)
                .withCountryCode(countryCode)
                .withTaxIdFormat(taxIdFormat)
                .withPostcodeFormat(postcodeFormat);
    }

    public String getRandomZone(Country randomCountry) throws SQLException {

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
            randomZone = "";
        }

        rs.close();
        st.close();
        conn.close();

        return randomZone;
    }
}

