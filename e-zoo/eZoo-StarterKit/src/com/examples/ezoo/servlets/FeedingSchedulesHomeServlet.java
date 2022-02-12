package com.examples.ezoo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

/**
 * Servlet implementation class FeedingSchedulesHomeServlet
 */
@WebServlet("/feedingSchedules")
public class FeedingSchedulesHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
    	FeedingScheduleDAO feedingScheduleDAO = DAOUtilities.getFeedingScheduleDao();
    	List<FeedingSchedule> feedingSchedules = feedingScheduleDAO.getAllFeedingSchedules();
    	request.getSession().setAttribute("feedingSchedules", feedingSchedules);
    	
    	AnimalDAO animalDAO = DAOUtilities.getAnimalDao();
    	List<Animal> animals = animalDAO.getAllAnimals();
    	request.getSession().setAttribute("animals", animals);

    	request.getRequestDispatcher("feedingSchedulesHome.jsp")
    		   .forward(request, response);
	}
}
