package sagar.datastructure.queueasalinklist;

/**
 * Queue node contains:- 1. Data, 2. Pointer to the next node in the Queue
 * 
 * @author Sagar
 */
public class QueueElement {

	private int data;
	private QueueElement next;

	public QueueElement() {
		super();
		this.next = null;
	}

	public QueueElement(int data) {
		super();
		this.data = data;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public QueueElement getNext() {
		return next;
	}

	public void setNext(QueueElement next) {
		this.next = next;
	}

}
