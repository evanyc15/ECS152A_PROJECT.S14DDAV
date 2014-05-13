package ecs152a_project;

public class Statistics {
	private double arrivalPoint;
	private double departurePoint;
	private double serverBusyTime;
	private double serverTotalTime;
	private int numPackets;
	private int numDroppedPackets;
	private double meanQueueLength;
	
	public Statistics(){
		serverBusyTime = 0;
		serverTotalTime = 0;
		numPackets = 0;
		numDroppedPackets = 0;
		meanQueueLength = 0.0;
		arrivalPoint = 0.0;
		departurePoint = 0.0;
	}
	public void calcBusyTime(double nextArrive, double nextDept){
		if(nextArrive >= departurePoint){
			serverBusyTime += nextDept - nextArrive;
		}
		else{
			serverBusyTime += nextDept - departurePoint;
		}
		arrivalPoint = nextArrive;
		departurePoint = nextDept;
	}
	public double getServerBusyTime() {
		return serverBusyTime;
	}
	public double getServerTotalTime() {
		return serverTotalTime;
	}
	public void setServerTotalTime(double serverTotalTime) {
		this.serverTotalTime = serverTotalTime;
	}
	public int getNumPackets() {
		return numPackets;
	}
	public void setNumPackets(int numPackets) {
		this.numPackets = numPackets;
	}
	public int getNumDroppedPackets() {
		return numDroppedPackets;
	}
	public void setNumDroppedPackets(int numDroppedPackets) {
		this.numDroppedPackets = numDroppedPackets;
	}
	public double getmeanQueueLength(){
		return meanQueueLength;
	}
	public void setmeanQueueLength(double lambda, double mu){
		double p;
		
		p = lambda/mu;
		meanQueueLength = (p*p)/(1-p);
	}
	public void outputStats(double lambda, double mu, int bufferNum){
		double serverBusyFraction = (double)serverBusyTime/(double)serverTotalTime;
		this.setmeanQueueLength(lambda, mu);
		
		if(bufferNum == -1){
			System.out.print("Run with Lambda: " + lambda + ", mu: " + mu + ", and buffer: infinity " + 
					"\n-----------------------------------------------------------------" + 
	 				"\nServer Busy Time: " + serverBusyTime + "\nServer Total Time: " + serverTotalTime + 
					"\nPackets Dropped: " + numDroppedPackets + "\nServer Busy Fraction: " + serverBusyFraction +
					"\nMean Queue Length: " + meanQueueLength + 
					"\n-----------------------------------------------------------------\n\n");
		}
		else{
			System.out.print("Run with Lambda: " + lambda + ", mu: " + mu + ", and buffer: " + bufferNum + 
					"\n-----------------------------------------------------------------" + 
	 				"\nServer Busy Time: " + serverBusyTime + "\nServer Total Time: " + serverTotalTime + 
					"\nPackets Dropped: " + numDroppedPackets + "\nServer Busy Fraction: " + serverBusyFraction +
					"\nMean Queue Length: " + meanQueueLength + 
					"\n-----------------------------------------------------------------\n\n");
		}
	}
}
