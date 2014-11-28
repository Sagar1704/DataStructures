package sagar.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Eucladian {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			int x11 = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
			int x21 = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
			
			int x12 = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
			int x22 = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
			
			int x13 = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
			int x23 = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
			
			System.out.println((x11 - x12)*(x11 - x12) + (x21 - x22)*(x21 - x22));
			System.out.println((x11 - x13)*(x11 - x13) + (x21 - x23)*(x21 - x23));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
