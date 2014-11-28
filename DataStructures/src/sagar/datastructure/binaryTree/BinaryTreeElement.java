package sagar.datastructure.binaryTree;

/**
 * Binary tree node contains:- 1. Data, 2. Pointer to left child, 3. Pointer to
 * right child
 * 
 * @author Sagar
 */
public class BinaryTreeElement<T> implements Comparable<BinaryTreeElement<T>> {
	private T data;
	private BinaryTreeElement<T> left;
	private BinaryTreeElement<T> right;

	public BinaryTreeElement() {
		super();
	}

	public BinaryTreeElement(T data, BinaryTreeElement<T> left,
			BinaryTreeElement<T> right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public BinaryTreeElement<T> getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeElement<T> left) {
		this.left = left;
	}

	public BinaryTreeElement<T> getRight() {
		return right;
	}

	public void setRight(BinaryTreeElement<T> right) {
		this.right = right;
	}

	/**
	 * @return true if node has a left child
	 */
	public boolean hasLeft() {
		if (left != null)
			return true;
		return false;
	}

	/**
	 * @return true if node has a right child
	 */
	public boolean hasRight() {
		if (right != null)
			return true;
		return false;
	}

	@Override
	public int compareTo(BinaryTreeElement<T> object) {
		try {
			if (Integer.parseInt("" + this.getData()) > Integer.parseInt(""
					+ object.getData()))
				return 1;
			else if (Integer.parseInt("" + this.getData()) < Integer
					.parseInt("" + object.getData()))
				return -1;
		} catch (NumberFormatException e) {
			int comparator = this.getData().toString()
					.compareTo(object.getData().toString());
			if (comparator == 1)
				return 1;
			else if (comparator == -1)
				return -1;
		}
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BinaryTreeElement<?> other = (BinaryTreeElement<?>) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}

}
