package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface MealService {

    MealWithExceed save(MealWithExceed meal, int userId);

    void update(MealWithExceed meal, int userId);

    void delete(int id, int userId) throws NotFoundException;

    MealWithExceed get(int id, int userId, int caloriesPerDay) throws NotFoundException;

    List<MealWithExceed> getAll(int userId, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, int caloriesPerDay);
}
