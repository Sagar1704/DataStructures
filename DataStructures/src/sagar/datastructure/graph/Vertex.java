package sagar.datastructure.graph;

/**
 * Graph's Vertex
 * 
 * @author Sagar
 * 
 */
public class Vertex {
	private String value;
	private Visit flag;

	/**
	 * Default Constructor
	 */
	public Vertex() {
	}

	public Vertex(String value) {
		super();
		this.value = value;
		this.flag = Visit.NOT_VISITED;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Visit getFlag() {
		return flag;
	}

	public void setFlag(Visit flag) {
		this.flag = flag;
	}
}
