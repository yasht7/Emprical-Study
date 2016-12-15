package graphCode;

import java.util.Iterator;
import java.util.LinkedList;

public class SimpleGraph {

    LinkedList vertexList;
    LinkedList edgeList;

    public SimpleGraph() {
        this.vertexList = new LinkedList();
        this.edgeList = new LinkedList();
    }

    /**
     * Return the vertex list of this graph.
     */
    public Iterator vertices() {
        //System.out.println("Vertex list is" + vertexList);
        return vertexList.iterator();
    }

    /**
     * Return the edge list of this graph.
     */
    public Iterator edges() {
        return edgeList.iterator();
    }

    /**
     * Given a vertex, return an iterator to the edge list of that vertex
     *
     * @param v a vertex
     * @returns an iterator to the edge list of that vertex
     */
    public Iterator incidentEdges(Vertex v) {
        return v.incidentEdgeList.iterator();
    }

    /**
     * Return an arbitrary vertex of this graph
     *
     * @returns some vertex of this graph
     */
    public Vertex aVertex() {
        if (vertexList.size() > 0)
            return (Vertex) vertexList.getFirst();
        else
            return null;
    }

    /**
     * Add a vertex to this graph.
     *
     * @param data an object to be associated with the new vertex
     * @param name a name to be associated with the new vertex
     * @returns the new vertex
     */
    public Vertex insertVertex(int data, String name) {
        Vertex v;
        v = new Vertex(data, name);
        vertexList.addLast(v);
        return v;
    }

    /**
     * Add an edge to this graph.
     *
     * @param v    the first endpoint of the edge
     * @param w    the second endpoint of the edge
     * @param data data to be associated with the new edge
     * @param name name to be associated with the new edge
     * @returns the new edge
     */
    public Edge insertEdge(Vertex v, Vertex w, Double data, int name) {
        Edge e;
        e = new Edge(v, w, data, name);
        edgeList.addLast(e);
        v.incidentEdgeList.addLast(e);
        w.incidentEdgeList.addLast(e);
        return e;
    }

    /**
     * Given a vertex and an edge, if the vertex is one of the endpoints
     * of the edge, return the other endpoint of the edge.  Otherwise,
     * return null.
     *
     * @param v a vertex
     * @param e an edge
     * @returns the other endpoint of the edge (or null, if v is not an endpoint of e)
     */
    public Vertex opposite(Vertex v, Edge e) {
        Vertex w;

        if (e.getFirstEndpoint() == v) {
            w = e.getSecondEndpoint();
        } else if (e.getSecondEndpoint() == v) {
            w = e.getFirstEndpoint();
        } else
            w = null;

        return w;
    }

    /**
     * Return the number of vertices in this graph.
     *
     * @returns the number of vertices
     */
    public int numVertices() {
        return vertexList.size();
    }

    /**
     * Return the number of edges in this graph.
     *
     * @returns the number of edges
     */
    public int numEdges() {
        return edgeList.size();
    }

    public Edge getSpecificEdge(int index) {
        for (Iterator i = this.edges(); i.hasNext(); ) {
            Edge e = (Edge) i.next();
            if (e.getName() == index) return e;
        }
        return null;
    }

    /**
     * TO get the vertex at a given index
     */
    public Vertex getSpecificVertex(int index) {
        for (Iterator<Vertex> i = this.vertices(); i.hasNext(); ) {
            Vertex v = i.next();
            if (v.getData() == index) return v;
        }
        return null;
    }

    /**
     * To check if the set of vertex forms a forward edge
     */
    public boolean isForward(Vertex v, Vertex w) {
        boolean result = false;
        for (Iterator<Edge> e = this.incidentEdges(v); e.hasNext(); ) {
            Edge E = e.next();
            if (E.getSecondEndpoint() == w) {
                result = true;
            }
        }
        return result;
    }

    /**
     * To get the edge between two vertices*
     */
    public Edge getEdge(Vertex v, Vertex w) {
        Edge E = null;
        for (Iterator<Edge> i = this.incidentEdges(v); i.hasNext(); ) {
            Edge e = i.next();
            if (this.opposite(v, e) == w)
                E = e;
        }
        return E;
    }

    /**
     * Reversing an Edge
     */
    public Edge reverse(int edgeindex) {
        Edge e = this.getSpecificEdge(edgeindex);
        Vertex temp1 = e.getFirstEndpoint();
        Vertex temp2 = e.getSecondEndpoint();
        e.setV1(temp2);
        e.setV2(temp1);
        e.setFlow(0.0);
        return e;
    }
}


