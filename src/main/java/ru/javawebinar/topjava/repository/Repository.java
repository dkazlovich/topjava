package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface Repository {
    List<Meal> getMeals();
    Meal getMealById(UUID id);
    void deleteMeal(UUID id);
    void updateMeal(UUID id, LocalDateTime dateTime, String description, int calories);
    Meal addMeal(LocalDateTime dateTime, String description, int calories);

}
