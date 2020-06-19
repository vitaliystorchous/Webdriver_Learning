package ru.stqa.training.selenium.models;

public class Country {

    String name;
    int zonesAmount;

    public String getName() {
        return name;
    }

    public Country withName(String name) {
        this.name = name;
        return this;
    }

    public int getZonesAmount() {
        return zonesAmount;
    }

    public Country withZonesAmount(int zonesAmount) {
        this.zonesAmount = zonesAmount;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (zonesAmount != country.zonesAmount) return false;
        return name != null ? name.equals(country.name) : country.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + zonesAmount;
        return result;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", zonesAmount=" + zonesAmount +
                '}';
    }
}
