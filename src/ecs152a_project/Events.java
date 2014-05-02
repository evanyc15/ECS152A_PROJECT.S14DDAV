package ecs152a_project;

public class Events {
	private String type;
	private int eventTime;
	
	Events(){
		type = null;
		eventTime = -1;
	}
	Events(String inType){
		type = inType;
		eventTime = -1;
	}
	Events(int inTime){
		type = null;
		eventTime = inTime;
	}
	Events(String inType, int inTime){
		type = inType;
		eventTime = inTime;
	}
	public String getType(){
		return type;
	}
	public int getTime(){
		return eventTime;
	}
}
