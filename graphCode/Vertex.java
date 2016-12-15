package graphCode;

import java.util.LinkedList;

/**
 * Class that represents a vertex in a graph. A name (usually a string, but it
 * can be an arbitrary object) can be associated with the vertex.
 * 
 * Data (also represented by an object (e.g., a string)) can also be associated
 * with a vertex. This could be useful, for example, if you need to mark a
 * vertex as being visited in some graph traversal.
 */
public class Vertex {
	/** the edge list for this vertex */
	LinkedList incidentEdgeList;

	private int index; // an object associated with this vertex
	private String name; // a name associated with this vertex
	private Edge edgeTo;

	/**
	 * Constructor that allows data and a name to be associated with the vertex.
	 * 
	 * param data: an object to be associated with this vertex
	 * param name: a name to be associated with this vertex
	 */
	public Vertex(int index, String name) {
		this.index = index;
		this.name = name;
		this.incidentEdgeList = new LinkedList();
		this.edgeTo = null;
	}

	/**
	 * Return the name associated with this vertex.
	 * @return the name of this vertex
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Return the data associated with this vertex.
	 * @return the data of this vertex
	 */
	public int getData() {
		return this.index;
	}

	/**
	 * Set the data associated with this vertex.
	 * param data: the data of this vertex
	 */
	public void setData(int index) {
		this.index = index;
	}

	public void setEdgeTo(Edge e) {
		this.edgeTo = e;
	}

	public Edge getEdgeTo() {
		return this.edgeTo;
	}
}
