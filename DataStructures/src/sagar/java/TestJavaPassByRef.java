package sagar.java;

import sagar.datastructure.stack.Stack;

public class TestJavaPassByRef {
	private String name;
	private Stack<String> s;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestJavaPassByRef obj = new TestJavaPassByRef();
		obj.setName("beforeCallingTest");
		System.out.println("Before Calling :: " + obj.getName());
		
		obj.test(obj);
		
		System.out.println("After Calling :: " + obj.getName());
		
		obj.s = new Stack<String>();
		
		obj.s.push("Smart");
		obj.s.push("is");
		obj.s.push("Sagar");
		
		for (int i = 0; i < obj.s.getStack().size(); i++) {
			System.out.print(obj.s.pop() + " ");	
		}
		
	}
	
	public void test(TestJavaPassByRef ob) {
		ob.setName("InsideTest");
		System.out.println("Inside Test :: " + ob.getName());
		TestJavaPassByRef newOb = new TestJavaPassByRef();
		newOb.setName("afterCallingTest");
		ob = newOb;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
