package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.filteredByStreams;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    Repository mealRepository = new MealRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String path = "/meals.jsp";
        if (action != null && action.equalsIgnoreCase("delete")) {
            UUID uuid = UUID.fromString(request.getParameter("id"));
            mealRepository.deleteMeal(uuid);
        }
        if (action != null && action.equalsIgnoreCase("update")) {
            UUID uuid = UUID.fromString(request.getParameter("id"));
            Meal meal = mealRepository.getMealById(uuid);
            request.setAttribute("meal", meal);
            path = "meal.jsp";
        }
        request.setAttribute("mealsTo", filteredByStreams(mealRepository.getMeals(), LocalTime.of(0, 0), LocalTime.of(23, 59, 59, 999999999), 2000));
        log.debug("forward to meals");
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action != null && action.equalsIgnoreCase("createMeal")) {
            mealRepository.addMeal(LocalDateTime.parse(request.getParameter("dateTime")), request.getParameter("description"), Integer.parseInt(request.getParameter("calories")));
        }
        if (action != null && action.equalsIgnoreCase("updateMeal")) {
            mealRepository.updateMeal(UUID.fromString(request.getParameter("id")), LocalDateTime.parse(request.getParameter("dateTime")), request.getParameter("description"), Integer.parseInt(request.getParameter("calories")));
        }
        request.setAttribute("mealsTo", filteredByStreams(mealRepository.getMeals(), LocalTime.of(0, 0), LocalTime.of(23, 59, 59, 999999999), 2000));
        String path = "/meals.jsp";
        request.getRequestDispatcher(path).forward(request, response);

    }
}
