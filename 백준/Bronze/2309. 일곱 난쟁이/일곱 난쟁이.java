import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static int[] arr, nums;
	static boolean[] visited;

	public static void combi(int cnt, int start, int tot) {
		if (cnt == 7) {

			if (tot == 100) {
				Arrays.sort(nums);
				for (int j = 0; j < nums.length; j++)
					System.out.println(nums[j]);
				System.exit(0);
			}
			return;
		}
		for (int i = start; i < arr.length; i++) {
			visited[i] = true;
			nums[cnt] = arr[i];
			combi(cnt + 1, i + 1, tot + arr[i]);
			nums[cnt] = 0;
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		arr = new int[9];
		nums = new int[7];
		visited = new boolean[9];

		for (int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		combi(0, 0, 0);
	}

}
