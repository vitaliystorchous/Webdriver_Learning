package ru.stqa.training.selenium.tests;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.training.selenium.generators.GeneratorHelper;
import ru.stqa.training.selenium.models.Product;
import sun.awt.image.ToolkitImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.apache.commons.lang3.time.DateUtils.truncatedEquals;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/*
перевірка правильно загруженої картинки:
        навчитись робити порівняння 2 окремих файлів-картинок (зробити відповідний метод і помістити його в логічний клас який надалі можна буде використовувати на різних проектах)
        загрузить картинку на сервер
        зайти на відповідну сторінку
        зробити скріншот загруженої картинки
        порівняти скріншот з потрібною нам картинкою (або оригінальна картинка, або зарані підготовлена зменшена/збільшена версія оригінальної картинки)
        ідея: при порівнянні 2 об'єктів один із атрибутів яких є картинки можна в метод equals додати toggle який буде вказувати як і чи потріd)бно взагалі перевіряти картинки (напр. enum ImageCheck.Enabled, ImageCheck.Disable
*/


public class Sandbox extends TestBase {

    @Test
    public void test() throws IOException {
        //сравнивать картинки мы уже можем - используй метод compareTwoImages (он возвращает процент различия картинок)
        //на данный момент я переделал этот метод что бы он принимал как параметры 2 BufferedImage, но в случае
        //необходимости можно переделать его что бы он принимал 2 File
        //теперь нужно научиться снимать скриншот конкретного елемента и потом сравнивать скриншот с заданой картинкой
        //реализация снятия скриншота отдельного элемента частично реализирована в методе takeScreenshot (пока это
        //sandbox). можно взять это за основу и доработать его. его проблема в том что он не скролит к элементу, если
        //он не в зоне видимости
        File largeImage = new File("src/test/resources/test_files/road-1072823__340.jpg");
        File smallImage = new File("src/test/resources/test_files/2052e78c02f27d93ae336f9907aab72e4b58b618100x75_fob.jpg");
        File screenshot = new File("src/test/resources/test_files/Screenshot_5.jpg");

        BufferedImage bufferedSmallImage = ImageIO.read(smallImage);
        BufferedImage bufferedResizedImage = resize(largeImage, smallImage);
        File resizedImage = new File("src/test/resources/test_files/resizedImage.jpg");
        ImageIO.write(bufferedResizedImage, "jpg", resizedImage);

        compareTwoImages(resizedImage, smallImage);
        compareTwoImages(bufferedResizedImage, bufferedSmallImage);


        /*app.goTo().adminPanel();
        app.goTo().catalogPage();
        app.catalogPage().openProduct("test product 1");
        takeScreenshot(app.getDriver());*/
    }

    public void takeScreenshot(WebDriver driver) throws IOException {
        WebElement ele = driver.findElement(By.cssSelector("p > img"));

// Get entire page screenshot
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        BufferedImage  fullImg = ImageIO.read(screenshot);

// Get the location of element on the page
        Point point = ele.getLocation();

// Get width and height of the element
        int eleWidth = ele.getSize().getWidth();
        int eleHeight = ele.getSize().getHeight();

// Crop the entire page screenshot to get only element screenshot
        BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),
                eleWidth, eleHeight);
        ImageIO.write(eleScreenshot, "jpg", screenshot);

// Copy the element screenshot to disk
        File screenshotLocation = new File("src/test/resources/test_files/seleniumScreenshot.jpg");
        FileUtils.copyFile(screenshot, screenshotLocation);
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

    public BufferedImage resize(File image, int newWidth, int newHeight) throws IOException {
        BufferedImage img = ImageIO.read(image);
        Image tmp = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    public BufferedImage resize(File imageToResize, File imageToGetSize) throws IOException {
        BufferedImage imgA = ImageIO.read(imageToResize);
        BufferedImage imgB = ImageIO.read(imageToGetSize);
        Image tmp = imgA.getScaledInstance(imgB.getWidth(), imgB.getHeight(), Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(imgB.getWidth(), imgB.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    public void compareTwoImages(File fileA, File fileB) {

        BufferedImage imgA = null;
        BufferedImage imgB = null;

        try
        {
            imgA = ImageIO.read(fileA);
            imgB = ImageIO.read(fileB);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }

        int width1 = imgA.getWidth();
        int width2 = imgB.getWidth();
        int height1 = imgA.getHeight();
        int height2 = imgB.getHeight();

        if ((width1 != width2) || (height1 != height2))
            System.out.println("Error: Images dimensions" +
                    " mismatch");
        else {
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

            System.out.println("Difference Percentage-->" +
                    percentage);
        }
    }

    public void compareTwoImages(BufferedImage imgA, BufferedImage imgB) {

        int width1 = imgA.getWidth();
        int width2 = imgB.getWidth();
        int height1 = imgA.getHeight();
        int height2 = imgB.getHeight();

        if ((width1 != width2) || (height1 != height2))
            System.out.println("Error: Images dimensions" +
                    " mismatch");
        else {
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

            System.out.println("Difference Percentage-->" +
                    percentage);
        }
    }
}