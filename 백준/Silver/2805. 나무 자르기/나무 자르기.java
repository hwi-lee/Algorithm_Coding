import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static long M, sum;
	static int N;
	static int arr[];

	static long check(long mid) {
		sum = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > mid) {
				sum += arr[i] - mid;
			}
		}
		return sum;
	}

	static long function(long s, long e) {
		long l = s;
		long r = e;
		long result = 0;
		while (l <= r) {
			long mid = (l + r) / 2;
			long ans = check(mid);

			if (ans >= M) {
				result = mid;
				l = mid + 1;
			} else {
				r = mid - 1;
			}

		}
		return result;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		long ans = function(0, arr[arr.length - 1]);
		System.out.println(ans);
	}

}
