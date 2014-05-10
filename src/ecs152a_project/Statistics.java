package ecs152a_project;

public class Statistics {
	private int serverBusyTime;
	private int serverTotalTime;
	private int numPackets;
	private int numDroppedPackets;
	
	public Statistics(){
		serverBusyTime = 0;
		serverTotalTime = 0;
		numPackets = 0;
		numDroppedPackets = 0;
	}
	public int getServerBusyTime() {
		return serverBusyTime;
	}
	public void setServerBusyTime(int inTime) {
		this.serverBusyTime = inTime;
	}
	public int getServerTotalTime() {
		return serverTotalTime;
	}
	public void setServerTotalTime(int serverTotalTime) {
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
	public void outputStats(){
		double serverBusyFraction = (double)serverBusyTime/(double)serverTotalTime;
		
		System.out.print("Server Busy Time: " + serverBusyTime + "\nServer Total Time: " + serverTotalTime + 
				"\nPackets Dropped: " + numDroppedPackets + "\nServer Busy Fraction: " + serverBusyFraction);
	}
}
