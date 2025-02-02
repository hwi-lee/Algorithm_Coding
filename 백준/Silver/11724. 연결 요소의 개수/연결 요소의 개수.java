import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static ArrayList<Integer> graph[];
	static boolean visited[];

	static void dfs(int n) {

		for (int i = 0; i < graph[n].size(); i++) {
			int nextnode = graph[n].get(i);
			if (!visited[nextnode - 1]) {
				visited[nextnode - 1] = true;
				dfs(nextnode - 1);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u - 1].add(v);
			graph[v - 1].add(u);
		}
		for (int i = 0; i < N; i++) {
			Collections.sort(graph[i]);
		}

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				dfs(i);
				cnt++;
			}
		}
		bw.write(String.valueOf(cnt));
		bw.flush();
		bw.close();

	}

}
