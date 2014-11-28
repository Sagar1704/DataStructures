package sagar.datastructure.graph;

/**
 * Graph's Edge with start and end vertices, and weight
 * 
 * @author Sagar
 * 
 */
public class Edge {
	private Vertex fromVertex;
	private Vertex toVertex;
	private double weight;

	/**
	 * Default Constructor
	 */
	public Edge() {
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Vertex getFromVertex() {
		return fromVertex;
	}

	public void setFromVertex(Vertex fromVertex) {
		this.fromVertex = fromVertex;
	}

	public Vertex getToVertex() {
		return toVertex;
	}

	public void setToVertex(Vertex toVertex) {
		this.toVertex = toVertex;
	}

}
