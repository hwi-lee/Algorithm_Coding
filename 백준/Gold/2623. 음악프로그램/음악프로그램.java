import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int N, M;
	static ArrayList<Integer> list[];
	static int[] indegree;
	static int cnt = 0;
	static StringBuilder sb = new StringBuilder();

	public static void function() {
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < indegree.length; i++) {
			if (indegree[i] == 0)
				q.add(i + 1);
		}
		while (!q.isEmpty()) {
			int p = q.poll();
			sb.append(p).append("\n");
			cnt++;
			for (int i = 0; i < list[p - 1].size(); i++) {
				int r = list[p - 1].get(i);
				indegree[r - 1]--;
				if (indegree[r - 1] == 0)
					q.add(r);
			}

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		indegree = new int[N];
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			for (int j = 0; j < k; j++) {
				a.add(Integer.parseInt(st.nextToken()));
			}
			for (int r = 0; r < k - 1; r++) {
				int u = a.get(r);
				int v = a.get(r + 1);
				list[u - 1].add(v);
				indegree[v - 1]++;
			}
			a.clear();
		}

		function();

		if (cnt < N)
			System.out.println(0);
		else
			System.out.print(sb.toString());

	}

}
