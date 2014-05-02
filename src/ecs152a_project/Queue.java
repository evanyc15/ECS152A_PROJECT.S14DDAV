package ecs152a_project;

public class Queue {
	private Node first;
	private Node last;
	private int numElem;

	protected class Node{
		protected Events data;
		protected Node next;

		public Node(Events inData){
			data = inData;
			next = null;
		}
		public Events getData(){
			return data;
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
	public void enqueue(Events inData){
		Node oldNode = last;

		last = new Node(inData);
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
				System.out.print(reference.data+"| ");
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
