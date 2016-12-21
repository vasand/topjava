package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;
/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public MealWithExceed save(MealWithExceed meal, int userId) {
        repository.save(new Meal(null, meal.getDateTime(), meal.getDescription(), meal.getCalories(), userId));
        return meal;
    }

    @Override
    public void update(MealWithExceed meal, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.get(meal.getId(), userId), meal.getId());
        repository.save(new Meal(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), userId));
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public MealWithExceed get(int id, int userId, int caloriesPerDay) throws NotFoundException {
        return checkNotFoundWithId(getAll(userId, LocalDate.MIN, LocalTime.MIN, LocalDate.MAX, LocalTime.MAX, caloriesPerDay).
                stream().filter(m->m.getId()==id).findFirst().get(), id);
    }

    @Override
    public List<MealWithExceed> getAll(int userId, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, int caloriesPerDay) {
        return MealsUtil.getFilteredWithExceeded(repository.getAll(userId, startDate, endDate), startTime, endTime, caloriesPerDay);
    }
}
