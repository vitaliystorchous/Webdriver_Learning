package ru.stqa.training.selenium.models;

public class Country {

    public enum Status{
        ENABLED,
        DISABLED
    }

    String name;
    int zonesAmount;
    Status status;


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

    public Status getStatus() {
        return status;
    }

    public Country withStatus(Status status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (zonesAmount != country.zonesAmount) return false;
        if (name != null ? !name.equals(country.name) : country.name != null) return false;
        return status == country.status;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + zonesAmount;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", zonesAmount=" + zonesAmount +
                ", status=" + status +
                '}';
    }
}
