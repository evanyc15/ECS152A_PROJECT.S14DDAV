package ecs152a_project;

public class Controller {
	public static void main( String[] args ){
		double currentTime = 0;
		double lambda[] = {0.1,0.25,0.4,0.55,0.65,0.80,0.90};
		double mu = 1.0;
		double nextArrivTime;
		double serviceTime;
		GEL eventList = new GEL();
		Queue packetQueue = new Queue();
		Statistics stats = new Statistics();
		
		/*currentTime = currentTime + negative_exponentially_distributed_time(lambda[0]);
		Events event1 = new Events("arrival",1,currentTime,1);
		currentTime = currentTime + negative_exponentially_distributed_time(lambda[0]);
		Events event2 = new Events("arrival",2,currentTime,1);
		currentTime = currentTime + negative_exponentially_distributed_time(lambda[0]);
		Events event3 = new Events("arrival",3,currentTime,1);*/
		
		/*eventList.insert(event1);
		eventList.insert(event2);
		eventList.insert(event3);*/
		
		currentTime = currentTime + negative_exponentially_distributed_time(lambda[0]);
		serviceTime = negative_exponentially_distributed_time(mu);
		Events FirstEvent = new Events("arrival",0,currentTime);
		eventList.insert(FirstEvent);
		for (int i = 1; i < 25; i++){ 
			Events currentEvent = eventList.getNode(i);
			currentTime = currentEvent.getTime();
			if(currentEvent.getType() == "arrival"){
				
				//Creating next arrival event
				nextArrivTime = currentTime + negative_exponentially_distributed_time(lambda[0]);
				Events event = new Events("arrival",i,nextArrivTime);
				eventList.insert(event);
				
				//Create new packet (insert in queue)
				
			}
			else if(currentEvent.getType() == "departure"){
				if(packetQueue.getNumElem() == 0){
					
				}
				else if(packetQueue.getNumElem() > 0){
					
				}
			}
			/*
			currentTime = currentTime + negative_exponentially_distributed_time(lambda[0]);
			Events event = new Events("arrival",0,currentTime,1/*placeholder for now*///);
			//eventList.insert(event);
			
			
		} 
		eventList.printGel();
		//output-statistics; 
		
	} // end main 
	 public static double negative_exponentially_distributed_time(double rate) 
	    {
		 double u;
	     u = Math.random()*1+0;
	     return ((-1/rate)*Math.log(1-u));
	    }

}
