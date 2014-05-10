package ecs152a_project;

public class Queue {
	private Node first;
	private Node last;
	private int numElem;

	protected class Node{
		protected int packetNum;
		protected double packetSize;
		protected Node next;

		public Node(int inNum,double inData){
			packetNum = inNum;
			packetSize = inData;
			next = null;
		}
		public double getPacketSize(){
			return packetSize;
		}
		public int getPacketNum(){
			return packetNum;
		}
	}
	public Queue(){
		first = null;
		last = null;
		numElem = 0;
	}
	public boolean isEmpty(){
		if(first == null) return true;
		else{
			return false;
		}
	}
	public int getNumElem(){
		return numElem;
	}
	public void enqueue(int inNum, double inData){
		Node oldNode = last;

		last = new Node(inNum, inData);
		last.next = null;
		if(isEmpty()) first = last;
		else{
			oldNode.next = last;
		}
		numElem++;
	}
	public Node dequeue(){
		Node reference = first;

		if(first == null) return null;

		first = first.next;
		numElem--;
		return reference;
	}
	public void printQueue(){
		if(first == null)
			System.out.println("Queue is empty");
		else{
			Node reference = first;
			for(int i = 0; i < numElem; i++){
				System.out.print("Number"+reference.packetNum +": "+reference.packetSize+"| ");
				reference = reference.next;
			}
			System.out.println("");
		}
	}
	public Node getfront(){
		if(first == null) return null;
		return first;
	}
}
