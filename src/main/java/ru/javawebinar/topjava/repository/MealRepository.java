package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.exception.EntityNotFountException;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static ru.javawebinar.topjava.util.MealsUtil.filteredByStreams;

public class MealRepository implements Repository {

    private List<Meal> meals = new ArrayList<>(Arrays.asList(
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    ));

    @Override
    public List<Meal> getMeals() {
        return meals;
    }

    @Override
    public Meal getMealById(UUID id) {
        return meals.stream().filter(m -> m.getId().equals(id)).findFirst().orElseThrow(EntityNotFountException::new);
    }

    @Override
    public void deleteMeal(UUID id) {
        meals.removeIf(m -> m.getId().equals(id));
    }

    @Override
    public void updateMeal(UUID id, LocalDateTime dateTime, String description, int calories) {
        Meal meal = meals.stream().filter(m -> m.getId().equals(id)).findFirst().orElseThrow(EntityNotFountException::new);
        try {
            meal.setDateTime(dateTime);
            meal.setDescription(description);
            meal.setCalories(calories);
        } catch (EntityNotFountException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Meal addMeal(LocalDateTime dateTime, String description, int calories) {
        Meal meal = new Meal(dateTime, description, calories);
        meals.add(meal);
        return meal;
    }


}
