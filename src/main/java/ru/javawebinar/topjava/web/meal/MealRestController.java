package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.Collection;

@Controller
public class MealRestController {
    private final MealService service;
    public MealRestController(MealService service) {
        this.service = service;
    }
    public Collection<Meal> getAll() {
        return service.getAll();
    }
}