/*  This program simulation is to show how a token ring network works.
 *  A token ring network's computers are known as hosts. This is the class
 *  for the Host object. A host does not release its packets until the
 *  token has been passed to it. If it has packets to transmit upon
 *  receiving the token, then it transmits all of the packets or else
 *  it immediately forwards the token. Hosts are only used in Phase II
 */

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

    //  Host constructor
	Hosts(int inNum, int inHosts, boolean inToken, double inlambda){
		queueDelay = 0.0;
		lastTokenPassedTime = 0.0;
		currentTime = 0.0;
		hostNum = inNum;
		Token = inToken;
		lambda = inlambda;
        totalHosts = inHosts;
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
    // Each host in the network is assigned a number to distinguish it from the other hosts
	public int getHostNum() {
		return hostNum;
	}
	public void setHostNum(int hostNum) {
		this.hostNum = hostNum;
	}
    // Checks if host has the token
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
    // Remember that this simulation is not a completely dynamic one. What this means it in reality, while the token is
    // moving through the network, each host will technically be receiving packets. However, in our simulation for simplicity purposes,
    // the packets' are "generated" when a token arrives at a host. (its like a backwards way of generating things because our server isn't actually running in real-time).
    // When the token arrives, the host gets checked to see how much time has passed since the last token was passed. It then "creates" packets as if they had already arrived.
    // Each packet gets a random length (uniformly distributed between [64, 1518] and a random host node is chosen as the destination.
	public void retrieveNewPackets(){
		double tempTime = this.currentTime;
		int destinationHost;
		
		while(tempTime <= this.lastTokenPassedTime){
			tempTime += negative_exponentially_distributed_time(lambda);
			if(tempTime <= this.lastTokenPassedTime){
				Random rand = new Random();
				//Find destination host for each packet
				do{
					destinationHost = rand.nextInt(((totalHosts-1)-0)+1)+0;
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
