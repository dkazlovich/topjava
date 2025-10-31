package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
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

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Meal> mealsMap = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> {meal.setUserId(SecurityUtil.authUserId()); save(meal);});

    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            mealsMap.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
            return mealsMap.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id) {
        return mealsMap.remove(id) != null;
    }

    @Override
    public Meal get(int id) {
        return mealsMap.get(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return getByDates(LocalDate.MIN, LocalDate.MAX);
    }

    @Override
    public Collection<Meal> getByDates(LocalDate start, LocalDate end) {
        return mealsMap.values().stream()
                .filter(meal -> !meal.getDate().isBefore(start) && !meal.getDate().isAfter(end))
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}

