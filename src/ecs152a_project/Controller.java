package ecs152a_project;

public class Controller {
	public static void main( String[] args ){
		double currentTime = 0;
		double lambda[] = {0.1,0.25,0.4,0.55,0.65,0.80,0.90};
		GEL eventList = new GEL();
		Queue packetQueue = new Queue();
		Statistics stats = new Statistics();
		
		currentTime = currentTime + negative_exponentially_distributed_time(lambda[0]);
		Events event = new Events("arrival",0,currentTime,1/*placeholder for now*/);
		currentTime = currentTime + negative_exponentially_distributed_time(lambda[0]);
		Events event1 = new Events("arrival",1,currentTime,1/*placeholder for now*/);
		currentTime = currentTime + negative_exponentially_distributed_time(lambda[0]);
		Events event2 = new Events("arrival",2,currentTime,1/*placeholder for now*/);
		currentTime = currentTime + negative_exponentially_distributed_time(lambda[0]);
		Events event3 = new Events("arrival",3,currentTime,1/*placeholder for now*/);
		
		eventList.insert(event);
		eventList.insert(event1);
		eventList.insert(event2);
		eventList.insert(event3);
		
		eventList.printGel();
		/*for (int i = 0; i < 100000; i++){ 
			//1. get the first event from the GEL; 
			//2. If the event is an arrival then process-arrival-event; 
			//3. Otherwise it must be a departure event and hence 
			//process-service-completion; 
		} */
		//output-statistics; 
		
	} // end main 
	 public static double negative_exponentially_distributed_time(double rate) 
	    {
		 double u;
	     u = Math.random()*1+0;
	     return ((-1/rate)*Math.log(1-u));
	    }

}
