package com.examples.ezoo.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.FeedingSchedule;

@WebServlet("/updateFeedingSchedule")
public class UpdateFeedingScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long scheduleId = Long.parseLong(request.getParameter("scheduleId"));
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();

		FeedingSchedule feedingSchedule =
				dao.getFeedingScheduleById(scheduleId);
		request.getSession().setAttribute("feedingSchedule", feedingSchedule);
		request.getRequestDispatcher("updateFeedingSchedule.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long scheduleId = Long.parseLong(request.getParameter("scheduleId"));
		String feedingTime = request.getParameter("feedingTime");
		String recurrence = request.getParameter("recurrence");
		String food = request.getParameter("food");
		String notes = request.getParameter("notes");
		
		FeedingSchedule feedingSchedule =
				new FeedingSchedule(scheduleId, feedingTime, recurrence, food, notes);
		
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		boolean updatedTable = dao.updateFeedingSchedule(feedingSchedule);
		if (updatedTable) {
			request.getSession().setAttribute("message", "Successfully updated!");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("feedingSchedules");
		} else {
			request.getSession()
				   .setAttribute("message", "Failed to update schedule");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			response.sendRedirect("feedingSchedules");
		}
	}
}
