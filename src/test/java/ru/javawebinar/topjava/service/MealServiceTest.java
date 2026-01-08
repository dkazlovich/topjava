package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))

public class MealServiceTest {
    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal podobed = service.get(PODOBED_ID, 100000);
        assertMatch(podobed, MealTestData.podobed);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(PODOBED_ID, 100001));
    }

    @Test
    public void delete() {
        service.delete(PODOBED_ID, 100000);
        assertThrows(NotFoundException.class, () -> service.delete(PODOBED_ID, 100000));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(PODOBED_ID, 100001));
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, 100000);
        assertMatch(service.get(PODOBED_ID, 100000), getUpdated());
    }

    @Test
    public void updateNotFound() {
        Meal updated = getUpdated();
        assertThrows(NotFoundException.class, () -> service.update(updated, 100001));
    }

    @Test
    public void getBetweenInclusive() {
        LocalDate start = LocalDate.parse("2026-01-06");
        LocalDate end = LocalDate.parse("2026-01-07");
        List<Meal> meals = service.getBetweenInclusive(start, end, 100000);
        assertMatch(meals, podobed, zavtrak);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(100000);
        assertMatch(all, zavtrak, podobed);
    }

    @Test
    public void create() {
        Meal created = service.create(getNew(), 100000);
        Integer newId = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(service.get(newId, 100000), newMeal);
    }

    @Test
    public void duplicateDateTimeCreate() {
        Meal meal = getNew();
        Meal mealDuplicate = getNew();
        service.create(meal, 100000);
        assertThrows(DataAccessException.class, () -> service.create(mealDuplicate, 100000));
    }
}