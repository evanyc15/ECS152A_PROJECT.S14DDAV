package ecs152a_project;

public class Controller {
	public static void main( String[] args ){
		int currentTime = 0;
		GEL eventList = new GEL();
		Queue packetQueue = new Queue();
		Statistics stats = new Statistics();
		
		Events event = new Events("arrival",0/*placeholder right now for both time and size, look at instructions*/,0);
		eventList.insert(event, 0);
		for (int i = 0; i < 100000; i++){ 
			//1. get the first event from the GEL; 
			//2. If the event is an arrival then process-arrival-event; 
			//3. Otherwise it must be a departure event and hence 
			//process-service-completion; 
		} 
		//output-statistics; 
		
	} // end main 
	public static double neg_expo_distr_time(double rate) { 
		double u; 
		u = Math.random() * 50 + 1; //Placeholder for now
		return ((-1/rate)*Math.log(1-u)); 
	} 

}
