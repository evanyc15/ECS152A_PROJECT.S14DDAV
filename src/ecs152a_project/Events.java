package ecs152a_project;

import java.util.Collections;
import java.util.Comparator;

public class Events {
	private String type;
	private int eventNumber;
	private double eventTime;
	private int packetSize;
	
	Events(){
		type = null;
		eventNumber = -1;
		eventTime = -1;
		packetSize = 0;
	}
	Events(String inType, int inNum, double inTime, int inSize){
		type = inType;
		eventNumber = inNum;
		eventTime = inTime;
		packetSize = inSize;
	}
	public String getType(){
		return type;
	}
	public int getNumber(){
		return eventNumber;
	}
	public double getTime(){
		return eventTime;
	}
	public int getSize(){
		return packetSize;
	}
}

class EventsComp implements Comparator<Events>{
    @Override
    public int compare(Events e1, Events e2) {
        if(e1.getTime() > e2.getTime()){
            return 1;
        } else {
            return -1;
        }
    }
}
