/*  This class keeps track of all the statistics/data throughout the simulation.
 *  For Phase II, data that we are looking for is throughput (number of bytes transmitted per unit time, equation = # bytes transmitted/ entire simulation time)
 *  and average packet delay which is the delay including queueing delay and transmission delay for each  host and propagation delay between hosts. Propagation delay
 *  was 10 microseconds and channel transmission rate is 100 Mbps.
 */

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
	private double totalFrameLengths;
	private double totalThroughput;
	private double totalQueueDelay;
	private double serverTime;
	private double frameLength;
	
	public Statistics(){
		serverBusyTime = 0;
		serverTotalTime = 0;
		numPackets = 0;
		numDroppedPackets = 0;
		meanQueueLength = 0.0;
		QueueLengthSum = 0.0;
		departurePoint = 0.0;
		previousEventForLength = 0.0;
		totalFrameLengths = 0.0;
		totalThroughput = 0.0;
		totalQueueDelay = 0.0;
		serverTime = 0.0;
		frameLength = 0.0;
	}
	public double getFrameLength() {
		return frameLength;
	}
	public void setFrameLength(double frameLength) {
		this.frameLength = frameLength;
	}
	public void addFrameLength(double frameLength) {
		this.frameLength += frameLength;
	}
	public void subFrameLength(double frameLength) {
		this.frameLength -= frameLength;
	}
	public double getServerTime() {
		return serverTime;
	}
	public void setServerTime(double serverTime) {
		this.serverTime = serverTime;
	}
	public void addServerTime(double serverTime) {
		this.serverTime += serverTime;
	}
	public double getTotalQueueDelay() {
		return totalQueueDelay;
	}
	public void setTotalQueueDelay(double totalQueueDelay) {
		this.totalQueueDelay = totalQueueDelay;
	}
	public void addTotalQueueDelay(double totalQueueDelay) {
		this.totalQueueDelay += totalQueueDelay;
	}
	public double getTotalThroughput() {
		return totalThroughput;
	}
	public void addTotalThroughput(double totalThroughput) {
		this.totalThroughput += totalThroughput;
	}
	public double getTotalFrameLengths() {
		return totalFrameLengths;
	}
	public void setTotalFrameLengths(double totalFrameLengths) {
		this.totalFrameLengths += totalFrameLengths;
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
