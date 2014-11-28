package sagar.datastructure.graph;

public enum Visit {
	NOT_VISITED(0), VISITED(1), TRAVERESED(2);

	int value;

	private Visit(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
