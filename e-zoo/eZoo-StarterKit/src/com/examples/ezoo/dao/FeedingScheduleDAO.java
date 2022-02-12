package com.examples.ezoo.dao;

import java.util.List;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

public interface FeedingScheduleDAO {

	//A method to retrieve all feeding schedules from the database
	public List<FeedingSchedule> getAllFeedingSchedules();
	// A method to retrieve a single feeding schedule by its id.
	public FeedingSchedule getFeedingScheduleById(long id);
	//A method to add a given feeding schedule to the database.
	public boolean addFeedingSchedule(FeedingSchedule feedingSchedule);
	//A method to update a given feeding schedule to the database.
	public boolean updateFeedingSchedule(FeedingSchedule feedingSchedule);
	//A method to delete a given feeding schedule to the database. This method should first remove all references to that feeding schedule from the ANIMALS table.
	public boolean removeFeedingScheduleById(long id);
	//A method to retrieve a single feeding schedule from the database for a given animal
	public FeedingSchedule getFeedingScheduleForAnimal(Animal animal);
	//A method to assign a given feeding schedule to a given animal.
	public boolean setFeedingScheduleForAnimal(Long animalID, Long scheduleID);
	//A method to remove a feeding schedule from a given animal.
	public boolean removeFeedingScheduleForAnimal(Long animalID);
}
