class Edge {
	public Vertex v1;
	public Vertex v2;

	public Edge(Vertex v1,Vertex v2) {
		this.v1=v1;
		this.v2=v2;
	}
	public String toString() {
		return ("("+v1.SourceNode+","+v2.SourceNode+")");
	}
}