/*
Name: Ronnie Tam
Date: April 13, 1999
Class: CS 241 Spring 1999
Lab 4: Dijkstra's Shortest Path Algorithm
*/

// This class is a linked list node in the adjacency list.

public class VertexNode {
	private int VertexID;
	public VertexNode next;
	public double edgeWeight;
	public Vertex vertex;

	public VertexNode(Vertex vertex, int VertexID, double edgeWeight, VertexNode next) {
		this.vertex = vertex;
		this.VertexID = VertexID;
		this.edgeWeight = edgeWeight;
		this.next = next;
	}
	public VertexNode(VertexNode next) {
		this.next = next;
	}

	public int getVertexID() {
		return VertexID;
	}
	public String toString() {
		return ("["+VertexID+","+edgeWeight+"]");
	}
}