import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, K, ans;
	static int[] arr;

	static void function(int s, int e) {
		while (s < e) {
			if (arr[s] + arr[e] <= K) {
				ans++;
				e--;
				s++;
			} else if (arr[s] + arr[e] > K) {
				e--;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		function(0, arr.length - 1);
		System.out.println(ans);
	}

}
