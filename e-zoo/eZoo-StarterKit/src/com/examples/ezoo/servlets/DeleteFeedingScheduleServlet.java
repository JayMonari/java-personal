package com.examples.ezoo.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;

@WebServlet("/deleteFeedingSchedule")
public class DeleteFeedingScheduleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long scheduleId = Long.parseLong(request.getParameter("scheduleId"));
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();

		boolean removedFromTable = dao.removeFeedingScheduleById(scheduleId);
		if (removedFromTable) {
			request.getSession().setAttribute("message", "You removed the schedule!");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("feedingSchedules");
		} else {
			request.getSession()
				   .setAttribute("message", "Failed to remove schedule");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			response.sendRedirect("feedingSchedules");
		}
	}

}
