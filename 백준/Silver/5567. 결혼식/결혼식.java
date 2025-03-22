import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main{

	static int n, m, cnt;
	static ArrayList<Integer> graph[];
	static int[] ans;

	public static void function() {
		for (int i = 0; i < graph[0].size(); i++) {
			int node = graph[0].get(i);
			ans[node - 1]++;
			for (int j = 0; j < graph[node - 1].size(); j++) {
				ans[graph[node - 1].get(j) - 1]++;
			}
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		graph = new ArrayList[n];
		ans = new int[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u - 1].add(v);
			graph[v - 1].add(u);
		}
		function();
		for (int i = 1; i < ans.length; i++) {
			if (ans[i] > 0)
				cnt++;
		}
		System.out.println(cnt);
	}

}
