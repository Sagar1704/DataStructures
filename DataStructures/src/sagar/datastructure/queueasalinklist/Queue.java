package sagar.datastructure.queueasalinklist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Perform Queue operations
 * 
 * @author Sagar
 */
public class Queue {
	private QueueElement start;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Queue queue = new Queue();
		String choice = null;

		do {
			queue.displayQ();
			queue.displayMenu();
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
					queue.enqueue();
					break;
				case 2:
					queue.dequeue();
					break;
				case 3:
					queue.displayQ();
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
				System.out.println("Press any key to continue.");
				new BufferedReader(new InputStreamReader(System.in)).readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} while (choice == null || !choice.equals("4"));

	}

	/**
	 * Display Menu driven screen
	 */
	private void displayMenu() {
		System.out.println("\nQueue Operations::");
		System.out.println("\n(1)Enqueue");
		System.out.println("\n(2)Dequeue");
		System.out.println("\n(3)Display");
		System.out.println("\n(4)Exit");
		System.out.print("\nEnter your choice::");
	}

	/**
	 * Display Queue elements
	 */
	private void displayQ() {
		if (!isQEmpty()) {
			System.out.println("**********My Queue**********");
			System.out.print("START --> ");
			QueueElement temp = getStart();
			while (temp != null) {
				System.out.print(temp.getData() + " --> ");
				temp = temp.getNext();
			}
			System.out.println("END");
			System.out.println("****************************");
		} else
			System.out.println("****Queue is Empty****");

	}

	/**
	 * Check if Queue is empty
	 * 
	 * @return true - If queue is empty, false - If queue has elements
	 */
	private boolean isQEmpty() {
		if (start == null)
			return true;
		return false;
	}

	/**
	 * Insert an element in the queue
	 */
	public void enqueue() {
		System.out.println("**********ENQUEUE**********");
		System.out.print("\nEnter data to be enqueued :: ");
		try {
			String data = new BufferedReader(new InputStreamReader(System.in))
					.readLine();
			try {
				int number = Integer.parseInt(data);
				QueueElement qData = new QueueElement(number);
				if (isQEmpty())
					start = qData;
				else
					getEnd().setNext(qData);
				System.out.println("***************************");
			} catch (NumberFormatException e) {
				System.out.println("Please enter a number.");
				System.out.println("***************************");
				enqueue();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Remove an element from the queue
	 */
	public void dequeue() {
		System.out.println("**********DEQUEUE**********");
		if (!isQEmpty()) {
			System.out.println("Dequeued :: " + start.getData());
			start = start.getNext();
		} else
			System.out
					.println("Queue is empty. Cannot dequeue an empty Queue.");
		System.out.println("***************************");
	}

	/**
	 * @return starting node of the Queue
	 */
	public QueueElement getStart() {
		return start;
	}

	public void setStart(QueueElement start) {
		this.start = start;
	}

	/**
	 * @return last node in the Queue
	 */
	public QueueElement getEnd() {
		QueueElement temp = getStart();
		while (temp.getNext() != null)
			temp = temp.getNext();

		return temp;
	}
}
