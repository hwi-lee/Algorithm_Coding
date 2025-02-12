import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int arr[] = new int[26];
		for (int i = 0; i < s.length(); i++) {
			int index = s.charAt(i) - 97;
			// System.out.println(index);
			arr[index]++;
		}
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");

	}

}
