package com.examples.ezoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

public class FeedingScheduleDAOImpl implements FeedingScheduleDAO {
	
	private Connection connection = null;
	private PreparedStatement statement = null;
	private boolean updatedSuccessfully;

	@Override
	public List<FeedingSchedule> getAllFeedingSchedules() {
		List<FeedingSchedule> feedingSchedules = new ArrayList<>();
		Statement staticStatement = null;
		try {
			connection = DAOUtilities.getConnection();
			staticStatement = connection.createStatement();
			String sql = "SELECT * FROM FEEDING_SCHEDULES";

			ResultSet rs = staticStatement.executeQuery(sql);
			while (rs.next()) {
				FeedingSchedule feedingSchedule = new FeedingSchedule();
				feedingSchedule.setScheduleId(rs.getLong("schedule_id"));
				feedingSchedule.setFeedingTime(rs.getString("feeding_time"));
				feedingSchedule.setRecurrence(rs.getString("recurrence"));
				feedingSchedule.setFood(rs.getString("food"));
				feedingSchedule.setNotes(rs.getString("notes"));
				
				feedingSchedules.add(feedingSchedule);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(staticStatement);
		}
		return feedingSchedules;
	}
	
	@Override
	public FeedingSchedule getFeedingScheduleById(long id) {
		FeedingSchedule feedingSchedule = new FeedingSchedule();
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM FEEDING_SCHEDULES WHERE schedule_id=?";
			
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				feedingSchedule.setScheduleId(rs.getLong("schedule_id"));
				feedingSchedule.setFeedingTime(rs.getString("feeding_time"));
				feedingSchedule.setRecurrence(rs.getString("recurrence"));
				feedingSchedule.setFood(rs.getString("food"));
				feedingSchedule.setNotes(rs.getString("notes"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return feedingSchedule;
	}

	@Override
	public FeedingSchedule getFeedingScheduleForAnimal(Animal animal) {
		FeedingSchedule feedingSchedule = new FeedingSchedule();
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM FEEDING_SCHEDULES WHERE "
					+ "schedule_id=(SELECT schedule_id FROM animals WHERE "
					+ "animalid=?)";

			statement = connection.prepareStatement(sql);
			statement.setLong(1, animal.getAnimalID());
			
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				feedingSchedule.setScheduleId(rs.getLong("schedule_id"));
				feedingSchedule.setFeedingTime(rs.getString("feeding_time"));
				feedingSchedule.setRecurrence(rs.getString("recurrence"));
				feedingSchedule.setFood(rs.getString("food"));
				feedingSchedule.setNotes(rs.getString("notes"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return feedingSchedule;
	}

	@Override
	public boolean addFeedingSchedule(FeedingSchedule feedingSchedule) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO FEEDING_SCHEDULES "
				+ "(feeding_time, recurrence, food, notes) "
				+ "VALUES (?, ?, ?, ?)";

			statement = connection.prepareStatement(sql);
			
			statement.setString(1, feedingSchedule.getFeedingTime());
			statement.setString(2, feedingSchedule.getRecurrence());
			statement.setString(3, feedingSchedule.getFood());
			statement.setString(4, feedingSchedule.getNotes());
			
			updatedSuccessfully = statement.executeUpdate() != 0;
			return updatedSuccessfully ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	@Override
	public boolean updateFeedingSchedule(FeedingSchedule feedingSchedule) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE FEEDING_SCHEDULES SET feeding_time=?, "
					+ "recurrence=?, food=?, notes=? WHERE schedule_id=?";
			
			statement = connection.prepareStatement(sql);
			statement.setString(1, feedingSchedule.getFeedingTime());
			statement.setString(2, feedingSchedule.getRecurrence());
			statement.setString(3, feedingSchedule.getFood());
			statement.setString(4, feedingSchedule.getNotes());
			statement.setLong(5, feedingSchedule.getScheduleId());

			updatedSuccessfully = statement.executeUpdate() != 0;
			return updatedSuccessfully ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}

	@Override
	public boolean removeFeedingScheduleById(long id) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM FEEDING_SCHEDULES WHERE schedule_id=?";

			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);

			updatedSuccessfully = statement.executeUpdate() != 0;
			return updatedSuccessfully ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}

	@Override
	public boolean setFeedingScheduleForAnimal(Long animalID, Long scheduleID) {
			try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE ANIMALS SET schedule_id=? WHERE animalid=?";
			
			statement = connection.prepareStatement(sql);
			statement.setLong(1, scheduleID);
			statement.setLong(2, animalID);

			updatedSuccessfully = statement.executeUpdate() != 0;
			return updatedSuccessfully ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}

	@Override
	public boolean removeFeedingScheduleForAnimal(Long animalID) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE ANIMALS SET schedule_id=null WHERE animalid=?";

			statement = connection.prepareStatement(sql);
			statement.setLong(1, animalID);

			updatedSuccessfully = statement.executeUpdate() != 0;
			return updatedSuccessfully ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}

	private void closeResources() {
		try {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void closeResources(Statement staticStatement) {
		try {
			if (staticStatement != null) {
				staticStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
