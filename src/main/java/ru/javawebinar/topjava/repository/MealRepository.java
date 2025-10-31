package ru.javawebinar.topjava.repository;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

// TODO add userId
@Repository
public interface MealRepository {
    // null if updated meal does not belong to userId
    Meal save(Meal meal);

    // false if meal does not belong to userId
    boolean delete(int id);

    // null if meal does not belong to userId
    Meal get(int id);

    // ORDERED dateTime desc
    Collection<Meal> getAll();

    Collection<Meal> getByDates(LocalDate start, LocalDate end);
}
