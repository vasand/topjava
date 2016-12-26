package ru.javawebinar.topjava.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;


/**
 * Created by andrey on 26.12.16.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Autowired
    DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGetUserBreakfast() throws Exception {
        Meal meal = service.get(USER_BREAKFAST_ID, USER_ID);
        MATCHER.assertEquals(USER_BREAKFAST, meal);
    }

    @Test
    public void testGetAdminLunch() throws Exception {
        Meal meal = service.get(ADMIN_LUNCH_ID, ADMIN_ID);
        MATCHER.assertEquals(ADMIN_LUNCH, meal);
    }

    @Test(expected = NotFoundException.class)
    public void testGetAlienMeal() throws Exception {
        service.get(ADMIN_BREAKFAST_ID, USER_ID);
    }

    @Test
    public void testDeleteUserLunch() throws Exception {
        service.delete(USER_LUNCH_ID, USER_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(USER_BREAKFAST), service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteAlienMeal() throws Exception {
        service.delete(USER_LUNCH_ID, ADMIN_ID);
    }

    @Test
    public void testGetUserMealBetweenDates() throws Exception {
        Collection<Meal> meals = service.getBetweenDates(LocalDate.of(2016, Month.DECEMBER, 21),
                LocalDate.of(2016, Month.DECEMBER, 21), USER_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(USER_LUNCH), meals);
    }

    @Test
    public void testGetAdminMealBetweenDateTimes() throws Exception {
        Collection<Meal> meals = service.getBetweenDateTimes(LocalDateTime.of(2016, Month.DECEMBER, 22, 8, 0),
                LocalDateTime.of(2016, Month.DECEMBER, 22, 11, 0), ADMIN_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(ADMIN_BREAKFAST), meals);
    }

    @Test
    public void testGetEmptyUserMealBetweenDateTimes() throws Exception {
        Collection<Meal> meals = service.getBetweenDateTimes(LocalDateTime.of(2016, Month.DECEMBER, 22, 8, 0),
                LocalDateTime.of(2016, Month.DECEMBER, 22, 11, 0), USER_ID);
        MATCHER.assertCollectionEquals(Collections.emptyList(), meals);
    }

    @Test
    public void testGetAllUserMeal() throws Exception {
        Collection<Meal> meals = service.getAll(USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(USER_LUNCH, USER_BREAKFAST), meals);

    }

    @Test
    public void testUpdateUserLunch() throws Exception {
        Meal updatedMeal = new Meal(USER_LUNCH);
        updatedMeal.setDescription("Ужин");
        updatedMeal.setCalories(2000);
        service.update(updatedMeal, USER_ID);
        MATCHER.assertEquals(updatedMeal, service.get(USER_LUNCH_ID, USER_ID));
    }

    @Test
    public void testUpdateAdminBreakfast() throws Exception {
        Meal updatedMeal = new Meal(ADMIN_BREAKFAST);
        updatedMeal.setDescription("Завтрак");
        updatedMeal.setCalories(3000);
        service.update(updatedMeal, ADMIN_ID);
        MATCHER.assertEquals(updatedMeal, service.get(ADMIN_BREAKFAST_ID, ADMIN_ID));

    }

    @Test(expected = NotFoundException.class)
    public void testUpdateAlienMeal() throws Exception {
        Meal updatedMeal = new Meal(ADMIN_BREAKFAST);
        updatedMeal.setDescription("Завтрак");
        updatedMeal.setCalories(3000);
        service.update(updatedMeal, USER_ID);
    }

    @Test
    public void testSave() throws Exception {
        Meal savedMeal = service.save(new Meal(null, LocalDateTime.of(2016, Month.DECEMBER, 26, 18, 0),
                "Перекус", 1500), USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(savedMeal, USER_LUNCH, USER_BREAKFAST), service.getAll(USER_ID));
    }

}