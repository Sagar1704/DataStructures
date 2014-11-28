package sagar.datastructure.mst;

public class WeightVertex {
	private int weight;
	private int vertexIndex;

	public WeightVertex() {
		weight = Integer.MAX_VALUE;
	}

	public WeightVertex(int weight, int vertexIndex) {
		super();
		this.weight = weight;
		this.vertexIndex = vertexIndex;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getVertexIndex() {
		return vertexIndex;
	}

	public void setVertexIndex(int vertexIndex) {
		this.vertexIndex = vertexIndex;
	}

}
