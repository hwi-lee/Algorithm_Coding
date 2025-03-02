import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		int x = Integer.parseInt(br.readLine());
		Arrays.sort(nums);
		int s = 0;
		int e = N - 1;
		int cnt = 0;
		while (s != e && s < e) {
			if (nums[s] + nums[e] == x) {
				s++;
				e--;
				cnt++;
			}

			if (nums[s] + nums[e] > x) {
				e--;
			}
			if (nums[s] + nums[e] < x) {
				s++;
			}

		}
		System.out.println(cnt);
	}

}
