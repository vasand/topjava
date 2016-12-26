package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int USER_BREAKFAST_ID = START_SEQ + 2;
    public static final int USER_LUNCH_ID = START_SEQ + 3;
    public static final int ADMIN_BREAKFAST_ID = START_SEQ + 4;
    public static final int ADMIN_LUNCH_ID = START_SEQ + 5;

    public static final Meal USER_BREAKFAST =
            new Meal(USER_BREAKFAST_ID, LocalDateTime.of(2016, Month.DECEMBER, 20, 9, 0), "Завтрак", 500);

    public static final Meal USER_LUNCH =
            new Meal(USER_LUNCH_ID, LocalDateTime.of(2016, Month.DECEMBER, 21, 13, 0), "Обед", 1000);

    public static final Meal ADMIN_BREAKFAST =
            new Meal(ADMIN_BREAKFAST_ID, LocalDateTime.of(2016, Month.DECEMBER, 22, 9, 0), "Завтрак", 1000);

    public static final Meal ADMIN_LUNCH =
            new Meal(ADMIN_LUNCH_ID, LocalDateTime.of(2016, Month.DECEMBER, 23, 13, 0), "Обед", 1001);

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected==actual ||
                    (Objects.equals(expected.getId(), actual.getId()) &&
                     Objects.equals(expected.getDateTime(), actual.getDateTime()) &&
                     Objects.equals(expected.getDescription(), actual.getDescription()) &&
                     Objects.equals(expected.getCalories(), actual.getCalories())
                    )
    );

}
