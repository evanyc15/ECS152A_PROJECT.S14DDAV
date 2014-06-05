package ecs152a_project;

public class Node {
	private double packetSize;
	private int destinationHost;
	private double creationTime;

	public Node(double inData){
		packetSize = inData;
	}
	public Node(double inData,int inHost,double increationTime){
		packetSize = inData;
		destinationHost = inHost;
		creationTime = increationTime;
	}
	
	public double getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(double creationTime) {
		this.creationTime = creationTime;
	}
	public int getDestinationHost() {
		return destinationHost;
	}
	public void setDestinationHost(int destinationHost) {
		this.destinationHost = destinationHost;
	}
	public double getPacketSize(){
		return packetSize;
	}
}
