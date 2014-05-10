package ecs152a_project;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Collections;

public class GEL{
	private LinkedList<Events> GelList;
	
	GEL(){
		GelList = new LinkedList<Events>();
	}
	public void insert(Events event){
		boolean added = false;
		
		GelList.add(event);
		Collections.sort(GelList,new EventsComp());
	}
	public Events removeFirst(){
		Events firstEvent;
		firstEvent = GelList.removeFirst();
		return firstEvent;
	}
	public Events getNode(int index){
		return GelList.get(index);
	}
	public void printGel(){
		for(int i = 0; i < GelList.size(); i++){
			Events temp = GelList.get(i);
			System.out.print(temp.getTime()+" ");
		}
	}
}
