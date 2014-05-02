package ecs152a_project;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

public class GEL {
	private LinkedList<Events> GelList;
	
	GEL(){
		GelList = new LinkedList<Events>();
	}
	public void insert(Events event,int index){
		GelList.add(index,event);
	}
	public Events removeFirst(){
		Events firstEvent;
		firstEvent = GelList.removeFirst();
		return firstEvent;
	}
}
