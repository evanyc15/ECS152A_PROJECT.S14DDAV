package ecs152a_project;

public class Events {
	private String type;
	private int eventTime;
	private int packetSize;
	
	Events(){
		type = null;
		eventTime = -1;
		packetSize = 0;
	}
	Events(String inType, int inTime, int inSize){
		type = inType;
		eventTime = inTime;
		packetSize = inSize;
	}
	public String getType(){
		return type;
	}
	public int getTime(){
		return eventTime;
	}
	public int getSize(){
		return packetSize;
	}
}
