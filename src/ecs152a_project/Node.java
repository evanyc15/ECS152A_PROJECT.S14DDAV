package ecs152a_project;

public class Node {
	private double packetSize;
	private int destinationHost;

	public Node(double inData){
		packetSize = inData;
	}
	public Node(double inData,int inHost){
		packetSize = inData;
		destinationHost = inHost;
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
