package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoMemory;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;

/**
 * Created by andrey on 11.12.16.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    private MealDao mealDao = new MealDaoMemory();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doAction(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doAction(request, response);
    }

    private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        int action = getAction(request);
        LOG.debug("forward to meals");
        LOG.debug("Path = "+request.getPathTranslated());
        LOG.debug("Path = "+request.getPathInfo());

        mealDao.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        request.setAttribute("mealList", mealDao.getAll());
        request.getRequestDispatcher("/meals.jsp").forward(request, response);

    }

    private int getAction(HttpServletRequest request) {
        return 0;
    }
}
