package practice;

import java.util.*;

public class Arraylist {
	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the ending number: ");
		int b = s.nextInt();
		System.out.println("Enter the name instead of the number that divisible by 3: ");
		String c = s.next();
		System.out.println("Enter the name instead of the number that divisible by 5: ");
		String d = s.next();
		System.out.println("Enter the name instead of the number that divisible by 3 and 5: ");
		String e = s.next();
		String arr[] = new String[b];
		List<String> a = new ArrayList<>();
		for (int i = 1; i <= b; i++) {
			if (i % 3 == 0 && i % 5 == 0) {
				arr[i - 1] = (e);
				a.add(e);
			} else if (i % 3 == 0) {
				arr[i - 1] = (c);
				a.add(c);
			} else if (i % 5 == 0) {
				arr[i - 1] = (d);
				a.add(d);
			} else {
				arr[i] = String.valueOf(i);
				a.add(arr[i]);
			}
		}
		System.out.print(a);
	}
}
