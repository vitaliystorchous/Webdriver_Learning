package ru.stqa.training.selenium.tests;

import org.apache.commons.lang3.time.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.training.selenium.generators.GeneratorHelper;
import ru.stqa.training.selenium.models.Product;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.apache.commons.lang3.time.DateUtils.truncatedEquals;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Sandbox {

    @Test
    public void test() throws IOException {
        try {
            System.out.println(getMD5Checksum("src/test/resources/test_files/road-1072823__340.jpg"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] createChecksum(String filename) throws Exception {
        InputStream fis =  new FileInputStream(filename);

        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance("MD5");
        int numRead;

        do {
            numRead = fis.read(buffer);
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);

        fis.close();
        return complete.digest();
    }

    // see this How-to for a faster way to convert
    // a byte array to a HEX string
    public static String getMD5Checksum(String filename) throws Exception {
        byte[] b = createChecksum(filename);
        String result = "";

        for (int i=0; i < b.length; i++) {
            result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        return result;
    }
}