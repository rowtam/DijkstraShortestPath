/*
Name: Ronnie Tam
Date: April 13, 1999
Class: CS 241 Spring 1999
Lab 4: Dijkstra's Shortest Path Algorithm
*/

public class Vertex {

    // This is a linked list class that contains a linked list of vertices adjacent
    // to SourceNode.

	private VertexNode list;
	public int SourceNode;
	public Vertex parent;
	private BinaryHeap.Handle handle;
	public double distance = Double.POSITIVE_INFINITY;
	public boolean removed = false; // removed flag for Dijkstra
	public boolean primsRemoved = false; // removed flag for Prims
	public Vertex primsParent;

	public Vertex(int SourceNode) {
	    list = new VertexNode(null);     // "head" pointer.
		handle = null;                   // handle is set elsewhere;
		this.SourceNode = SourceNode;
		parent = null;
		primsParent = null;
	}

	// adds a vertex to the list
	public void addtoList(Vertex v1, int VertexID,double weight) {
		list.next = new VertexNode(v1,VertexID,weight,list.next);
	}

	public VertexNode getVertexNodeList() {
		return list;
	}

	public void setHandle(BinaryHeap.Handle h) {
		handle = h;
	}

	public void setParent(Vertex parent) {
		this.parent = parent;
	}

    public void setprimsParent(Vertex primsParent) {
        this.primsParent = primsParent;
    }

	public BinaryHeap.Handle getHandle() {
	    return handle;
	}

	public void setRemoved(boolean b) {
		removed = b;
	}

	public String toString() {
		VertexNode cur = list;
		String s = "";
		while (cur.next != null) {
			s += (""+cur.next);
			cur = cur.next;
		}
		return s;
	}
}