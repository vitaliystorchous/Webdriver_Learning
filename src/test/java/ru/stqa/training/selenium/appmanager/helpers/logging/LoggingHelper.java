package ru.stqa.training.selenium.appmanager.helpers.logging;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;

import java.util.List;

public class LoggingHelper {

    WebDriver wd;

    public LoggingHelper(WebDriver wd) {
        this.wd = wd;
    }

    public List<LogEntry> getBrowserLogs() {
        return wd.manage().logs().get("browser").getAll();
    }
}
