package sagar.datastructure.binarysearchtree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import sagar.datastructure.binaryTree.BinaryTree;
import sagar.datastructure.binaryTree.BinaryTreeElement;

public class BinarySearchTree<T> extends BinaryTree<T> {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		String choice = null;
		BinaryTreeElement<Integer> temp = null;

		do {
			bst.displayMenu();
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
					bst.insert();
					break;
				case 2:
					bst.initializeQueue();
					bst.queue = bst.traverseBreadthFirst(bst.queue);
					System.out
					.println("Which of the following node do you want to delete?");
					bst.displayBreadth();
					bst.delete(temp);
					break;
				case 3:
					bst.initializeQueue();
					bst.queue = bst.traverseBreadthFirst(bst.queue);
					bst.displayBreadth();
					break;
				case 4:
					temp = bst.getRoot();
					bst.initializeQueue();
					bst.queue = bst.traverseBreadthFirst(bst.queue);
					if (bst.isEmpty())
						System.out.println("Binary Tree is Empty");
					else {
						bst.display(temp, bst.queue);
					}
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
				System.out.println("Press Enter to continue.");
				new BufferedReader(new InputStreamReader(System.in)).readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} while (choice == null || !choice.equals("5"));

	}

	/**
	 * Display Menu driven screen
	 */
	public void displayMenu() {
		System.out.println("\nBinary Search Tree Operations::");
		System.out.println("\n(1)Insert");
		System.out.println("\n(2)Delete");
		System.out.println("\n(3)Display Breadth");
		System.out.println("\n(4)Display Tree");
		System.out.println("\n(5)Exit");
		System.out.print("\nEnter your choice::");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sagar.datastructure.binaryTree.BinaryTree#insert()
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
				root = node;
			else {
				BinaryTreeElement<T> temp = root;
				temp = getInsertPoint(temp, node);
			}

			System.out.println("***************************");
		} catch (NumberFormatException e) {
			System.out.println("Please enter a number.");
			System.out.println("***************************");
			insert();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Find the correct insertion point for the node
	 * 
	 * @param temp
	 * @param node
	 * @return new node at its rightful place in the search tree
	 */
	private BinaryTreeElement<T> getInsertPoint(BinaryTreeElement<T> temp,
			BinaryTreeElement<T> node) {
		if (node.compareTo(temp) == -1) {
			if (!temp.hasLeft()) {
				temp.setLeft(node);
				return temp;
			}
			temp = temp.getLeft();
			return getInsertPoint(temp, node);
		} else {
			if (!temp.hasRight()) {
				temp.setRight(node);
				return temp;
			}
			temp = temp.getRight();
			return getInsertPoint(temp, node);
		}
	}

	private void delete(BinaryTreeElement<T> temp) {
		System.out.println("**********Delete Node**********");
		System.out.print("Enter Node data :: ");
		try {
			@SuppressWarnings("unchecked")
			T data = (T) new BufferedReader(new InputStreamReader(System.in))
			.readLine();
			BinaryTreeElement<T> node = new BinaryTreeElement<T>(data, null,
					null);
			node = search(temp, node);
			if (node != null) {
				temp = getParent(node);
				BinaryTreeElement<T> leftMostRightNode = getLeftmostRight(node
						.getRight());
				if(leftMostRightNode != null) {
					getParent(leftMostRightNode).setLeft(
							leftMostRightNode.getRight());
					leftMostRightNode.setLeft(node.getLeft());
					leftMostRightNode.setRight(node.getRight());

					temp.setRight(leftMostRightNode);
				}
				System.out.println("Deleted :: " + data);
			} else
				System.out.println(data + " Not found");

			System.out.println("***************************");
		} catch (NumberFormatException e) {
			System.out.println("Please enter a number.");
			System.out.println("***************************");
			insert();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private BinaryTreeElement<T> search(BinaryTreeElement<T> temp,
			BinaryTreeElement<T> node) {
		for (BinaryTreeElement<T> element : queue.getQueue())
			if (element.equals(node))
				return element;
		return null;
	}

	private BinaryTreeElement<T> getLeftmostRight(BinaryTreeElement<T> element) {
		if (element != null)
			while (element.hasLeft())
				element = element.getLeft();
		return element;
	}

	private BinaryTreeElement<T> getParent(BinaryTreeElement<T> element) {
		int nodeIndex = queue.getQueue().indexOf(element);
		double parentIndex = 0;
		if (isInt((nodeIndex - 1.0) / 2))
			parentIndex = (int) (nodeIndex - 1) / 2;
		else if (isInt((nodeIndex - 2.0) / 2))
			parentIndex = (int) (nodeIndex - 2) / 2;
		return queue.getQueue().get((int) parentIndex);
	}
}
