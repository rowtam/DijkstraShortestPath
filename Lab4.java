/*
Name: Ronnie Tam
Date: April 13, 1999
Class: CS 241 Spring 1999
Lab 4: Dijkstra's Shortest Path Algorithm
*/

public class Lab4 {
	public static void main(String[] Args) {
	    Terminal.startTranscript("transcript.txt");
		Terminal.readInputFromFile("testdata.txt");
		int numberOfGraphs = Terminal.readInt();
		int currentGraph = 0;
		while(currentGraph < numberOfGraphs) {
		    Terminal.println("\nProcessing Weighted Undirected Graph #"+(currentGraph+1));
			int NumberofVertices = Terminal.readInt();
			int NumberofEdges = Terminal.readInt();
			int SourceVertex = Terminal.readInt();
			Terminal.println("Source Vertex for this graph is: "+SourceVertex);
			WeightedUndirectedGraph testGraph = new WeightedUndirectedGraph(NumberofVertices, SourceVertex);
			int processedEdges = 0;
			int vertex1;
			int vertex2;
			double weight;
			while(processedEdges < NumberofEdges) { //Loop through the text file and read input
				vertex1 = Terminal.readInt();
				vertex2 = Terminal.readInt();
				weight = Terminal.readDouble();
				testGraph.addEdge(vertex1, vertex2, weight);
				processedEdges++;
			}
			testGraph.Dijkstra();
			testGraph.PrimsMST();
			// Showing that Dijkstra and Prims works
			testGraph.print();
			currentGraph++;
		}
			// Showing that Binary Heap works
			Terminal.println("\nAlthought it isn't necessary, here is BinaryHeap test data");
			Terminal.println("Demonstration of Binary Heap and Handle");
			Terminal.println("Note that the Handle index always matches the array index");
			Terminal.println("Thus the Handle index is being updated properly");
    		BinaryHeap bh = new BinaryHeap(10);
	    	bh.insert(new Vertex(1),16);
    		bh.insert(new Vertex(1),14);
	    	bh.insert(new Vertex(1),10);
    		bh.insert(new Vertex(1),8);
	    	bh.insert(new Vertex(1),7);
    		bh.insert(new Vertex(1),9);
	    	bh.insert(new Vertex(1),3);
		    bh.insert(new Vertex(1),2);
    		bh.insert(new Vertex(1),4);
	    	bh.insert(new Vertex(1),1);
		    bh.print();
		    Terminal.println("Extracting Minimum");
    		bh.extractMin();
	    	bh.print();
	    	Terminal.stopTranscript();
	}
}