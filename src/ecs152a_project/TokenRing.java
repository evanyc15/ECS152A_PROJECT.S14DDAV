package ecs152a_project;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Collections;

public class TokenRing {
	private LinkedList<Hosts> TokenRing;
	
	TokenRing(int numHosts,double lambda){
		TokenRing = new LinkedList<Hosts>();
		for(int i = 1; i <= numHosts; i++){ //Starts at 1 rather than 0 cause its like that in instructions
			Hosts host = new Hosts(i,numHosts,false,lambda);
			TokenRing.add(host);
		}
	}
}
