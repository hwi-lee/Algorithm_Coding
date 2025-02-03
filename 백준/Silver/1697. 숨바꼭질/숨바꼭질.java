import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {

	static int N, K, cnt, ans;
	static boolean visited[] = new boolean[100001];

	static void bfs() {
		Queue<Integer> q = new LinkedList();

		cnt = 0;
		q.offer(N);
		visited[N] = true;
		ans = 0;
		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				int current = q.poll();

				if (current == K) {

					return;
				}
				if (current + 1 <= 100000 && !visited[current + 1]) {
					visited[current + 1] = true;
					q.offer(current + 1);
				}
				if (current - 1 >= 0 && !visited[current - 1]) {
					visited[current - 1] = true;
					q.offer(current - 1);
				}
				if (current * 2 <= 100000 && !visited[current * 2]) {
					visited[current * 2] = true;
					q.offer(current * 2);
				}

			}
			ans++;

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		bfs();
		bw.write(String.valueOf(ans));
		bw.flush();
	}

}
