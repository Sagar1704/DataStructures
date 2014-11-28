package sagar.datastructure.binaryTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import sagar.datastructure.queue.Queue;

/**
 * Perform Binary tree operations
 * 
 * @author Sagar
 * 
 */
public class BinaryTree<T> {
	protected BinaryTreeElement<T> root;
	protected Queue<BinaryTreeElement<T>> queue;
	protected int height;

	public int getHeight() {
		return height;
	}

	public void setHeight() {
		BinaryTreeElement<T> temp = getRoot();
		this.height = calculateHeight(temp);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();
		String choice = null;
		BinaryTreeElement<Integer> temp = null;

		do {
			binaryTree.displayMenu();
			try {
				choice = new BufferedReader(new InputStreamReader(System.in))
				.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (choice != null) {

				int ch = -1;
				try {
					ch = Integer.parseInt(choice);
				} catch (NumberFormatException e) {
					// Wrong choice
				}

				switch (ch) {
				case 1:
					binaryTree.initializeQueue();
					binaryTree.queue = binaryTree
							.traverseBreadthFirst(binaryTree.queue);
					binaryTree.insert();
					binaryTree.setHeight();
					break;
				case 2:
					binaryTree.initializeQueue();
					binaryTree.queue = binaryTree
							.traverseBreadthFirst(binaryTree.queue);
					binaryTree.displayBreadth();
					break;
				case 3:
					temp = binaryTree.getRoot();
					binaryTree.initializeQueue();
					binaryTree.queue = binaryTree
							.traverseBreadthFirst(binaryTree.queue);
					if (binaryTree.isEmpty())
						System.out.println("Binary Tree is Empty");
					else {
						binaryTree.display(temp, binaryTree.queue);
					}
					break;
				case 4:
					temp = binaryTree.getRoot();
					System.out.println("Number of Leaves:: " + binaryTree.countLeaves(temp));
					break;
				case 5:
					System.out.println("****GoodBye****");
					System.exit(0);
					break;
				default:
					System.out.println("\nWrong choice. Enter Again.");
					break;
				}
			} else
				System.out.println("\nWrong choice. Enter Again.");

			try {
				System.out.println("Press any key to continue.");
				new BufferedReader(new InputStreamReader(System.in)).readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} while (choice == null || !choice.equals("5"));
	}

	/**
	 * Display Menu driven screen
	 */
	private void displayMenu() {
		System.out.println("\nBinary Tree Operations::");
		System.out.println("\n(1)Insert");
		System.out.println("\n(2)Breadth First Traversal");
		System.out.println("\n(3)Display");
		System.out.println("\n(4)Number of Leaves");
		System.out.println("\n(5)Exit");
		System.out.print("\nEnter your choice::");
	}

	/**
	 * Insert a node in the binary tree
	 */
	public void insert() {
		System.out.println("**********Insert Node**********");
		System.out.print("Enter Node data :: ");
		try {
			@SuppressWarnings("unchecked")
			T data = (T) new BufferedReader(new InputStreamReader(System.in))
			.readLine();
			BinaryTreeElement<T> node = new BinaryTreeElement<T>(data, null,
					null);
			if (isEmpty())
				root = (BinaryTreeElement<T>) node;
			else {
				BinaryTreeElement<T> temp = (BinaryTreeElement<T>) root;
				queue.enQ(node);
				temp = getInsertPoint(temp, node);

			}

			System.out.println("***************************");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize Queue to empty list
	 */
	protected void initializeQueue() {
		queue = new Queue<BinaryTreeElement<T>>();
	}

	/**
	 * Find the correct insertion point for the node
	 * 
	 * @param temp
	 * @param node
	 * @return new node at its rightful place
	 */
	private BinaryTreeElement<T> getInsertPoint(BinaryTreeElement<T> temp,
			BinaryTreeElement<T> node) {
		int nodeIndex = queue.getSize() - 1;
		double parentIndex = 0;
		if (isInt((nodeIndex - 1.0) / 2)) {
			parentIndex = (int) (nodeIndex - 1) / 2;
			temp = queue.getQueue().get((int) parentIndex);
			temp.setLeft(node);
		} else if (isInt((nodeIndex - 2.0) / 2)) {
			parentIndex = (int) (nodeIndex - 2) / 2;
			temp = queue.getQueue().get((int) parentIndex);
			temp.setRight(node);
		}
		return temp;
	}

	/**
	 * @param parent
	 * @return true if integer
	 */
	protected boolean isInt(double parent) {
		return parent == (int) parent;
	}

	/**
	 * Traverse the binary tree in a breadth first fashion, recursively
	 * 
	 * @param temp
	 */
	protected Queue<BinaryTreeElement<T>> traverseBreadthFirst(
			Queue<BinaryTreeElement<T>> queue) {
		BinaryTreeElement<T> temp = root;
		Queue<BinaryTreeElement<T>> tempQueue = new Queue<>();
		tempQueue.enQ(temp);

		while (!isQReallyEmpty(tempQueue)) {
			if (!tempQueue.isEmpty())
				temp = tempQueue.dQ();
			else
				break;

			queue.enQ(temp);

			if (temp != null) {
				tempQueue.enQ(temp.getLeft());
				tempQueue.enQ(temp.getRight());
			} else if (!isQReallyEmpty(tempQueue)) {
				tempQueue.enQ(null);
				tempQueue.enQ(null);
			}
		}
		return queue;
	}

	private boolean isQReallyEmpty(Queue<BinaryTreeElement<T>> queue) {
		for (BinaryTreeElement<T> element : queue.getQueue()) {
			if (element != null)
				return false;
		}
		return true;
	}

	/**
	 * Display the breadth first traversal
	 */
	protected void displayBreadth() {
		System.out.println("Breadth First Traversal ::\n");
		System.out.print("START");
		for (BinaryTreeElement<T> element : queue.getQueue()) {
			if (element != null)
				System.out.print(" --> " + element.getData());
		}
		System.out.println(" --> END");
	}

	/**
	 * @return true if binary tree is empty
	 */
	protected boolean isEmpty() {
		if (root == null)
			return true;
		return false;
	}

	/**
	 * @return root node of the binary tree
	 */
	protected BinaryTreeElement<T> getRoot() {
		return root;
	}

	protected void setRoot(BinaryTreeElement<T> root) {
		this.root = root;
	}

	/**
	 * Display binary tree in a tree fashion
	 * 
	 * @param temp
	 * @param queue
	 */
	protected void display(BinaryTreeElement<T> temp,
			Queue<BinaryTreeElement<T>> queue) {
		int size = queue.getSize();
		int height = calculateHeight(temp);
		int h = height;
		int index = 0;
		int tempIndex = 1;
		Queue<BinaryTreeElement<T>> tempQ = new Queue<BinaryTreeElement<T>>(
				queue.getQueue());
		int leftAndRightSpace = 0;
		int rightAndLeftSpace = 0;
		for (int level = 0; level < h; level++) {
			System.out.print("\n");
			if (level != 0 && index < size) {
				rightAndLeftSpace = leftAndRightSpace - 3;
				leftAndRightSpace = 1;
				int maxStartSpace = (int) Math.pow(2, height + 1);
				for (int arrowLevel = 0; arrowLevel < Math.pow(2, height); arrowLevel++) {
					tempIndex = index;
					for (int arrowPosition = 0; arrowPosition < Math.pow(2,
							level); arrowPosition++) {
						if (arrowPosition == 0) {
							for (int startSpace = 0; startSpace < maxStartSpace - 1; startSpace++)
								System.out.print(" ");
							if (tempIndex < size
									&& tempQ.getQueue().get(tempIndex) != null)
								System.out.print("/");
							else
								System.out.print(" ");
							for (int afterLeftSpace = 0; afterLeftSpace < leftAndRightSpace; afterLeftSpace++)
								System.out.print(" ");
							maxStartSpace--;
						} else {
							if (!isEven(tempIndex)) {
								if (tempIndex < size
										&& tempQ.getQueue().get(tempIndex) != null)
									System.out.print("/");
								else
									System.out.print(" ");
								for (int afterLeftSpace = 0; afterLeftSpace < leftAndRightSpace; afterLeftSpace++)
									System.out.print(" ");
							} else if (isEven(tempIndex)) {
								if (tempIndex < size
										&& tempQ.getQueue().get(tempIndex) != null)
									System.out.print("\\");
								else
									System.out.print(" ");
								if (arrowPosition + 1 < Math.pow(2, level)) {
									for (int afterRightSpace = 0; afterRightSpace < rightAndLeftSpace; afterRightSpace++)
										System.out.print(" ");
								}

							}
						}
						tempIndex++;
					}
					leftAndRightSpace += 2;
					rightAndLeftSpace -= 2;
					System.out.print("\n");
				}
			}

			for (int nodePosition = 0; nodePosition < Math.pow(2, level); nodePosition++) {
				if (nodePosition == 0) {
					for (int startSpace = 0; startSpace < Math.pow(2, height) - 1; startSpace++) {
						System.out.print(" ");
					}
				} else {
					if (isEven(index))
						for (int betweenSpace = 0; betweenSpace < leftAndRightSpace - 2; betweenSpace++)
							System.out.print(" ");
					else
						for (int betweenSpace = 0; betweenSpace < leftAndRightSpace - 3; betweenSpace++)
							System.out.print(" ");
				}
				if (!queue.isEmpty() && queue.getQueue().get(0) != null)
					System.out.print(((BinaryTreeElement<T>) queue.dQ())
							.getData());
				else {
					System.out.print("  ");
					if (!queue.isEmpty())
						queue.dQ();
				}
				index++;
			}
			height--;
		}
		System.out.println("\n");
	}

	/**
	 * @param number
	 * @return true if even number
	 */
	private boolean isEven(int number) {
		if ((number % 2) == 0)
			return true;
		return false;
	}

	/**
	 * @param temp
	 * @return depth of the tree
	 */
	private int calculateHeight(BinaryTreeElement<T> temp) {
		if (temp == null)
			return 0;
		if (!temp.hasLeft() && !temp.hasRight())
			return 1;

		int leftHeight = calculateHeight(temp.getLeft());
		int rightHeight = calculateHeight(temp.getRight());

		if (leftHeight > rightHeight)
			return leftHeight + 1;
		else
			return rightHeight + 1;
	}

	public int countLeaves(BinaryTreeElement<T> temp) {
		if(temp == null)
			return 0;
		else if(!temp.hasLeft() && !temp.hasRight())
			return 1;
		else {
			int left = countLeaves(temp.getLeft());
			int right = countLeaves(temp.getRight());
			return left + right;
		}
	}
}
