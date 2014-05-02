package ecs152a_project;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

public class GEL {
	private LinkedList<Events> GelList;
	
	GEL(){
		GelList = new LinkedList<Events>();
	}
	public boolean insert(Events event,int index){
		
	}
	public Events removeFirst(){
		Events firstEvent;
		firstEvent = GelList.removeFirst();
		return firstEvent;
	}
}
