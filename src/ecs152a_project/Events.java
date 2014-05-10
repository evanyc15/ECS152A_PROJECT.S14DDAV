package ecs152a_project;

import java.util.Collections;
import java.util.Comparator;

public class Events {
	private String type;
	private int eventNumber;
	private double eventTime;
	
	Events(){
		type = null;
		eventNumber = -1;
		eventTime = -1;
		serviceTime = 0;
	}
	Events(String inType, int inNum, double inTime){
		type = inType;
		eventNumber = inNum;
		eventTime = inTime;
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
