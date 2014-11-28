package sagar.datastructure.mst;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import sagar.datastructure.exceptions.OutOfRangeException;
import sagar.datastructure.graph.Graph;
import sagar.datastructure.graph.Visit;

public class Prims {
	private Graph graph;
	private Integer[][] weights;
	private int startingIndex;

	private static Integer MAX = Integer.MAX_VALUE;

	private Scanner scanner = new Scanner(System.in);

	public Prims() {
		weights = new Integer[100][100];
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int vertex;

		String ch = "";
		String choice = "";

		Prims prims = new Prims();
		prims.graph = new Graph();

		System.out.println("*****************************");
		System.out.println("\tPRIMS");
		System.out.println("*****************************");

		do {
			System.out.println("Enter file containing graph information:");
			String file = prims.scanner.nextLine();
			int lineCounter = 1;
			int startVertex = 0;
			try {
				prims.scanner = new Scanner(new File(file));
			} catch (FileNotFoundException e) {
				System.out.println("Enter correct file name");
				continue;
			}
			try {
				while (prims.scanner.hasNextLine()) {
					String line = prims.scanner.nextLine();
					switch (lineCounter) {
					case 1:

						prims.graph.setNumOfVertices(Integer.parseInt(line));

						prims.initializeWeight();
						break;
					case 2:
						prims.graph.intializeGraph();
						prims.graph.setVertices(line);
						break;
					case 3:
						prims.graph.setAcyclicEdges(line, startVertex);
						prims.setWeights(line, startVertex);
						startVertex++;
						break;
					default:
						break;
					}

					if (lineCounter != 3)
						lineCounter++;
				}

				System.out.println("\n*****************************");
				prims.scanner = new Scanner(System.in);
				do {
					int vertexCounter = 0;
					for (String v : prims.graph.getVertices()) {
						System.out.println(++vertexCounter + ". " + v);
					}
					System.out.print("\n\tEnter Starting Node::");
					try {
						vertex = Integer.parseInt(prims.scanner.next());

						if (vertex < 1
								|| vertex > prims.graph.getNumOfVertices()) {
							throw new OutOfRangeException(
									"\tPlease enter the vertex from given range");
						}

						prims.startingIndex = prims.graph.search(prims.graph
								.getVertices().get(vertex - 1), prims.graph
								.getVertices(), prims.graph.getNumOfVertices());

						System.out.println("\n*****************************");

						System.out.println("The edges of the graph");
						System.out.println("*****************************");
						prims.printWeightedEdges();
						System.out.println("\n*****************************");
						prims.prim();
					} catch (NumberFormatException e) {
						System.out.println("\tPlease enter the vertex number");
					} catch (OutOfRangeException e) {
						System.out.println(e.getMessage());
					}

					System.out
							.print("\n\tEnter Different Starting Node(Press y)::");
					prims.scanner = new Scanner(System.in);
					ch = prims.scanner.next();

				} while (ch.equalsIgnoreCase("y"));
			} catch (NumberFormatException e) {
				System.out
						.println("\tPlease check the input file and try again");
			}
			System.out.print("\n\tWan't to Enter Another Graph(Press c)::");
			prims.scanner = new Scanner(System.in);
			choice = prims.scanner.next();

		} while (choice.equalsIgnoreCase("c"));

	}

	private void printWeightedEdges() {
		System.out.println("\n\tSTART\t--\tWEIGHT\t--\tEND");

		for (int i = 0; i < graph.getNumOfVertices(); i++) {
			for (int j = i; j < graph.getNumOfVertices(); j++) {
				if (weights[i][j] != MAX) {
					System.out.println("\n\t" + graph.getVertices().get(i)
							+ "\t--\t" + weights[i][j] + "\t--\t"
							+ graph.getVertices().get(j));
				}
			}
		}
	}

	private void initializeWeight() {
		for (int i = 0; i < graph.getNumOfVertices(); i++) {
			for (int j = 0; j < graph.getNumOfVertices(); j++) {
				weights[i][j] = MAX;
			}
		}
	}

