package ru.javawebinar.topjava;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;


public class MealTestData {
    public static final int PODOBED_ID = START_SEQ + 3;
    public static final int ZAVTRAK_ID = START_SEQ + 4;
    public static final int PODYZHIN_ID = START_SEQ + 5;

    public static final Meal podobed = new Meal(PODOBED_ID, LocalDateTime.parse("2026-01-06T17:56:00"), "Подобед", 1000);
    public static final Meal zavtrak = new Meal(ZAVTRAK_ID, LocalDateTime.parse("2026-01-07T17:57:00"), "Завтрак", 3000);
    public static final Meal podyzhin = new Meal(ZAVTRAK_ID, LocalDateTime.parse("2026-01-06T17:58:00"), "Подужин", 900);

    public static Meal getNew() {
        return new Meal(LocalDateTime.parse("2026-01-16T13:33:33"),"new",333);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(podobed);
        updated.setDateTime(LocalDateTime.parse("2026-01-17T23:44:45"));
        updated.setDescription("updated");
        updated.setCalories(555);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

}
