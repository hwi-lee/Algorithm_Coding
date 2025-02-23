import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		long incident[] = new long[N - 1];
		long vertex[] = new long[N];
		for (int i = 0; i < N - 1; i++) {
			incident[i] = sc.nextInt();
		}
		for (int i = 0; i < N; i++) {
			vertex[i] = sc.nextInt();
		}
		long price = incident[0] * vertex[0];

		long start = vertex[0];
		for (int i = 1; i < N - 1; i++) {
			if (vertex[i] < start) {
				start = vertex[i];
			}
			price += start * incident[i];

		}
		System.out.println(price);

	}

}
