import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	static int N, M, min = Integer.MAX_VALUE, sum, ans = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<int[]> Home;
	static ArrayList<int[]> chicken;
	static ArrayList<int[]> chicken_min;

	public static void function() {

		sum = 0;
		for (int i = 0; i < Home.size(); i++) {
			min = Integer.MAX_VALUE;
			for (int j = 0; j < chicken_min.size(); j++) {
				int d = Math.abs(Home.get(i)[0] - chicken_min.get(j)[0])
						+ Math.abs(Home.get(i)[1] - chicken_min.get(j)[1]);
				min = Math.min(d, min);

			}
			sum += min;
		}
	}

	public static void combi(int c, int start) {

		if (c == M) {
			function();
			ans = Math.min(ans, sum);
			return;
		}

		for (int i = start; i < chicken.size(); i++) {

			visited[chicken.get(i)[0]][chicken.get(i)[1]] = true;
			chicken_min.add(new int[] { chicken.get(i)[0], chicken.get(i)[1] });
			combi(c + 1, i + 1);
			chicken_min.remove(chicken_min.size() - 1);
			visited[chicken.get(i)[0]][chicken.get(i)[1]] = false;

		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Scanner sc = new Scanner(System.in);

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];

		Home = new ArrayList<int[]>();
		chicken = new ArrayList<int[]>();
		chicken_min = new ArrayList<int[]>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					Home.add(new int[] { i, j });
				}
				if (map[i][j] == 2) {
					chicken.add(new int[] { i, j });
				}

			}
		}
		combi(0, 0);
		System.out.println(ans);
	}

}
