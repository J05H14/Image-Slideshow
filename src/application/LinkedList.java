package application;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

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
			while(curr.next != null){
				curr = curr.next;
			}
			newNode.prev = curr;
			curr.next = newNode;
		}
		this.size++;
	}
	
	public void clear(){
		this.head = null;
	}
	
	public void deleteFirst(){
		if(size == 0){
			throw new IndexOutOfBoundsException("This List is Empty");
		}
		else{
			this.head = this.head.next;
		}
		
	}
	
	public void deleteLast(){
		if(size == 0){
			throw new IndexOutOfBoundsException("This List is Empty");
		}
		
		Node curr = this.head;
		
		while(curr.next != null){
			curr = curr.next;
		}
		
		curr.prev.next = null;
	}
	
	public void delete(int index){
		if(size == 0){
			throw new IndexOutOfBoundsException("This List is Empty");
		}
		
		if(index == 0){
			deleteFirst();
		}
		else if(index == size - 1){
			deleteLast();
		}
		else{
			Node curr = this.head;
			for(int i = 0; i < index; i++){
				curr = curr.next;
			}
			curr.prev.next = curr.next;
		}
	}
	
	public void shuffle(){
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		Node curr = this.head;
		while(curr.next != null){
			nodes.add(curr);
		}
		Collections.shuffle(nodes);
		
		for(int i = 0; i < nodes.size(); i++){
			add(nodes.get(i).file);
		}
	}
}
