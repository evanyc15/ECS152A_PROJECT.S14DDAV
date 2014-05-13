package ecs152a_project;

public class Statistics {
	private double departurePoint;
	private double serverBusyTime;
	private double serverTotalTime;
	private int numPackets;
	private int numDroppedPackets;
	private double meanQueueLength;
	private double QueueLengthSum;
	private double previousEventForLength;
	
	public Statistics(){
		serverBusyTime = 0;
		serverTotalTime = 0;
		numPackets = 0;
		numDroppedPackets = 0;
		meanQueueLength = 0.0;
		QueueLengthSum = 0.0;
		departurePoint = 0.0;
		previousEventForLength = 0.0;
	}
	public void calcBusyTime(double nextArrive, double nextDept){
		if(nextArrive >= departurePoint){
			serverBusyTime += nextDept - nextArrive;
		}
		else{
			serverBusyTime += nextDept - departurePoint;
		}
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
	public void calcQueueLength(int queueSize, double currentArrivTime){
		QueueLengthSum += (double)queueSize * (currentArrivTime - previousEventForLength);
		previousEventForLength = currentArrivTime;
	}
	public void outputStats(double lambda, double mu, int bufferNum){
		double serverBusyFraction = (double)serverBusyTime/(double)serverTotalTime;
		meanQueueLength = QueueLengthSum/serverTotalTime;
		
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
