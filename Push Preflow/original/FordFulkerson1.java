package original;

import graphCode.Edge;
import graphCode.SimpleGraph;
import graphCode.Vertex;

import java.util.*;


public class FordFulkerson1 {
	// Object for SimpleGraph
	private static SimpleGraph myGraph;
	private static int numberOfVertices;
	private static List<Vertex> minCutVertexList = new ArrayList<>();

	public static Double findMaxFlow(SimpleGraph theG, int theNumV) {
		myGraph = theG;
		numberOfVertices = theNumV;

		Double maxFlow = 0.0;
		Vertex source = myGraph.aVertex();
		Vertex sink = null;
		
		// get sink could put into SimpleGraph
		for (Iterator<Vertex> i = myGraph.vertices(); i.hasNext();) {
			Vertex v = (Vertex) i.next();
			if (v.getName().equals("t")) {
				sink = v;
			}
		}


		List<String> resultList = new ArrayList<>();
		List<Edge> augmentPath;
		
		while ((augmentPath = bfs(source, sink)) != null) {
			double maxCapacity = Double.MAX_VALUE;
			
			for (Edge e : augmentPath) maxCapacity = Math.min(e.getRemainedFlow(), maxCapacity);
				
			for (Edge e : augmentPath) e.addFlow(maxCapacity);

			maxFlow += maxCapacity;
		}
		return maxFlow;
	}

	private static List<Edge> bfs(Vertex src, Vertex sink) {
		boolean hasPathTo = false;
		Queue<Vertex> queue = new LinkedList<>();
		queue.add(src);
		minCutVertexList.clear();
		minCutVertexList.add(src);

		while (queue.size() > 0) {
			Vertex currVertex = queue.remove();
			List<Edge> listE = new ArrayList<>();
			/** Iterating through the incident edges*/
			for (Iterator<Edge> i = myGraph.incidentEdges(currVertex); i.hasNext();) {
				listE.add(i.next());
			}
			for (Edge edge : listE) {
				Vertex dstVertex = edge.getSecondEndpoint();
				if (minCutVertexList.contains(dstVertex) || edge.getRemainedFlow() == 0)
					continue;
				dstVertex.setEdgeTo(edge);
				minCutVertexList.add(dstVertex);
				if (dstVertex.equals(sink)) {
					hasPathTo = true;
					break;
				}
				queue.add(dstVertex);
			}
		}

		List<Edge> edgePathList = new ArrayList<>();
		if (hasPathTo) {
			Vertex vertex = sink;
			while (!(vertex.getData() == src.getData())) {
				edgePathList.add(vertex.getEdgeTo());
				vertex = vertex.getEdgeTo().getFirstEndpoint();
			}
		}
		return edgePathList.size() > 0 ? edgePathList : null;
	}
}
