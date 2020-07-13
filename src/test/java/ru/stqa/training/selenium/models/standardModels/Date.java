package ru.stqa.training.selenium.models.standardModels;

import ru.stqa.training.selenium.models.ModelsHelper;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date extends ModelsHelper {

    Integer day;
    Integer month;
    Integer year;

    public Date() {
        this.day = null;
        this.month = null;
        this.year = null;
    }

    public Integer getDay() {
        return day;
    }

    public Date withDay(Integer day) {
        this.day = day;
        return this;
    }

    public Integer getMonth() {
        return month;
    }

    public Date withMonth(Integer month) {
        this.month = month;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public Date withYear(Integer year) {
        this.year = year;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Date date = (Date) o;

        if (day != null ? !day.equals(date.day) : date.day != null) return false;
        if (month != null ? !month.equals(date.month) : date.month != null) return false;
        return year != null ? year.equals(date.year) : date.year == null;
    }

    @Override
    public String toString() {
        return "Date{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

    @Override
    public int hashCode() {
        int result = day != null ? day.hashCode() : 0;
        result = 31 * result + (month != null ? month.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        return result;
    }

    public static Date getRandomDate(int fromYearIncluding, int toYearExcluding) {
        GregorianCalendar gregorianCalendar = getRandomGCDate(fromYearIncluding, toYearExcluding);

        int day = gregorianCalendar.get(Calendar.DAY_OF_MONTH);
        int month = gregorianCalendar.get(Calendar.MONTH) + 1;
        int year = gregorianCalendar.get(Calendar.YEAR);

        return new Date().withDay(day).withMonth(month).withYear(year);
    }
}
