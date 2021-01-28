/*
Name: Ronnie Tam
Date: April 13, 1999
Class: CS 241 Spring 1999
Lab 4: Dijkstra's Shortest Path Algorithm
*/

// This class contains an array of Vertices (linked lists).
// and stores the adjacency list.
// Prims and Dijkstra's algorithms are fully implemented

// IMPORTANT INVARIANTS:
// The index of the array is the Vertex ID associated with the linked list.
// For example: List[1] is a reference to the Vertex class containing a linked
// list of vertices adjacent to Vertex 1.  Furthermore, since Vertices are
// identified by an number from 1 to n, I have again decided to leave
// element 0 of the array empty.  The TA said this was okay.

public class WeightedUndirectedGraph {

	private Vertex[] List;
	private int size, SourceVertex;
	private boolean fullyconnected = true;
	double cost = 0;

	public WeightedUndirectedGraph(int size, int SourceVertex) {
		size++;     // increment the size since element 0 is empty
		this.size = size;
		this.SourceVertex = SourceVertex;
		List = new Vertex[size];
		int i = 1;
		while(i < size) {
			List[i] = new Vertex(i);
			i++;
		}
	}

	// Method used to add edge to the graph.
	public void addEdge(int node1, int node2, double weight) {
		List[node1].addtoList(List[node2],node2, weight);
		List[node2].addtoList(List[node1],node1, weight);
	}

	// Dijkstra's Shortest Path Algorithm
	public void Dijkstra() {
		BinaryHeap bh = new BinaryHeap(size);
		for(int i = 1; i < size; i++) {
			if(i != SourceVertex) {
				BinaryHeap.Handle h = bh.insert(List[i],Double.POSITIVE_INFINITY);
				List[i].setHandle(h);
			}
		}
		BinaryHeap.Handle h = bh.insert(List[SourceVertex], 0);
		List[SourceVertex].setHandle(h);
		while(bh.getHeapSize() != 0) {
			double key = bh.minimumKey();
			heapNode hn = bh.extractMin();
			if(key == Double.POSITIVE_INFINITY) {
				return;
			}
			Vertex temp = (Vertex)hn.storedObject;
			temp.setRemoved(true);
			temp.distance = (double)hn.key;
			VertexNode vnode = temp.getVertexNodeList();
			while(vnode.next != null) {
				if(!vnode.next.vertex.removed) {
					if(bh.decreaseKey(vnode.next.vertex.getHandle(),hn.key+vnode.next.edgeWeight)) {
                        vnode.next.vertex.setParent(temp);
					}
				}
				vnode = vnode.next; // move to the next node in the adjacency list
			}
		}
	}

    // Prims Minimal Spanning Tree Algorithm
	public void PrimsMST() {
		BinaryHeap bh = new BinaryHeap(size);
		for(int i = 1; i < size; i++) {
			if(i != SourceVertex) {
				BinaryHeap.Handle h = bh.insert(List[i],Double.POSITIVE_INFINITY);
				List[i].setHandle(h);
			}
		}
		BinaryHeap.Handle h = bh.insert(List[SourceVertex], 0);
		List[SourceVertex].setHandle(h);
		while(bh.getHeapSize() != 0) {
			double key = bh.minimumKey();
			heapNode hn = bh.extractMin();
			Vertex temp = (Vertex)hn.storedObject;
			if(key == Double.POSITIVE_INFINITY) {
			    fullyconnected = false; // Not fully connected
				return;
			}
			temp.primsRemoved = true;
			cost += hn.key;
			VertexNode vnode = temp.getVertexNodeList();
			while(vnode.next != null) {
				if(!vnode.next.vertex.primsRemoved) {
					if(bh.decreaseKey(vnode.next.vertex.getHandle(),vnode.next.edgeWeight)) {
                        vnode.next.vertex.setprimsParent(temp);
					}
				}
				vnode = vnode.next; // move to the next node in the adjacency list
			}
		}
	}

	public void print() {
		int i = 1;
		Terminal.println("Shortest Path Output:");
		while(i < size) {
			if(List[i].distance != Double.POSITIVE_INFINITY) {
				Terminal.print("For vertex "+i+", distance is "+(float)List[i].distance+" (via path ");
				Vertex temp = List[i];
				String s = "";
				while(temp != null) {
					s = (" "+temp.SourceNode+s);
					temp = temp.parent;
				}
				Terminal.println(s+")");
			}else {
				Terminal.println("Vertex "+i+" is not reachable from vertex "+SourceVertex);
			}
			i++;
		}
		i = 1;
		Terminal.println("MST Output:");
		if(fullyconnected) {
		    Terminal.println("The MST has cost "+(float)cost+" and contains the edges:");
    		while(i < List.length) {
   	    	    if(List[i].primsParent != null) {
       	    	    Terminal.println("("+List[i].SourceNode+","+List[i].primsParent.SourceNode+")");
	            }
    		    i++;
	    	}
		} else
		    Terminal.println("Graph is not fully connected, no spanning tree exists.");
		int k = 1;
		Terminal.println("Here are the adjacency lists to demonstrate that this graph is being properly represented.");
		Terminal.println("Adjacency Lists used in this Graph given by [VertexID, weight]:");
		while(k < List.length) {
			Terminal.println("Vertex "+k+":"+List[k]);
			k++;
		}
	}
}