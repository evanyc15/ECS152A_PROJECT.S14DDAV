package ecs152a_project;

import java.util.Collections;
import java.util.Comparator;

// Class for the Events object.
// It has two private properties: Type = arrival or departure event
public class Events {
	private String type;
	private double eventTime;
	
	Events(){
		type = null;
		eventTime = -1;
	}
	Events(String inType, double inTime){
		type = inType;
		eventTime = inTime;
	}
	public String getType(){
		return type;
	}
	public double getTime(){
		return eventTime;
	}
}

// Used to sort the Events. Its implementing the comparator
// This overrides the default compare in order to sort the events
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
