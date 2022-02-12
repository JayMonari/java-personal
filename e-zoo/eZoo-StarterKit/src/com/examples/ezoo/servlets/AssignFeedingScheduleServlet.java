package com.examples.ezoo.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;

@WebServlet("/assignFeedingSchedule")
public class AssignFeedingScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long scheduleID = Long.parseLong(request.getParameter("scheduleID"));
		Long animalID = Long.parseLong(request.getParameter("animalID"));
		
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		boolean assignedToAnimal = dao.setFeedingScheduleForAnimal(animalID, scheduleID);
		if (assignedToAnimal) {
			request.getSession().setAttribute("message", "Animal assigned feeding schedule!");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("animalCare");
		} else {
			request.getSession()
				   .setAttribute("message", "Failed to assign schedule to animal");
			request.getSession().setAttribute("messageClass", "alert-danger");
			response.sendRedirect("feedingSchedules");
		}
	}

}
