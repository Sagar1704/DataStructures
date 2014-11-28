package sagar.datastructure.stack;

import java.util.ArrayList;

public class Stack<T> {
	ArrayList<T> stack;
	int top;

	public Stack() {
		super();
		stack = new ArrayList<T>();
		this.top = -1;
	}

	public ArrayList<T> getStack() {
		return stack;
	}

	public int getTop() {
		return top;
	}

	public void push(T data) {
		stack.add(data);
		top++;
	}

	public T pop() {
		T data = null;
		if (!isEmpty()) {
			data = stack.get(top);
			stack.remove(top--);
		}
		return data;
	}

	public boolean isEmpty() {
		if (top == -1)
			return true;
		return false;
	}

}
