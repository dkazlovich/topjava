package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.exception.EntityNotFountException;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class MapRepository implements Repository {

    Meal meal1 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    Meal meal2 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
    Meal meal3 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
    Meal meal4 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
    Meal meal5 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
    Meal meal6 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
    Meal meal7 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);
    private Map<UUID, Meal> meals = new HashMap<UUID, Meal>() {{
        put(meal1.getId(), meal1);
        put(meal2.getId(), meal2);
        put(meal3.getId(), meal3);
        put(meal4.getId(), meal4);
        put(meal5.getId(), meal5);
        put(meal6.getId(), meal6);
        put(meal7.getId(), meal7);
    }};

    @Override
    public List<Meal> getMeals() {
        return new ArrayList<>(meals.values());
    }

    @Override
    public Meal getMealById(UUID id) {
        try {
            return meals.get(id);
        } catch (NullPointerException e) {
            throw new EntityNotFountException();
        }
    }

    @Override
    public void deleteMeal(UUID id) {
        meals.remove(id);
    }

    @Override
    public void updateMeal(UUID id, LocalDateTime dateTime, String description, int calories) {
        try {
            Meal meal = meals.get(id);
            meal.setDateTime(dateTime);
            meal.setDescription(description);
            meal.setCalories(calories);
        } catch (NullPointerException e) {
            throw new EntityNotFountException();
        }
    }

    @Override
    public Meal addMeal(LocalDateTime dateTime, String description, int calories) {
        Meal meal = new Meal(dateTime, description, calories);
        meals.put(meal.getId(), meal);
        return meal;
    }
}
