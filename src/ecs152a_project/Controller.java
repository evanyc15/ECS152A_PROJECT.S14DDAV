package ecs152a_project;

import java.util.Queue;
import java.util.LinkedList;

public class Controller {
	
	public static void main( String[] args ){
		double lambda1[] = {0.1,0.25,0.4,0.55,0.65,0.80,0.90};
		double lambda2[] = {0.2,0.4,0.6,0.8,0.9};
		double mu = 1.0;
		int[] MAXBUFFER = {-1,1,20,50}; //-1 means infinite
		
		for(int i = 0; i < lambda1.length; i++){
			calculate(lambda1,MAXBUFFER,mu,i,0);
		}
		for(int i = 0; i < lambda2.length; i++){
			for(int j = 1; j < MAXBUFFER.length; j++){
				calculate(lambda2,MAXBUFFER,mu,i,j);
			}
		}
	} // end main 
	
	//This is the main portion of the program. It computes everything
	/*---------------------------------------------------------------------------------------*/
	public static void calculate(double[] lambda, int[] MAXBUFFER, double mu, int lambdaNum,int bufferNum){
		double currentTime = 0;
		double nextArrivTime;
		int queueSize = 0;	//This is the 'length' in the instructions
		GEL eventList = new GEL();
		Queue<Node> packetQueue = new LinkedList<Node>(); //Queue represents the packets, every time we have a new packet, we insert into Queue
		Statistics stats = new Statistics();
		
		currentTime = currentTime + negative_exponentially_distributed_time(lambda[lambdaNum]);
		//currentTime = currentTime + ParetoDistr(lambda[lambdaNum]);
		
		Events FirstEvent = new Events("arrival",0,currentTime);
		eventList.insert(FirstEvent);
		for (int i = 0; i < 100000; i++){ 
			Events currentEvent = eventList.removeFirstEvent();
			//Processing Events
			//Arrival Event
			/*-----------------------------------------------------------------------------------------*/
			if(currentEvent.getType() == "arrival"){
				stats.calcQueueLength(queueSize, currentEvent.getTime());
				//Creating next arrival event
				currentTime = currentEvent.getTime();
				nextArrivTime = currentTime + negative_exponentially_distributed_time(lambda[lambdaNum]);
				//nextArrivTime = currentTime + ParetoDistr(lambda[lambdaNum]);
				
				//Create new packet (insert in queue)
				Node packet = new Node(currentEvent.getNumber(),negative_exponentially_distributed_time(mu));
				
				Events arrEvent = new Events("arrival",currentEvent.getNumber() + 1,nextArrivTime);
				eventList.insert(arrEvent);
				
				//Processing Arrival Event
				//Server is free
				/*---------------------------------------------------------------------------------------*/
				if(queueSize == 0){
					Events depEvent = new Events("departure",currentEvent.getNumber(),currentEvent.getTime() + packet.getPacketSize());
					eventList.insert(depEvent);
					queueSize++;
					stats.calcBusyTime(currentEvent.getTime(), depEvent.getTime());
					stats.setServerTotalTime(depEvent.getTime());
				}
				//Server is busy
				else if(queueSize > 0){
					if(MAXBUFFER[bufferNum] == -1){
						packetQueue.add(packet);
						queueSize++;
					}
					else if(queueSize - 1 < MAXBUFFER[bufferNum]){ //MAXBUFFER INDEX SHOULD BE SET DEPENDING ON FOR LOOP
						packetQueue.add(packet);
						queueSize++;
					}
					else{
						stats.setNumDroppedPackets(stats.getNumDroppedPackets() + 1);
						//DROP PACKET HERE
					}
				}
			}
			//Departure Events
			/*---------------------------------------------------------------------------------------*/
			else if(currentEvent.getType() == "departure"){
				stats.calcQueueLength(queueSize, currentEvent.getTime());
				currentTime = currentEvent.getTime();
				queueSize--;
				if(queueSize > 0){
					Node temp = packetQueue.remove();
					Events depEvent = new Events("departure",temp.getPacketNum(),currentTime + temp.getPacketSize());
					eventList.insert(depEvent);
					stats.calcBusyTime(currentEvent.getTime(), depEvent.getTime());
					stats.setServerTotalTime(depEvent.getTime());
				}
			}
		} 
		stats.outputStats(lambda[lambdaNum],mu,MAXBUFFER[bufferNum]);
		//System.out.print("\n" + ParetoDistr(lambda[lambdaNum]) + "\n");
	}
	
	public static double negative_exponentially_distributed_time(double rate) 
    {
		double u;
		u = Math.random()*1+0;
		return ((-1/rate)*Math.log(1-u));
    }
	
	//Extra Credit Pareto Distribution
	public static double ParetoDistr(double rate){
		double k = (double)1.0/rate;
		double xM = 0.1*k;
		double alpha = -k/(xM - k);
		double u = Math.random()*1+0;
		
		//return alpha*Math.pow(xM,alpha)*Math.pow(u,-(alpha+1));
		return xM/(Math.pow(u, (double)1.0/alpha));
		
	}
}
