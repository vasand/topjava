package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * GKislin
 * 07.01.2015.
 */
public class TimeUtil {
    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }

    public static LocalTime toLocalTime(UserMeal userMeal){
        return userMeal.getDateTime().toLocalTime();
    }

    public static LocalDate toLocalDate(UserMeal userMeal){
        return userMeal.getDateTime().toLocalDate();
    }
}

