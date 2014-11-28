package sagar.datastructure.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import sagar.datastructure.exceptions.BlankException;
import sagar.datastructure.queue.Queue;
import sagar.datastructure.stack.Stack;

/**
 * Graph traversals using Adjacency Matrix
 * 
 * @author Sagar
 * 
 */
public class Graph {
	private boolean m[][];
	private int numOfVertices;
	private ArrayList<String> vertices;

	public Graph() {
		vertices = new ArrayList<String>();
		this.m = new boolean[100][100];
	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) {
		Graph g = new Graph();
		int choice = -1;

		do {
			
			System.out.println("\n********MENU*********");
			System.out.println("(1)Enter Graph");
			System.out.println("(2)BFS");
			System.out.println("(3)DFS");
			System.out.println("(4)Exit");
			System.out.print("Enter Choice::");
			try {
				choice = Integer.parseInt(new BufferedReader(
						new InputStreamReader(System.in)).readLine());
			} catch (NumberFormatException | IOException e) {
				// Wrong choice
				choice = -1;
			}

			switch (choice) {
			case 1:
				g = new Graph();
				g.setVertices();

				System.out.println("Enter Edges::");

				g.setEdges();
				System.out.println("\n*******************");
				break;
			case 2:
				System.out.println("*******************");
				System.out.println("*******BFS*********");
				System.out.println("*******************");
				g.bfs();
				System.out.println("\n*******************");
				break;
			case 3:
				System.out.println("*******************");
				System.out.println("*******DFS*********");
				System.out.println("*******************");
				g.dfs();
				System.out.println("\n*******************");
				break;
			case 4:
				System.out.println("*******************");
				return;
			default:
				System.out.println("\nWrong Choice");
				break;
			}
			try {
				new BufferedReader(new InputStreamReader(System.in)).readLine();
			} catch (IOException e) {
				System.out.println("Some problem with Input");
			}
		} while (choice != 4);
	}

	public ArrayList<String> getVertices() {
		return vertices;
	}

	/**
	 * Get graph vertices from the User
	 */
	public void setVertices() {
		System.out.println("*****************************");
		System.out.print("Enter No. of Vertices::");
		try {
			numOfVertices = Integer.parseInt(new BufferedReader(
					new InputStreamReader(System.in)).readLine());
		} catch (NumberFormatException | IOException e) {
			System.out.println("Please enter a number");
			setVertices();
			return;
		}
		intializeGraph();
		for (int vertexCounter = 0; vertexCounter < numOfVertices; vertexCounter++) {
			System.out.print("Enter Vertex(" + (vertexCounter + 1) + ")::");
			try {
				String vertex = new BufferedReader(new InputStreamReader(
						System.in)).readLine();
				if (vertex.equals(""))
					throw new BlankException("Please enter a vertex");
				vertices.add(vertex);
			} catch (BlankException e) {
				System.out.println(e.getMessage());
				vertexCounter--;
				continue;
			} catch (IOException e) {
				System.out.println("Some problem with the input");
				System.out.println("Restarting");
				setVertices();
				return;
			}
		}
	}
	
	public void setVertices(String vertices) {
		for (String vertex : vertices.split(" ")) {
			this.vertices.add(vertex);
		}
	}

	/**
	 * Initialize the graph's adjacency matrix
	 */
	public void intializeGraph() {
		for (int i = 0; i < numOfVertices; i++) {
			for (int j = 0; j < numOfVertices; j++) {
				m[i][j] = false;
			}
		}
	}
	
	public boolean[][] getEdges() {
		return m;
	}

