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
        String action = getAction(request);
        LOG.debug("Action = "+action);

        String mealId = "";
        String mealDateTime = "";
        String mealDescription = "";
        String mealCalories = "";

        switch (action){
            case "add":
                mealId = request.getParameter("id");
                mealDateTime = request.getParameter("dateTime");
                mealDescription = request.getParameter("description");
                mealCalories = request.getParameter("calories");
                break;
            case "remove":
                mealId = request.getParameter("id");

                break;
            case "edit":
                mealId = request.getParameter("id");

                break;

        }
        LOG.debug("Action parameters: id=" + mealId + ", dateTime=" + mealDateTime + ", description=" + mealDescription + ", calories=" + mealCalories);
        LOG.debug("forward to meals");
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    private String getAction(HttpServletRequest request){

        String pathInfo = request.getPathInfo();
        return pathInfo==null?"":pathInfo.trim().substring(1);

    }

}
