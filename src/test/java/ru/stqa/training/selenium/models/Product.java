package ru.stqa.training.selenium.models;

public class Product {

    public enum Sticker {
        NEW,
        SALE
    }

    String name;
    Sticker sticker;


    public String getName() {
        return name;
    }

    public Product withName(String name) {
        this.name = name;
        return this;
    }

    public Sticker getSticker() {
        return sticker;
    }

    public Product withSticker(Sticker sticker) {
        this.sticker = sticker;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (sticker != product.sticker) return false;
        return name != null ? name.equals(product.name) : product.name == null;
    }

    @Override
    public int hashCode() {
        int result = sticker != null ? sticker.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", sticker=" + sticker +
                '}';
    }
}