	/**
	 * Get the edges of the graph from the user
	 */
	public void setEdges() {
		System.out
				.println("Press \"Enter\" if edge is not present or any other key if present");
		System.out.println("*****************************");
		for (int i = 0; i < numOfVertices; i++) {
			for (int j = 0; j < numOfVertices; j++) {
				System.out.print("From " + vertices.get(i) + " --> "
						+ vertices.get(j) + "::");
				if (!new Scanner(System.in).nextLine().equalsIgnoreCase(""))
					m[i][j] = true;
			}
		}
		
		System.out.println("*****************************");
		System.out.println("Entered Graph::");
		for (int i = 0; i < numOfVertices; i++) {
			System.out.print("\n");
			for (int j = 0; j < numOfVertices; j++) {
				System.out.print("\t" + (m[i][j] ? 1 : 0));
			}
		}
		System.out.println("*****************************");
	}

	/**
	 * Get the edges of the graph from the user
	 * @param startVertex 
	 */
	public void setAcyclicEdges(String line, int startVertex) {
		int endVertex = startVertex + 1;
		for (String edgeWeight : line.split(" ")) {
			if(Integer.parseInt(edgeWeight) != 0) {
				m[startVertex][endVertex] = m[endVertex][startVertex] = true;
			}
			endVertex++;
		}
	}
	
	public int getNumOfVertices() {
		return numOfVertices;
	}

	public void setNumOfVertices(int numOfVertices) {
		this.numOfVertices = numOfVertices;
	}

	/**
	 * Breadth first traversal
	 */
	public void bfs() {
		ArrayList<Visit> visited = new ArrayList<Visit>();
		for (int vertexCounter = 0; vertexCounter < numOfVertices; vertexCounter++)
			visited.add(Visit.NOT_VISITED);

		Queue<String> q = new Queue<String>();
		int i = 0;
		int j = 0;
		int k = 0;
		while (k < numOfVertices && visited.get(k) == Visit.NOT_VISITED) {
			q.enQ(vertices.get(k));
			visited.add(k, Visit.VISITED);
			visited.remove(k + 1);

			for (i = 0; i < numOfVertices; i++) {
				if (!q.isEmpty()) {
					System.out.print("\t" + q.dQ());
				} else
					break;

				j = i;
				while (j < numOfVertices) {
					if (m[i][j] && visited.get(j) == Visit.NOT_VISITED) {
						q.enQ(vertices.get(j));
						visited.add(j, Visit.VISITED);
						visited.remove(j + 1);
					}
					j++;
				}
			}
			k++;
		}
	}

	/**
	 * Depth first traversal
	 */
	public void dfs() {
		ArrayList<Visit> visited = new ArrayList<Visit>();
		for (int vertexCounter = 0; vertexCounter < numOfVertices; vertexCounter++)
			visited.add(Visit.NOT_VISITED);

		Stack<String> s = new Stack<String>();
		int i = 0;
		int j = 0;
		int k = 0;
		while (k < numOfVertices && visited.get(k) == Visit.NOT_VISITED) {
			visited.add(k, Visit.VISITED);
			visited.remove(k + 1);
			s.push(vertices.get(k));
			while (i < numOfVertices) {
				if (j < numOfVertices) {
					if (m[i][j] && visited.get(j) == Visit.NOT_VISITED) {
						visited.add(j, Visit.VISITED);
						visited.remove(j + 1);
						s.push(vertices.get(j));
						i = j;
						j = 0;
					} else
						j++;
				} else {
					if (!s.isEmpty()) {
						j = search(s.getStack().get(s.getTop()), vertices,
								numOfVertices);
						visited.add(j, Visit.TRAVERESED);
						visited.remove(j + 1);

						System.out.print("\t" + s.pop());
						j++;

						if (!s.isEmpty())
							i = search(s.getStack().get(s.getTop()), vertices,
									numOfVertices);
					} else
						break;
				}
			}
			k++;

		}
	}

	/**
	 * Search for the index of the node in the vertices array
	 * 
	 * @param node
	 * @param vertices
	 * @param numOfVertices
	 * @return
	 */
	public int search(String node, ArrayList<String> vertices,
			int numOfVertices) {
		int i = 0;

		while (i < numOfVertices && !(vertices.get(i).equalsIgnoreCase(node)))
			i++;

		return i;
	}
}
