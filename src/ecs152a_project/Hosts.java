package ecs152a_project;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Hosts {
	private int totalHosts;
	private int hostNum;
	private boolean Token;
	private double lastTokenPassedTime;
	double currentTime;
	private double lambda;
	Queue<Node> packetQueue; //= new LinkedList<Node>();
	private double queueDelay;
	
	Hosts(int inNum, int totalNum, boolean inToken, double inlambda){
		queueDelay = 0.0;
		lastTokenPassedTime = 0.0;
		currentTime = 0.0;
		hostNum = inNum;
		totalHosts = totalNum;
		Token = inToken;
		lambda = inlambda;
		packetQueue = new LinkedList<Node>(); //Similar to the queue in controller because it holds packets
	}
	public double getQueueDelay() {
		return queueDelay;
	}
	public void addQueueDelay(double queueDelay) {
		this.queueDelay += queueDelay;
	}
	public double getLastTokenPassedTime() {
		return lastTokenPassedTime;
	}
	public void setLastTokenPassedTime(double lastTokenPassedTime) {
		this.lastTokenPassedTime = lastTokenPassedTime;
	}
	public int getHostNum() {
		return hostNum;
	}
	public void setHostNum(int hostNum) {
		this.hostNum = hostNum;
	}
	public boolean isToken() {
		return Token;
	}
	public void setToken(boolean token) {
		Token = token;
	}
	public void addPacket(int inNum, int inSize){
		Node packet = new Node(inSize);
		packetQueue.add(packet);
	}
	public Node removePacket(){
		Node node = packetQueue.remove();
		return node;
	}
	public int getQueueSize(){
		return packetQueue.size();
	}
	public void clearQueue(){
		packetQueue.clear();
	}
	public void retrieveNewPackets(){
		double tempTime = this.currentTime;
		int destinationHost;
		
		while(tempTime <= this.lastTokenPassedTime){
			tempTime += negative_exponentially_distributed_time(lambda);
			if(tempTime <= this.lastTokenPassedTime){
				Random rand = new Random();
				//Find destination host for each packet
				do{
					destinationHost = rand.nextInt((9-0)+1)+0;
				}while(destinationHost == this.hostNum);
				packetQueue.add(new Node(((double)64 + Math.random() * ((double)1518 - (double)64)),destinationHost,tempTime));
			}
		}
	}
	public double getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(double currentTime) {
		this.currentTime = currentTime;
	}
	public static double negative_exponentially_distributed_time(double rate) 
    {
		double u;
		u = Math.random()*1+0;
		return ((-1/rate)*Math.log(1-u));
    }
	
}
