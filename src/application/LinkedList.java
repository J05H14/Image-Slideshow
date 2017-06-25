package application;

import java.io.File;

public class LinkedList{

	protected Node head;
	private int size = 0;
	
	public LinkedList(){
		this.head = null;
	}
	public LinkedList(File image){
		add(image);
	}
	public LinkedList(File[] images){
		for(File image : images){
			add(image);
		}
	}
	
	public void add(File image) {
		Node newNode = new Node(image);
		
		if(this.size == 0){
			this.head = newNode;
		}
		
		else{
			Node curr = this.head;
			while(curr != null){
				curr = curr.next;
			}
			newNode.prev = curr;
			curr.next = newNode;
		}
		this.size++;
	}
}
