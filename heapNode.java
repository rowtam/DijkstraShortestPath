/*
Name: Ronnie Tam
Date: April 13, 1999
Class: CS 241 Spring 1999
Lab 4: Dijkstra's Shortest Path Algorithm
*/

public class heapNode {

	public Object storedObject;
	public double key;
	public BinaryHeap.Handle h;

	public heapNode(Object k,BinaryHeap.Handle h,double key) {
		storedObject = k;
		this.key = key;
		this.h = h;
	}

}