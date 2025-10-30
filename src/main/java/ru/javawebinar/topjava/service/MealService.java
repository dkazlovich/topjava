package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal meal) {
        meal.setUserId(SecurityUtil.authUserId());
        return repository.save(meal);
    }

    public Meal update(Meal meal) {
        if (isAuthUser(meal)) {
            return repository.save(meal);
        }
        return null;
    }

    public boolean delete(int id) {
        Meal meal = repository.get(id);
        if (isAuthUser(meal)) {
            return repository.delete(id);
        }
        return false;
    }

    private static boolean isAuthUser(Meal meal) {
        if (meal.getUserId() == SecurityUtil.authUserId()) {
            return true;
        } else  {
            throw new NotFoundException("User is not authenticated");
        }
    }

    public Meal get(int id) {
        Meal meal = repository.get(id);
        if (isAuthUser(meal)) {
            return meal;
        }
        return null;
    }

    public Collection<Meal> getByDates(LocalDate start, LocalDate end) {
        return repository.getByDates(start, end).stream()
                .filter(MealService::isAuthUser)
                .collect(Collectors.toList());
    }

    public Collection<Meal> getAll() {
        return repository.getAll().stream()
                .filter(MealService::isAuthUser)
                .collect(Collectors.toList());
    }
}