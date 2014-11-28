package sagar.datastructure.binaryTree;

public class Expression<T> extends BinaryTree<T> {
	
	public static void main(String[] args) {
		Expression<String> exp = new Expression<String>();
		System.out.println("Enter a expression in tree format");
		exp.root = exp.getExpression();
	}
	
	private BinaryTreeElement<T> getExpression() {
		System.out.println();
		return null;
	}
}
