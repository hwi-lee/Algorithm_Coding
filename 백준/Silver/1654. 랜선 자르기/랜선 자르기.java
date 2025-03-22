import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

	static int K, N, sum;
	static long max, ans;
	static int[] arr;

	static int check(long mid) {
		sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i] / mid;
		}
		return sum;
	}

	static long function() {

		long s = 1;
		long e = max;
		while (s <= e) {
			long mid = (s + e) / 2;
			long num = check(mid);
			if (num < N) {
				e = mid - 1;
			} else if (num >= N) {
				ans = Math.max(ans, mid);
				s = mid + 1;
			}
		}
		return ans;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[K];
		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, arr[i]);
		}
		ans = function();
		System.out.println(ans);
	}

}
