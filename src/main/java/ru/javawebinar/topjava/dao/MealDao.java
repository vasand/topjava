package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Created by andrey on 11.12.16.
 */
public interface MealDao {

    public void add(Meal meal);

    public void delete(int id);

    public void update(Meal meal);

    public Meal getById(int id);

    public List<Meal> getAll();
}
