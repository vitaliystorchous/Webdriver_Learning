package ru.stqa.training.selenium.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Status {
    ENABLED,
    DISABLED;

    private static final List<Status> values =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int size = values.size();
    private static final Random random = new Random();

    public static Status randomStatus()  {
        return values.get(random.nextInt(size));
    }
}
