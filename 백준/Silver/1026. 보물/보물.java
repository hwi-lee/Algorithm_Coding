import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	static int N, S;
	static ArrayList<Integer> A, B;
	static boolean visited[];

	public static void function() {
		Collections.sort(A);
		Collections.sort(B, Collections.reverseOrder());

		for (int i = 0; i < N; i++) {
			S += A.get(i) * B.get(i);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		A = new ArrayList<Integer>();
		B = new ArrayList<Integer>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A.add(Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			B.add(Integer.parseInt(st.nextToken()));
		}
		function();
		System.out.println(S);
	}

}
