/*  This is the class for the Token Ring object.
 */

package ecs152a_project;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Collections;

public class TokenRing {
	private LinkedList<Hosts> TokenRing;
	
	TokenRing(int numHosts,double lambda){
		TokenRing = new LinkedList<Hosts>();
		for(int i = 0; i < numHosts; i++){ //Starts at 0 rather than 1 for simplicity
			Hosts host = new Hosts(i,numHosts,false,lambda);
			TokenRing.add(host);
		}
	}
	public int getTokenRingSize(){
		return TokenRing.size();
	}
	public Hosts getHost(int index){
		return TokenRing.get(index);
	}
	
}
