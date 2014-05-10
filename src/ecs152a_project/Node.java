package ecs152a_project;

public class Node {
	private int packetNum;
	private double packetSize;

	public Node(int inNum,double inData){
		packetNum = inNum;
		packetSize = inData;
	}
	public double getPacketSize(){
		return packetSize;
	}
	public int getPacketNum(){
		return packetNum;
	}
}
