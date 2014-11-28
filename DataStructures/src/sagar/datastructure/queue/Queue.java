package sagar.datastructure.queue;

import java.util.ArrayList;

public class Queue<T> {
	ArrayList<T> queue;

	public ArrayList<T> getQueue() {
		return queue;
	}

	public Queue() {
		super();
		this.queue = new ArrayList<T>();
	}
	
	public Queue(ArrayList<T> queue) {
		this.queue = new ArrayList<>();
		for (T t : queue) {
			this.queue.add(t);
		}
	}

	public void enQ(T data) {
		queue.add(data);
	}

	public T dQ() {
		T data = null;
		if (!isEmpty()) {
			data = queue.get(0);
			queue.remove(0);
		}
		return data;
	}

	public boolean isEmpty() {
		if (queue == null || queue.size() == 0)
			return true;
		return false;
	}

	public int getSize() {
		if (!isEmpty())
			return queue.size();
		return 0;
	}
}
