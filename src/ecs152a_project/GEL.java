package ecs152a_project;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

public class GEL {
	private LinkedList<Events> GelList;
	
	GEL(){
		GelList = new LinkedList<Events>();
	}
	public void insert(Events event){
		boolean added = false;
		
		GelList.add(event);
		
		/*if(GelList.size() == 0){
			GelList.add(event);
		}
		else{
			for(int i = 0; i < GelList.size() - 1 && added == false; i++){
				Events currentEvent = GelList.get(i);
				Events nextEvent = GelList.get(i+1);
				if(currentEvent.getTime() < event.getTime() && event.getTime() <= nextEvent.getTime()){
					GelList.add(i,event);
					added = true;
				}
				else{
					if(i == 0){
						GelList.addFirst(event);
						added = true;
					}
					else{
						GelList.add(i-1,event);
						added = true;
					}
				}
			}
			if(added == false){
				GelList.addLast(event);
			}
		}*/
	}
	public Events removeFirst(){
		Events firstEvent;
		firstEvent = GelList.removeFirst();
		return firstEvent;
	}
	public void printGel(){
		for(int i = 0; i < GelList.size(); i++){
			Events temp = GelList.get(i);
			System.out.print(temp.getTime()+" ");
		}
	}
}
