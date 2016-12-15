package graphCode;

import java.io.BufferedReader;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class GraphInput {

	/**
	 * Load graph data from a text file. This method asks
	 * the user for a directory and path name. It returns a hashtable of
	 * (String, Vertex) pairs. newgraph needs to already be initialized.
	 */
	public static Hashtable LoadSimpleGraph(SimpleGraph newgraph) {
		System.out.print("Please enter the full path and file name for the input data: ");
		String userinput;
		userinput = KeyboardReader.readString();
		return LoadSimpleGraph(newgraph, userinput);
	}

	/**
	 * Load graph data from a text file. The format of the file is: Each line of
	 * the file contains 3 tokens, where the first two are strings representing
	 * vertex labels and the third is an edge weight (a double). Each line
	 * represents one edge.
	 *
	 * Format: "s a 6" :: s->a has capacity of 6
	 *
	 * This method returns a hashtable of (String, Vertex) pairs.
	 */

	public static Hashtable LoadSimpleGraph(SimpleGraph newgraph, String pathandfilename) {
		BufferedReader inbuf = InputLib.fopen(pathandfilename);
		System.out.println("Opened " + pathandfilename + " for input.");
		String line = InputLib.getLine(inbuf); // get first line
		StringTokenizer sTok;
		int n, linenum = 0;
		Hashtable table = new Hashtable();
		SimpleGraph sg = newgraph;
		int indexV = 0;
		int indexE = 0;
		while (line != null) {
			linenum++;
			sTok = new StringTokenizer(line);
			n = sTok.countTokens();
			if (n == 3) {
				Double edgedata;
				Vertex v1, v2;
				String v1name, v2name;

				v1name = sTok.nextToken();
				v2name = sTok.nextToken();

				edgedata = new Double(Double.parseDouble(sTok.nextToken()));
				v1 = (Vertex) table.get(v1name);
				if (v1 == null) {
					v1 = sg.insertVertex(indexV, v1name);
					table.put(v1name, v1);
					indexV++;
				}
				v2 = (Vertex) table.get(v2name);
				if (v2 == null) {
					v2 = sg.insertVertex(indexV, v2name);
					table.put(v2name, v2);
					indexV++;
				}
				sg.insertEdge(v1, v2, edgedata, indexE);
				indexE++;
				
			} else {
				System.err.println("Error:invalid number of tokens found on line " + linenum + "!");
				return null;
			}
			line = InputLib.getLine(inbuf);
		}

		InputLib.fclose(inbuf);
		System.out.println("Successfully loaded " + linenum + " lines. ");
		return table;
	}


	public static SimpleGraph getMainGraph(){
	    SimpleGraph maingraph;
	    maingraph = new SimpleGraph();
	    LoadSimpleGraph(maingraph, "input.txt");
	    return maingraph;
    }

    /**
	 * Can be used to call the Network flow algorithms at once.*/
	public static void main(String args[]) {
		SimpleGraph G;
		G = new SimpleGraph();
		// For passing the input file in the command line.
		// LoadSimpleGraph(G, args[0]); // read input file and path
		LoadSimpleGraph(G, "random2.txt");
	}
}