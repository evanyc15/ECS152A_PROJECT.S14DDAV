/*  The GEL is the Global Event List. This is a double
 *  linked list which holds all the events. It also
 *  maintains the events in ascending order of time.
 *  GEL is only used in Phase 1
 */

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
    // Insert new event into the GEL. Call sorting function to maintain ascending order
	public void insert(Events event){
		
		GelList.add(event);
		Collections.sort(GelList,new EventsComp());
	}
	public Events getNode(int index){
		return GelList.get(index);
	}
	public Events removeFirstEvent(){
		return GelList.removeFirst();
	}
	public void printGel(){
		for(int i = 0; i < GelList.size(); i++){
			Events temp = GelList.get(i);
			System.out.print(temp.getTime()+" ");
		}
	}
}
