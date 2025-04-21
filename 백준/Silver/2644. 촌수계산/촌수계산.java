import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, m, x, y, ans;
	static boolean visited[];
	static ArrayList<Integer> graph[];

	static int bfs(int x) {

		Queue<Integer> q = new ArrayDeque<Integer>();

		q.add(x);
		int p;
		int cnt=0;
		while (!q.isEmpty()) {
			ans++;
			int k = q.size();
			for (int j = 0; j < k; j++) {
				p = q.poll();
				if (p == y) {
					return ans;
				}
				for (int i = 0; i < graph[p - 1].size(); i++) {
					int node = graph[p - 1].get(i);
					if (!visited[node - 1]) {
						visited[node - 1] = true;
						q.add(node);
					}
				}
			}
			
		}

		return 0;

	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		graph = new ArrayList[n];
		visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<Integer>();
		}

		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());

		m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {

			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			graph[v - 1].add(u);
			graph[u - 1].add(v);

		}

	
		
		int n=bfs(x);

		System.out.println(n-1);

		
		
	}

}
