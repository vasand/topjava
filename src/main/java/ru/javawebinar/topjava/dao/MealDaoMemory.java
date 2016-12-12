package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * Created by andrey on 11.12.16.
 */
public class MealDaoMemory implements MealDao {

    private static final Logger LOG = LoggerFactory.getLogger(MealDaoMemory.class);
    private CopyOnWriteArrayList<Meal> mealList = new CopyOnWriteArrayList<>();

    @Override
    public void add(Meal meal) {
        if (meal==null){
            LOG.info("Nothing to add. Meal==NULL");
            return;
        }
        Meal mealForAdd = new Meal(meal.getDateTime(), meal.getDescription(), meal.getCalories());
        mealList.addIfAbsent(mealForAdd);
        mealForAdd.setId(mealList.indexOf(mealForAdd)+1);
        LOG.info("Meal was added. Meal:" + mealForAdd);
    }

    @Override
    public void delete(int id) {
        if (mealList.size()>id){
            Meal mealForDelete = getById(id-1);
            mealList.set(id-1, null);
            LOG.info("Meal was deleted. Meal:" + mealForDelete);
        }else LOG.info("Nothing for delete by Id = " + id);
    }

    @Override
    public void update(Meal meal) {
        if (meal==null){
            LOG.info("Nothing to update. Meal==NULL");
            return;
        }
        if (mealList.contains(meal)){
            mealList.set(mealList.indexOf(meal), new Meal(meal));
            LOG.info("Meal was deleted. Meal:" + meal);
        }else LOG.info("Nothing for update. Storage not contains meal:" + meal);

    }

    @Override
    public Meal getById(int id) {
        if (mealList.size()<=id || mealList.get(id-1)==null){
            LOG.info("Storage not contains meal with Id=" + id);
            return null;
        }
        Meal meal = new Meal(mealList.get(id-1));
        LOG.info("Meal was loaded. Meal:" +meal);
        return meal;
    }

    @Override
    public List<Meal> getAll() {
        LOG.info("Meal list was loaded");
        return mealList.stream().filter(p -> p != null).collect(Collectors.toList());
    }
}
