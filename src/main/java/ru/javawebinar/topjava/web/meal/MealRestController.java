package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;

import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class MealRestController {

    @Autowired
    private MealService service;

    public List<MealWithExceed> getAll(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime){
        startDate = startDate==null?LocalDate.MIN:startDate;
        endDate   = endDate==null?LocalDate.MAX:endDate;
        startTime = startTime==null?LocalTime.MIN:startTime;
        endTime   = endTime==null?LocalTime.MAX:endTime;
        return service.getAll(AuthorizedUser.id(), startDate, startTime, endDate, endTime, AuthorizedUser.getCaloriesPerDay());
    }

    public List<MealWithExceed> getAll(LocalDate startDate, LocalDate endDate){
        return getAll(startDate, LocalTime.MIN, endDate, LocalTime.MAX);
    }

    public List<MealWithExceed> getAll(LocalTime startTime, LocalTime endTime){
        return getAll(LocalDate.MIN, startTime, LocalDate.MAX, endTime);
    }

    public List<MealWithExceed> getAll(){
        return getAll(LocalDate.MIN, LocalTime.MIN, LocalDate.MAX, LocalTime.MAX);
    }

    public MealWithExceed get(int id) throws NotFoundException{
        return service.get(id, AuthorizedUser.id(), AuthorizedUser.getCaloriesPerDay());
    }

    public void delete(int id)throws NotFoundException{
        service.delete(id, AuthorizedUser.id());
    }

    public void save(MealWithExceed meal){
        service.save(meal, AuthorizedUser.id());
    }

    public void update(MealWithExceed meal) throws NotFoundException{
        service.update(meal, AuthorizedUser.id());
    }
}
