import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
	static int N, M;
	static int A[], B[], H[];
	static HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();

	public static boolean function(int X) {
		int s = 0;
		int e = H.length - 1;
		while (s <= e) {
			int mid = (s + e) / 2;
			if (X < H[mid]) {
				e = mid - 1;
			} else if (X > H[mid]) {
				s = mid + 1;
			} else if (X == H[mid]) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			if (h.containsKey(A[i]) == false) {
				h.put(A[i], 1);
			} else {
				h.replace(A[i], h.get(A[i]) + 1);
			}
		}
		M = Integer.parseInt(br.readLine());
		B = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}

		List<Integer> l = new ArrayList<>(h.keySet());

		H = new int[l.size()];
		for (int i = 0; i < l.size(); i++) {
			H[i] = l.get(i);
		}
		Arrays.sort(H);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < B.length; i++) {
			int X = B[i];
			if (function(X) == true)
				sb.append(h.get(X)).append(" ");
			else
				sb.append(0).append(" ");
		}

		System.out.println(sb.toString());

	}

}
