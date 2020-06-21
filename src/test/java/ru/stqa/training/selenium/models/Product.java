package ru.stqa.training.selenium.models;

public class Product {

    public enum Sticker {
        NEW,
        SALE
    }

    String name;
    Sticker sticker;
    Double regularPrice;
    Double campaignPrice;


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

    public Double getRegularPrice() {
        return regularPrice;
    }

    public Product withRegularPrice(Double regularPrice) {
        this.regularPrice = regularPrice;
        return this;
    }

    public Double getCampaignPrice() {
        return campaignPrice;
    }

    public Product withCampaignPrice(Double campaignPrice) {
        this.campaignPrice = campaignPrice;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (sticker != product.sticker) return false;
        if (regularPrice != null ? !regularPrice.equals(product.regularPrice) : product.regularPrice != null)
            return false;
        return campaignPrice != null ? campaignPrice.equals(product.campaignPrice) : product.campaignPrice == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (sticker != null ? sticker.hashCode() : 0);
        result = 31 * result + (regularPrice != null ? regularPrice.hashCode() : 0);
        result = 31 * result + (campaignPrice != null ? campaignPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", sticker=" + sticker +
                ", regularPrice=" + regularPrice +
                ", campaignPrice=" + campaignPrice +
                '}';
    }
}
