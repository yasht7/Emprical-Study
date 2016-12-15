package graphCode;
/**
 * Class that represents an edge in a graph. An object can be associated with the edge.
 * 
 * A label also represented by an object (e.g., a string) can also be
 * associated with an edge. This could be useful, for example, if you need to
 * mark an edge as being visited in some graph traversal.
 */
public class Edge {
	/** the first endpoint of the edge */
	private Vertex v1;

	/** the second endpoint of the edge */
	private Vertex v2;

	private Double capacity; // an object associated with this edge
	private int name; // a name associated with this edge
	private Double flow;

	/**
	 * Constructor that allows data and a name to be associated with the edge.
	 * 
	 * @param v: the first endpoint of this edge
	 * @param w: the second endpoint of this edge
	 * param data: data to be associated with this edge
	 * @param name: a name to be associated with this edge
	 */
	public Edge(Vertex v, Vertex w, Double capacity, int name) {
		this.capacity = capacity;
		this.flow = 0.0;
		this.name = name;
		this.v1 = v;
		this.v2 = w;
	}

	/**
	 * Return the first endpoint of this edge.
	 */
	public Vertex getFirstEndpoint() {
		return this.v1;
	}

	/**
	 * Return the second endpoint of this edge.
	 */
	public Vertex getSecondEndpoint() {
		return this.v2;
	}

	/**
	 * Return the data associated with this edge.
	 */
	public Double getData() {
		return this.capacity;
	}

	/**
	 * Set the data associated with this edge.
	 */
	public void setData(Double capacity) {
		this.capacity = capacity;
	}

	/**
	 * Return the name associated with this edge.
	 */
	public int getName() {
		return this.name;
	}

	/**
	 * Incrementing the flow
     * */
	public void addFlow(Double flow) {
		this.flow += flow;
	}

	/**
	 * Decrementing the flow
     * */
	public void minusFlow(Double flow) {
		this.flow -= flow;
	}

	/**
	 * Setting the flow
     * */
	public void setFlow(Double flow){
		this.flow = flow;
	}

    /**
     * Getting the flow
     * */
	public Double getFlow(){
		return this.flow;
	}

	/**
     * Returning the remaining flow
     * */
	public Double getRemainedFlow() {
		return this.capacity - this.flow;
	}

	/**
     * Set Vertex : Used in Reversing
     * */
	public void setV1(Vertex theV1){
		this.v1 = theV1;
	}

    /**
     * Set Vertex : Used in Reversing
     * */
	public void setV2(Vertex theV2){
		this.v2 = theV2;
	}

}
