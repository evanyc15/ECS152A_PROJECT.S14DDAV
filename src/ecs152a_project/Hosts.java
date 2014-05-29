package ecs152a_project;

import java.util.LinkedList;
import java.util.Queue;

public class Hosts {
	private int hostNum;
	private boolean Token;
	private double Time;
	private double lambda;
	Queue<Node> packetQueue; //= new LinkedList<Node>();
	
	Hosts(int inNum, boolean inToken, double inlambda){
		Time = 0.0;
		hostNum = inNum;
		Token = inToken;
		lambda = inlambda;
		packetQueue = new LinkedList<Node>(); //Similar to the queue in controller because it holds packets
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
		Node packet = new Node(inNum,inSize);
		packetQueue.add(packet);
	}
	public Node removePacket(){
		Node node = packetQueue.remove();
		return node;
	}
	public int getQueueSize(){
		return packetQueue.size();
	}
	public static double negative_exponentially_distributed_time(double rate) 
    {
		double u;
		u = Math.random()*1+0;
		return ((-1/rate)*Math.log(1-u));
    }
	
}
