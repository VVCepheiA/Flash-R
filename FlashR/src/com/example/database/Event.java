package com.example.database;

public class Event {
	String dateTime;
	String eventName;
	
	public Event(){}
	
	public Event(String dateTime, String eventName){
		this.dateTime = dateTime;
		this.eventName = eventName;
	}
	
	public String getEventName(){
		return this.eventName;
	}
	public String getDateTime(){
		return this.dateTime;
	}
	
	public void setDateTime(String dateTime){
		this.dateTime = dateTime;
	}
	public void setEventName(String eventName){
		this.eventName=eventName;
	}

}
