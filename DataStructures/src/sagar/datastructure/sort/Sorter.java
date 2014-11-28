package sagar.datastructure.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Sorter {
	ArrayList<Integer> list;

	public ArrayList<Integer> getList() {
		return list;
	}

	public void setList(ArrayList<Integer> list) {
		this.list = list;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Sorter sorter = new Sorter();
		String choice = null;
		do {
			sorter.displayMenu();
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
					sorter.setList(sorter.createList());
					break;
				case 2:
					System.out.println(sorter.toString());
					break;
				case 3:
					String sorterChoice = null;
					do {
						sorter.displaySubMenu();
						try {
							sorterChoice = new BufferedReader(
									new InputStreamReader(System.in))
									.readLine();
						} catch (IOException e) {
							e.printStackTrace();
						}

						if (sorterChoice != null) {

							int ch1 = -1;
							try {
								ch1 = Integer.parseInt(sorterChoice);
							} catch (NumberFormatException e) {
								// Wrong choice
							}

							switch (ch1) {
							case 1:
								System.out.println(sorter.toString());
								sorter.heapsort(sorter.getList());
								break;
							case 2:
								System.out.println("****See You****");
								break;
							default:
								System.out
										.println("\nWrong choice. Enter Again.");
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
					} while (sorterChoice == null || !sorterChoice.equals("2"));
					break;
				case 4:
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

		} while (choice == null || !choice.equals("4"));
	}

	private void displayMenu() {
		System.out.println("*********Sorting***********");
		System.out.println("(1) \"Create/Add to\" list");
		System.out.println("(2) Display the entered list");
		System.out.println("(3) Go to sorting menu");
		System.out.println("(4) Exit");
		System.out.println("***************************");
		System.out.print("Enter your choice:: ");
	}

	/**
	 * Display sorting method's sub menu
	 */
	private void displaySubMenu() {
		System.out.println("***************************");
		System.out.println("*******SORTER**********");
		System.out.println("(1) Heap sort");
		System.out.println("(2) Go Back to main menu");
		System.out.println("***********************");
		System.out.print("Enter your choice:: ");
	}

	/**
	 * Create new unsorted list
	 * 
	 * @return
	 */
	private ArrayList<Integer> createList() {
		if (isListEmpty())
			list = new ArrayList<Integer>();
		String choice = null;
		do {
			System.out.print("Enter the list member:: ");
			try {
				list.add(Integer.parseInt(new BufferedReader(
						new InputStreamReader(System.in)).readLine()));
				System.out.print("Done?(y):: ");
				choice = new BufferedReader(new InputStreamReader(System.in))
						.readLine();
			} catch (IOException e) {
				System.out.println("Some problem with the input");
				return createList();
			} catch (NumberFormatException e) {
				System.out.println("Please enter a number");
				return createList();
			}
		} while (!choice.equalsIgnoreCase("y"));
		return list;
	}

	/**
	 * Heap sort
	 * 
	 * @param list
	 */
	private void heapsort(ArrayList<Integer> list) {
		ArrayList<Integer> sortedList = new ArrayList<Integer>(build(list));

		System.out.print("List after building Heap:: ");
		System.out.println(sortedList.toString());
		int pass = 1;
		for (int i = sortedList.size() - 1; i >= 0;) {
			Collections.swap(sortedList, 0, i--);

			sortedList = heapify(sortedList, 0, i);
			System.out.print("PASS " + pass++ + "::");
			System.out.println(sortedList.toString());
		}
		System.out.print("Sorted list:: ");
		System.out.println(sortedList.toString());
	}

	/**
	 * Build heap like structure from ArrayList
	 * 
	 * @param list
	 * @return
	 */
	private ArrayList<Integer> build(ArrayList<Integer> list) {
		for (int i = (list.size() / 2) - 1; i >= 0; i--) {
			list = heapify(list, i, list.size() - 1);
		}
		return list;
	}

	/**
	 * Heapify
	 * 
	 * @param list
	 * @param index
	 * @param size
	 * @return
	 */
	private ArrayList<Integer> heapify(ArrayList<Integer> list, int index,
			int size) {
		int maxIndex;

		int leftChildIndex = getLeft(index);
		int rightChildIndex = getRight(index);

		if (leftChildIndex <= size
				&& list.get(leftChildIndex) > list.get(index))
			maxIndex = leftChildIndex;
		else
			maxIndex = index;

		if (rightChildIndex <= size
				&& list.get(rightChildIndex) > list.get(maxIndex))
			maxIndex = rightChildIndex;

		if (maxIndex != index) {
			Collections.swap(list, index, maxIndex);
			heapify(list, maxIndex, size);
		}
		return list;
	}

	/**
	 * Get Left child
	 * 
	 * @param index
	 * @return
	 */
	private int getLeft(int index) {
		return 2 * index + 1;
	}

	/**
	 * Get Right child
	 * 
	 * @param index
	 * @return
	 */
	private int getRight(int index) {
		return 2 * index + 2;
	}

	@Override
	public String toString() {
		if (list != null)
			return "Original [list=" + list.toString() + "]";
		return null;
	}

	/**
	 * @return true if list is empty
	 */
	private boolean isListEmpty() {
		if (list == null || list.size() == 0)
			return true;
		return false;
	}
}