	public void setWeights(String line, int startVertex) {
		int endVertex = startVertex + 1;
		for (String edgeWeight : line.split(" ")) {
			if (Integer.parseInt(edgeWeight) != 0) {
				weights[startVertex][endVertex] = weights[endVertex][startVertex] = Integer
						.parseInt(edgeWeight);
			}
			endVertex++;
		}
	}

	public void prim() {
		int minimumIndex;
		int cost = 0;

		ArrayList<Visit> visited = new ArrayList<Visit>();
		ArrayList<WeightVertex> weightVertexs = new ArrayList<WeightVertex>();
		for (int vertexCounter = 0; vertexCounter < graph.getNumOfVertices(); vertexCounter++) {
			visited.add(Visit.NOT_VISITED);
			weightVertexs.add(new WeightVertex());
		}
		minimumIndex = startingIndex;

		visited.set(minimumIndex, Visit.TRAVERESED);

		for (int vertexCounter = 0; vertexCounter < graph.getNumOfVertices(); vertexCounter++) {
			int minIndex = updateWeightAndReturnMinimum(minimumIndex, visited,
					weightVertexs);
			if (minIndex != minimumIndex) {
				minimumIndex = minIndex;
			} else {
				minimumIndex = 0;
				for (int i = 0; i < visited.size(); i++) {
					if (visited.get(i) == Visit.NOT_VISITED) {
						minimumIndex = i;
						break;
					}
				}
			}
			visited.set(minimumIndex, Visit.TRAVERESED);
		}

		System.out.println("Edges of the MST(START-"
				+ graph.getVertices().get(startingIndex) + ")");
		System.out.println("*****************************");
		System.out.println("\n\tSTART\t--\tWEIGHT\t--\tEND");
		for (int vertexCounter = 0; vertexCounter < graph.getNumOfVertices(); vertexCounter++) {
			if (vertexCounter != startingIndex && weightVertexs.get(vertexCounter).getWeight() != MAX) {
				System.out.println("\n\t"
						+ graph.getVertices().get(vertexCounter)
						+ "\t--\t"
						+ weightVertexs.get(vertexCounter).getWeight()
						+ "\t--\t"
						+ graph.getVertices().get(
								weightVertexs.get(vertexCounter)
										.getVertexIndex()));
				cost = cost + weightVertexs.get(vertexCounter).getWeight();
			}
		}
		System.out.println("\n*****************************");

		// System.out.println("\n\n\tCost::" + cost);
	}

	/**
	 * UPDATE WEIGHT ARRAY TO GET MINIMUM WEIGHT OF EVERY EDGE
	 * 
	 * @param minimumIndex
	 * @param graph
	 * @param weights
	 * @param vertex
	 * @param visited
	 * @param weightVertexs
	 * @return
	 */
	private int updateWeightAndReturnMinimum(int minimumIndex,
			ArrayList<Visit> visited, ArrayList<WeightVertex> weightVertexs) {

		for (int vertexCounter = 0; vertexCounter < graph.getNumOfVertices(); vertexCounter++) {
			// FIND ADJACENT NON-VISITED NODES
			// UPDATE THE WEIGHT OF EACH EDGE IF MIN
			if (weights[minimumIndex][vertexCounter] < MAX
					&& weightVertexs.get(vertexCounter).getWeight() > weights[minimumIndex][vertexCounter]
					&& visited.get(vertexCounter) != Visit.TRAVERESED) {

				weightVertexs.set(vertexCounter, new WeightVertex(
						weights[minimumIndex][vertexCounter], minimumIndex));

				// MARK THAT NODE TEMPORARY VISITED
				visited.set(vertexCounter, Visit.VISITED);

			}
		}

		// FIND MINIMUM OF THE ADJACENT NODES
		int vertexCounter = 0;
		int minimumWeight = MAX;
		while (vertexCounter < graph.getNumOfVertices()) {
			if (weightVertexs.get(vertexCounter).getWeight() < minimumWeight
					&& visited.get(vertexCounter) != Visit.TRAVERESED) {
				minimumWeight = weightVertexs.get(vertexCounter).getWeight();

				minimumIndex = vertexCounter;
			}
			vertexCounter++;
		}

		// RETURN THE MIN NODE(INDEX)
		return minimumIndex;
	}

}
