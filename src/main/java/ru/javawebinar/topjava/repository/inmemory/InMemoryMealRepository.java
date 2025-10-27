package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Meal> mealsMap = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(SecurityUtil.authUserId());
            mealsMap.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        if (meal.getUserId() == SecurityUtil.authUserId()) {
            return mealsMap.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        Meal meal = mealsMap.get(id);
        if (meal.getUserId() == SecurityUtil.authUserId()) {
            return mealsMap.remove(id) != null;
        }
        return false;
    }

    @Override
    public Meal get(int id) {
        Meal meal = mealsMap.get(id);
        if (meal.getUserId() == SecurityUtil.authUserId()) {
            return meal;
        }
        return null;
    }

    @Override
    public Collection<Meal> getAll() {
        return getByDates(LocalDate.MIN, LocalDate.MAX);
    }

    @Override
    public Collection<Meal> getByDates(LocalDate start, LocalDate end) {
        return mealsMap.values().stream()
                .filter(meal -> meal.getUserId() == SecurityUtil.authUserId() && !meal.getDate().isBefore(start) && !meal.getDate().isAfter(end))
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}

