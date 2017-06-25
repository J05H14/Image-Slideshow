package application;

import java.io.File;

public class Node{

	protected File file;
	
	protected Node next = null;
	protected Node prev = null;
	
	public Node(File file){
		this.file = file;
	}
}
