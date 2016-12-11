package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class Meal {

    private int id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public Meal(int id, LocalDateTime dateTime, String description, int calories) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public Meal(Meal meal){
        this(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories());
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meal)) return false;

        Meal meal = (Meal) o;

        if (getId() != meal.getId()) return false;
        if (getCalories() != meal.getCalories()) return false;
        if (!getDateTime().equals(meal.getDateTime())) return false;
        return getDescription().equals(meal.getDescription());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getDateTime().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getCalories();
        return result;
    }
}
