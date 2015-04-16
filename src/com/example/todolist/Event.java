package com.example.todolist;

import java.io.Serializable;

public class Event implements Serializable {
	
	
	private String Name;
	private String Date;
	private String Time;
	private String Info;
	
	
	
	public Event(String name, String date, String time, String info) {
		Name = name;
		Date = date;
		Time = time;
		Info = info;
	}
	
	
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public String getInfo() {
		return Info;
	}
	public void setInfo(String info) {
		Info = info;
	}
	

}
