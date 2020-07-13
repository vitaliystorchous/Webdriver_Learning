package ru.stqa.training.selenium.models.standardModels;

import ru.stqa.training.selenium.models.ModelsHelper;

import java.io.File;

public class Image extends ModelsHelper {

    File image;

    public Image() {
        this.image = null;
    }

    public Image(File image) {
        this.image = image;
    }

    public String getPath() {
        return image.getPath();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image1 = (Image) o;

        return image != null ? (getPercentOfDifferenceOfTwoImages(image, image1.image) < 2) : image1.image == null;
    }

    @Override
    public int hashCode() {
        return image != null ? image.hashCode() : 0;
    }
}
