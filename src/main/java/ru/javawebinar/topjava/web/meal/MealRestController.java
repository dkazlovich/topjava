package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@Controller
public class MealRestController {
    private final MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public void delete(int id) {
        service.delete(id);
    }

    public Meal get(int id) {
        return service.get(id);
    }

    public Meal create(Meal meal) {
        return service.create(meal);
    }

    public Collection<Meal> getByDates(LocalDate start, LocalDate end) {
        return service.getByDates(start, end);
    }

    public Collection<Meal> getByTime(LocalTime start, LocalTime end) {
        return service.getByTime(start, end);
    }

    public Collection<Meal> getAll() {
        return service.getAll();
    }
}