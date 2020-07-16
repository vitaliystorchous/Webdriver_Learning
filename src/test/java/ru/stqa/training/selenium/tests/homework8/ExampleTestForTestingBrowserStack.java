package ru.stqa.training.selenium.tests.homework8;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.training.selenium.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class ExampleTestForTestingBrowserStack extends TestBase {

    @BeforeMethod (alwaysRun = true)
    public void navigateToSiteHomepage() {
        app.getDriver().navigate().to("https://www.w3schools.com/");
        app.getWait().until(titleIs("W3Schools Online Web Tutorials"));
    }

    @Test (alwaysRun = true)
    public void testLearnHTMLLinkMainNavigationLink() {
        openLearnHTMLCourse();
        assertThat(getPageTitle(), equalTo("HTML Tutorial"));
    }

    @Test (alwaysRun = true)
    public void testLearnCSSLinkMainNavigationLink() {
        openLearnCSSCourse();
        assertThat(getPageTitle(), equalTo("CSS Tutorial"));
    }

    @Test (alwaysRun = true)
    public void testLearnBootstrapLinkMainNavigationLink() {
        openLearnBootstrapCourse();
        assertThat(getPageTitle(), equalTo("Bootstrap 3 or Bootstrap 4"));
    }

    @Test (alwaysRun = true)
    public void testLearnW3CSSLinkMainNavigationLink() {
        openLearnW3CSSCourse();
        assertThat(getPageTitle(), equalTo("W3.CSS Home"));
    }

    @Test (alwaysRun = true)
    public void testLearnColorsLinkMainNavigationLink() {
        openLearnColorsCourse();
        assertThat(getPageTitle(), equalTo("Colors Tutorial"));
    }

    @Test (alwaysRun = true)
    public void testLearnIconsLinkMainNavigationLink() {
        openLearnIconsCourse();
        assertThat(getPageTitle(), equalTo("Icons Tutorial"));
    }

    @Test (alwaysRun = true)
    public void testLearnGraphicsLinkMainNavigationLink() {
        openLearnHTMLGraphicsCourse();
        assertThat(getPageTitle(), equalTo("HTML Graphics"));
    }

    @Test (alwaysRun = true)
    public void testLearnHowToLinkMainNavigationLink() {
        openW3SchoolsHowToPage();
        assertThat(getPageTitle(), equalTo("W3Schools How TO - Code snippets for HTML, CSS and JavaScript"));
    }

    @Test (alwaysRun = true)
    public void testLearnSassLinkMainNavigationLink() {
        openLearnSassCourse();
        assertThat(getPageTitle(), equalTo("Sass Tutorial"));
    }

    //==========================================================

    private String getPageTitle() {
        return app.getDriver().getTitle();
    }

    private void openLearnHTMLCourse() {
        app.getDriver().findElement(By.cssSelector("#mySidenav a[href=\"/html/default.asp\"]")).click();
        app.getWait().until(titleIs("HTML Tutorial"));
    }

    private void openLearnCSSCourse() {
        app.getDriver().findElement(By.cssSelector("#mySidenav a[href=\"/css/default.asp\"]")).click();
        app.getWait().until(titleIs("CSS Tutorial"));
    }

    private void openLearnBootstrapCourse() {
        app.getDriver().findElement(By.cssSelector("#mySidenav a[href=\"/bootstrap/bootstrap_ver.asp\"]")).click();
        app.getWait().until(titleIs("Bootstrap 3 or Bootstrap 4"));
    }

    private void openLearnW3CSSCourse() {
        app.getDriver().findElement(By.cssSelector("#mySidenav a[href=\"/w3css/default.asp\"]")).click();
        app.getWait().until(titleIs("W3.CSS Home"));
    }

    private void openLearnColorsCourse() {
        app.getDriver().findElement(By.cssSelector("#mySidenav a[href=\"/colors/default.asp\"]")).click();
        app.getWait().until(titleIs("Colors Tutorial"));
    }

    private void openLearnIconsCourse() {
        app.getDriver().findElement(By.cssSelector("#mySidenav a[href=\"/icons/default.asp\"]")).click();
        app.getWait().until(titleIs("Icons Tutorial"));
    }

    private void openLearnHTMLGraphicsCourse() {
        app.getDriver().findElement(By.cssSelector("#mySidenav a[href=\"/graphics/default.asp\"]")).click();
        app.getWait().until(titleIs("HTML Graphics"));
    }

    private void openW3SchoolsHowToPage() {
        app.getDriver().findElement(By.cssSelector("#mySidenav a[href=\"/howto/default.asp\"]")).click();
        app.getWait().until(titleIs("W3Schools How TO - Code snippets for HTML, CSS and JavaScript"));
    }

    private void openLearnSassCourse() {
        app.getDriver().findElement(By.cssSelector("#mySidenav a[href=\"/sass/default.asp\"]")).click();
        app.getWait().until(titleIs("Sass Tutorial"));
    }
}
