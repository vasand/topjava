package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoMemory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
                actionAdd(mealId, mealDateTime, mealDescription, mealCalories);
                break;
            case "remove":
                mealId = request.getParameter("id");
                actionRemove(mealId);
                break;
            case "edit":
                mealId = request.getParameter("id");
                if (mealId!=null && !mealId.equals("")){
                    request.setAttribute("curMeal", mealDao.getById(Integer.parseInt(mealId)));
                }
                break;

        }
        LOG.debug("Action parameters: id=" + mealId + ", dateTime=" + mealDateTime + ", description=" + mealDescription + ", calories=" + mealCalories);
        LOG.debug("forward to meals");
        request.setAttribute("mealList", MealsUtil.getFilteredWithExceeded(mealDao.getAll(), LocalTime.MIN, LocalTime.MAX, 2000));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    private void actionRemove(String mealId) {
        if (mealId!=null && !mealId.equals("")) mealDao.delete(Integer.parseInt(mealId));
    }

    private void actionAdd(String mealId, String mealDateTime, String mealDescription, String mealCalories) {
        if (mealDateTime==null || mealDescription==null || mealCalories==null
                || mealDateTime.isEmpty() || mealDescription.isEmpty() || mealCalories.isEmpty()){
            LOG.info("Nothing to add.");
            return;
        }
        if (mealId==null || mealId.equals(""))
            mealDao.add(new Meal(LocalDateTime.parse(mealDateTime), mealDescription, Integer.parseInt(mealCalories)));
        else
            mealDao.update(new Meal(Integer.parseInt(mealId), LocalDateTime.parse(mealDateTime), mealDescription, Integer.parseInt(mealCalories)));
    }


    private String getAction(HttpServletRequest request){

        String pathInfo = request.getPathInfo();
        return pathInfo==null?"":pathInfo.trim().substring(1);

    }

}
