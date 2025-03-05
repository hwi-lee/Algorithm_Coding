import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class P implements Comparable<P> {
	int s, e, w;

	public P(int s, int e, int w) {
		this.s = s;
		this.e = e;
		this.w = w;

	}

	// @Override
	public int compareTo(P o) {
		// return this.w - o.w;
		return Integer.compare(this.w, o.w);
	}

}

public class Main {

	static int V, E, ans;
	static boolean visited[][];
	static PriorityQueue<P> q = new PriorityQueue<>();
	static int[] parent, rank;

	public static boolean union(int s, int e) {
		s = find(s);
		e = find(e);

		if(s==e)
			return false;
		
		
		if(rank[s]<rank[e]) {
			parent[s]=e;
			rank[e]+=rank[s];
		}else {
			parent[e]=s;
			rank[s]+=rank[e];
		}
		return true;
		
		
	}

	public static int find(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
		
	}

	public static void kruskal() {
		while (!q.isEmpty()) {
			P p = q.poll();
			if (union(p.s,p.e)) {
				ans += p.w;
				union(p.s, p.e);
			}
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		parent = new int[V + 1];
		rank = new int[V + 1];

		for (int i = 1; i <= V; i++) {
			parent[i] = i;
			rank[i]=i;

		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			q.offer(new P(s, e, w));
		}

		kruskal();
		System.out.println(ans);
	}

}
