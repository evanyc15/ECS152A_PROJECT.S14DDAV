package ecs152a_project;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;

public class Controller {
	
	public static void main( String[] args ){
		double lambda1P1[] = {0.1,0.25,0.4,0.55,0.65,0.80,0.90};
		double lambda2P1[] = {0.2,0.4,0.6,0.8,0.9};
		int iterationsP2[] = {1,10,50,1000};
		double lambdaP2[] = {0.01,0.05,0.1,0.2,0.3,0.5,0.6,0.7,0.8,0.9};
		int hostNumsP2[] = {10,25};
		double mu = 1.0;
		int[] MAXBUFFER = {-1,1,20,50}; //-1 means infinite
		Statistics stats = new Statistics();

		// First two for loops are to emulate Phase 1
		/*for(int i = 0; i < lambda1P1.length; i++){
			calculateP1(lambda1P1,MAXBUFFER,mu,i,0);
		}
		for(int i = 0; i < lambda2P1.length; i++){
			for(int j = 1; j < MAXBUFFER.length; j++){
				calculateP1(lambda2P1,MAXBUFFER,mu,i,j);
			}
		}*/
		// Phase 2
		for(int i = 0; i < iterationsP2.length; i ++){
			for(int j = 0; j < hostNumsP2.length; j++){
				for(int k = 0; k < lambdaP2.length; k++){
					//System.out.print("\n"+negative_exponentially_distributed_time(lambdaP2[k])+" of lambda: "+lambdaP2[k] + " random of: "+(Math.random()*1+0));
					calculateP2(iterationsP2[i],hostNumsP2[j],lambdaP2[k],stats);
					System.out.print("Iterations : "+ iterationsP2[i]+", NumHosts: " + hostNumsP2[j]+", Lambda: "+lambdaP2[k]+", Total Average Throughput: "+(stats.getTotalThroughput()/stats.getServerTime())+", Total Delay: "+(stats.getTotalQueueDelay()+stats.getServerTime())+" seconds\n");
				}
			}
		}
		
	} // end main 
	
	//This is the main portion of the program. It computes everything
	/*---------------------------------------------------------------------------------------*/
	public static void calculateP1(double[] lambda, int[] MAXBUFFER, double mu, int lambdaNum,int bufferNum){
		double currentTime = 0;
		double nextArrivTime;
		int queueSize = 0;	//This is the 'length' in the instructions
		GEL eventList = new GEL();
		Queue<Node> packetQueue = new LinkedList<Node>(); //Queue represents the packets, every time we have a new packet, we insert into Queue
		Statistics stats = new Statistics();
		
		//currentTime = currentTime + negative_exponentially_distributed_time(lambda[lambdaNum]);
		currentTime = currentTime + ParetoDistr(lambda[lambdaNum]);
		
		Events FirstEvent = new Events("arrival",currentTime);
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
				//nextArrivTime = currentTime + negative_exponentially_distributed_time(lambda[lambdaNum]);
				nextArrivTime = currentTime + ParetoDistr(lambda[lambdaNum]);
				
				//Create new packet (insert in queue)
				Node packet = new Node(negative_exponentially_distributed_time(mu));
				
				Events arrEvent = new Events("arrival",nextArrivTime);
				eventList.insert(arrEvent);
				
				//Processing Arrival Event
				//Server is free
				/*---------------------------------------------------------------------------------------*/
				if(queueSize == 0){
					Events depEvent = new Events("departure",currentEvent.getTime() + packet.getPacketSize());
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
					Events depEvent = new Events("departure",currentTime + temp.getPacketSize());
					eventList.insert(depEvent);
					stats.calcBusyTime(currentEvent.getTime(), depEvent.getTime());
					stats.setServerTotalTime(depEvent.getTime());
				}
			}
		} 
		stats.outputStats(lambda[lambdaNum],mu,MAXBUFFER[bufferNum]);
		//System.out.print("\n" + ParetoDistr(lambda[lambdaNum]) + "\n");
	}
	public static void calculateP2(int iterations, int numHosts, double lambda, Statistics stats){
		TokenRing tokenRing = new TokenRing(numHosts,lambda);
		
		//System.out.print("\nProject Phase II with Iterations: "+iterations+", NumHosts: "+numHosts+", Lambda: "+lambda+"\n");
		
		stats.setServerTime(75);
		//serverTime = negative_exponentially_distributed_time(lambda)*5;
		
		for(int i = 0; i < iterations; i++){
			for(int j = 0; j < numHosts; j++){
				Hosts currentHost = tokenRing.getHost(j);
				currentHost.setToken(true);
				currentHost.setLastTokenPassedTime(stats.getServerTime());
				currentHost.retrieveNewPackets();
				currentHost.setCurrentTime(stats.getServerTime());
				
				//System.out.print("Host: "+currentHost.getHostNum()+" has "+currentHost.getQueueSize()+" packets in Queue\n");
				
				if(currentHost.getQueueSize() > 0){
					//Creates frame to move through token ring
					LinkedList<Node> frame = new LinkedList<Node>();
					stats.setFrameLength(0.0);
					for(int k = 0; k < currentHost.getQueueSize(); k++){
						Node tempNode1 = currentHost.removePacket();
						//This is to find the total size in bytes of frame (not how many packets are in it!!)
						stats.addFrameLength(tempNode1.getPacketSize());
						frame.add(tempNode1);
					}
					//This is traversing frame through tokenRing
					int currentHostNum = currentHost.getHostNum();
					int traverseHostNum = currentHostNum + 1; //Increment to begin on next host for frame transmitting
					if(traverseHostNum == numHosts){
						traverseHostNum = 0;
					}
					
					//System.out.print("\nCurrentNode: " + currentHostNum+"\n");
					
					while(traverseHostNum != currentHostNum){
						
						//System.out.print("Traversing Node: " + traverseHostNum+"\n");
						
						stats.addServerTime(0.00001);
						if(frame.size() > 0){
							//Computing total Throughput (without dividing server time) of iteration
							stats.addTotalThroughput(stats.getFrameLength());
							//Transmission delay time
							
							//System.out.print("Transmission delay: " + (frameLength/(double)13107200) + "\n");
							
							stats.addServerTime((stats.getFrameLength()/(double)12500000));
							//This loop is traversing the frame to see if any of the packets has a destination for temp2 host
							for(int k = 0; k < frame.size(); k++){
								Node tempNode2 = frame.get(k);
								
								//System.out.print("Destination Host: "+tempNode2.getDestinationHost()+"\n");
								
								if(traverseHostNum == tempNode2.getDestinationHost()){
									Node tempFrameNode1 = frame.remove(k); //Removes it from frame
									stats.addTotalQueueDelay(stats.getServerTime() - tempFrameNode1.getCreationTime());
									//Do something here because it matches
									stats.subFrameLength(tempFrameNode1.getPacketSize());
								}
							}
						}
						if(traverseHostNum >= numHosts-1){
							traverseHostNum = 0;
						}
						else{
							traverseHostNum++;
						}
					}
					stats.addServerTime(0.00001);  //For the last link right before currentHostNum is reached
				}
				else{
					//Set to false before passing token to next Host
					currentHost.setToken(false);
				}
			}
		}
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
	
	//Phase II Uniform Distribution
	public static double UniDistr(){
		return Math.random()*1518+64;
	}
}
