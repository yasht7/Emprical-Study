package original;


import graphCode.Edge;
import graphCode.GraphInput;
import graphCode.SimpleGraph;
import graphCode.Vertex;

import java.util.Iterator;

/**
 * Created by Yash on 12/5/2016.
 */

public class PushRelabel {

    private static double[] hlabel;
    private static double[] excess;
    static Double flow = 0.0;

    private static SimpleGraph myResGraph;
    private static SimpleGraph myGraph;

    /**Like an init function
     * Takes in a graph
     * Takes in the source*/

    public static Vertex getMinhlabel(Vertex v){
        Vertex minhlabelVertex = null;
        double minhlabel = 1000.0;
        for(Iterator<Edge> i = myResGraph.incidentEdges(v); i.hasNext();){
            Vertex u = myResGraph.opposite(v, i.next());
            if(myResGraph.isForward(v,u)){
                if(hlabel[u.getData()] <= minhlabel){
                    minhlabel = hlabel[u.getData()];
                    minhlabelVertex = u;
                }
            }
        }
        return minhlabelVertex;
    }

    public static Vertex getExcessVertex(){
        Vertex v = null;
        for(int i=1; i< excess.length-2; i++){
            if(excess[i] > 0){
                v = myResGraph.getSpecificVertex(i);
                //break;
            }
        }
        return v;
    }

    public static void preflow(){
        for(Iterator<Vertex> i = myResGraph.vertices(); i.hasNext();){
            Vertex v = i.next();
            hlabel[v.getData()] = 0.0;
            excess[v.getData()] = 0.0;
        }

        for(Iterator<Edge> edge = myResGraph.edges(); edge.hasNext();)
            edge.next().setFlow(0.0);

        hlabel[myResGraph.aVertex().getData()] = myResGraph.numVertices();

        for(Iterator<Vertex> i = myResGraph.vertices(); i.hasNext();){
            Vertex v = i.next();
            if(v.getName().equals("s")){
                for(Iterator<Edge> e = myResGraph.incidentEdges(v); e.hasNext();){
                    Edge E = e.next();
                    E.setFlow(E.getData());
                    excess[myResGraph.opposite(v, E).getData()] = E.getData();
                    // Letting the edge excess at source becoming negative.!
                    excess[v.getData()] -= E.getData();
                    Edge resedge = myResGraph.getSpecificEdge(E.getName());
                    myResGraph.reverse(resedge.getName());
                }
            }
        }
    }

    public static void push(Vertex u, Vertex v){
        // u is overflowing here and hlabel[u] > hlabel[v] AND also, (u,v) has some capacity in the residual graph.

        // Forward Edges in the Residual Graph.
        Edge E = myGraph.getEdge(myGraph.getSpecificVertex(u.getData()), myGraph.getSpecificVertex(v.getData()));
        Edge resE = myResGraph.getEdge(myResGraph.getSpecificVertex(u.getData()), myResGraph.getSpecificVertex(v.getData()));
        Double delta = Math.min(excess[u.getData()], resE.getRemainedFlow());

        if(myGraph.isForward(myGraph.getSpecificVertex(u.getData()), myGraph.getSpecificVertex(v.getData()))){
            System.out.println("Doing Flow: " + resE.getFirstEndpoint().getName() + "->" + resE.getSecondEndpoint().getName() + ": " + delta);
            resE.addFlow(delta);
            if(resE.getRemainedFlow() == 0) {
                resE = myResGraph.reverse(resE.getName());
            }
        }
        else{
            System.out.println("UnDoing Flow: " + resE.getFirstEndpoint().getName() + "->" + resE.getSecondEndpoint().getName() + ": " + delta);
            resE.minusFlow(delta);

            if(delta.equals(resE.getData())) {
                resE = myResGraph.reverse(resE.getName());
            }
        }
        excess[u.getData()] -= delta;
        excess[v.getData()] += delta;
    }

    public static void relabel(Vertex vert){
        // Applies v is overflowing and for all forward paths are at a higher point.
        Vertex u = getMinhlabel(vert);
        if(u == null)
            hlabel[vert.getData()] += 1 ;
        else
            hlabel[vert.getData()] = 1 + hlabel[u.getData()];
    }

    public static void main(String[] args){
        myGraph = GraphInput.getMainGraph();
        myResGraph = GraphInput.getMainGraph();
        hlabel = new double[myResGraph.numVertices()];
        excess = new double[myResGraph.numVertices()];

        preflow();

        while(getExcessVertex() != null){
            Vertex u = getExcessVertex();
            if(getMinhlabel(u) !=null){
                Vertex v = getMinhlabel(u);
                // Testing for applicability of Push or Relabel
                if((hlabel[u.getData()] > hlabel[v.getData()]) && (myResGraph.getEdge(myResGraph.getSpecificVertex(u.getData()), myResGraph.getSpecificVertex(v.getData())).getRemainedFlow() != 0)){
                    push(u,v);
                }
                else{
                    relabel(u);
                }
            }
        }
        System.out.println("Flow is: " + excess[excess.length-1]);
    }
}
