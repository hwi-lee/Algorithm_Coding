import java.util.*;
import java.awt.*;
import java.io.*;

public class Main {

	static int N, M;
	static int[] answer;
	static int cnt=1;
	static void dfs(ArrayList graph[], boolean visited[], int R) {

		visited[R - 1] = true;
		answer[R-1]=cnt;
		for (int i = 0; i < graph[R - 1].size(); i++) {
			int nextNode = (int) graph[R - 1].get(i);
			if (!visited[nextNode - 1]) {
				cnt++;
				dfs(graph, visited, nextNode);
			}
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		boolean visited[] = new boolean[N];

		answer=new int[N];
		ArrayList<Integer> graph[] = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			graph[u - 1].add(v);
			graph[v - 1].add(u);

		}

		for (int i = 0; i < N; i++) {
			Collections.sort(graph[i],Collections.reverseOrder());
		}

		dfs(graph, visited, R);
        
		for(int i=0;i<answer.length;i++) {
			System.out.println(answer[i]);
		}
		bw.flush();

	}

}