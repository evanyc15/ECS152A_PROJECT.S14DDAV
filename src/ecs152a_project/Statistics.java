package ecs152a_project;

public class Statistics {
	private int serverRunningTime;
	private int serverTotalTime;
	private int numPackets;
	private int numDroppedPackets;
	
	public Statistics(){
		serverRunningTime = 0;
		serverTotalTime = 0;
		numPackets = 0;
		numDroppedPackets = 0;
	}
	public int getServerRunningTime() {
		return serverRunningTime;
	}
	public void setServerRunningTime(int serverRunningTime) {
		this.serverRunningTime = serverRunningTime;
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
}
