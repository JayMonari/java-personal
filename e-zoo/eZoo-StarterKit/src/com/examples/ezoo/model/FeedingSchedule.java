package com.examples.ezoo.model;

public class FeedingSchedule {

	private long scheduleID;
	private String feedingTime;
	private String recurrence;
	private String food;
	private String notes;
	
	public FeedingSchedule() {}

	// for adding schedules, we don't want the client setting ID's
	// They won't know which are available
	public FeedingSchedule(String feedingTime, String recurrence, String food,
			String notes) {
		this.feedingTime = feedingTime;
		this.recurrence = recurrence;
		this.food = food;
		this.notes = notes;
	}

	// For updating schedules
	public FeedingSchedule(long scheduleId, String feedingTime, String recurrence, String food,
			String notes) {
		this.scheduleID = scheduleId;
		this.feedingTime = feedingTime;
		this.recurrence = recurrence;
		this.food = food;
		this.notes = notes;
	}
	
	public long getScheduleId() {
		return scheduleID;
	}
	
	public void setScheduleId(long scheduleId) {
		this.scheduleID = scheduleId;
	}
	
	public String getFeedingTime() {
		return feedingTime;
	}
	
	public void setFeedingTime(String feedingTime) {
		this.feedingTime = feedingTime;
	}
	
	public String getRecurrence() {
		return recurrence;
	}
	
	public void setRecurrence(String recurrence) {
		this.recurrence = recurrence;
	}
	
	public String getFood() {
		return food;
	}
	
	public void setFood(String food) {
		this.food = food;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
}
