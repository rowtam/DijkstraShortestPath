/*
Name: Ronnie Tam
Date: April 13, 1999
Class: CS 241 Spring 1999
Lab 4: Dijkstra's Shortest Path Algorithm
*/

public class BinaryHeap {

	heapNode[] heapArray;
	int heapsize;
	int smallest = 1;

	// IMPORTANT INVARIANT NOTE:
	// Although it isn't necessary, I left element
	// zero of the array empty, and starting filling
	// at element 1.  The TA said this was okay.

	public BinaryHeap(int i) {
		heapArray = new heapNode[i+1];
	}

	public boolean decreaseKey(Handle h, double k) {
		if(h.index < 1 || heapArray[h.index].key <= k)
			return false;
	    int loc = h.index;
		heapArray[loc].key = k;
		while(loc > 1 && heapArray[Parent(loc)].key > k) {
			swap(loc,Parent(loc));
			loc = Parent(loc);
		}
		return true;
	}

	public heapNode extractMin() {
		if(heapsize < 1) {
			Terminal.println("heap underflow");
		}
		heapNode min = heapArray[1];
		heapArray[1] = heapArray[heapsize];
		heapsize--;
		Heapify(1);
		return min;
	}

	public void Heapify(int i) {
		int l = Left(i);
		int r = Right(i);
		if(l <= heapsize && heapArray[l].key < heapArray[i].key)
			smallest = l;
		else
			smallest = i;
		if(r <= heapsize && heapArray[r].key < heapArray[smallest].key)
			smallest = r;
		if(smallest != i) {
			swap(i,smallest);
			Heapify(smallest);
		}
	}

	public Handle insert(Object k, double key) {
		heapsize++;
		int i = heapsize;
		BinaryHeap.Handle h = new BinaryHeap.Handle(i);
		heapArray[i] = new heapNode(k,h,key);
		while(i > 1 && heapArray[Parent(i)].key > key) {
			swap(i,Parent(i));
			i = Parent(i);
		}
		return h;
	}

	public Object getData(Handle h) {
		return heapArray[h.index].storedObject;
	}

	public int getHeapSize() {
		return heapsize;
	}

	public double getKey(Handle h) {
		return heapArray[h.index].key;
	}

	public double minimumKey() {
		return heapArray[1].key;
	}

	private int Parent(int i) {
		return (int)(Math.floor(i/2));
	}

	private int Left(int i) {
		return 2*i;
	}

	private int Right(int i) {
		return ((2*i)+1);
	}

	private void swap(int i, int j) {
		heapNode temp = heapArray[i];
		heapNode temp2 = heapArray[j];
		heapArray[j] = temp;
		heapArray[j].h.setIndex(j);
		heapArray[i] = temp2;
		heapArray[i].h.setIndex(i);
	}

	public void print() {
		Terminal.println("Printing out Heap");
		for(int i = 1;i < heapsize + 1;i++) {
			Terminal.print("Key: "+heapArray[i].key);
			Terminal.print(" index stored in Handle: "+heapArray[i].h.index);
			Terminal.println(" index of array: "+i);
		}
	}

	//This is the Handle class
	public class Handle {
    	private int index;

	    public Handle(int index) {
		    this.index = index;
    	}

    	private void setIndex(int i) {
    	    index = i;
    	}
    }
}