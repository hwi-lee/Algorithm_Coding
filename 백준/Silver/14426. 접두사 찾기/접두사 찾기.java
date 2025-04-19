import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, cnt;
	static TriNode root;

	static class TriNode {
		TriNode[] children;
		boolean isNumber;

		public TriNode() {
			this.children = new TriNode[26];
			this.isNumber = false;
		}
	}

	static void Insert(String str) {
		TriNode cur = root;

		for (int i = 0; i < str.length(); i++) {
			int idx = str.charAt(i) - 'a';

			if (cur.children[idx] == null) {
				cur.children[idx] = new TriNode();

				//cur = cur.children[idx];
			}
			cur = cur.children[idx];
		}
		cur.isNumber = true;
	}

	static boolean find(String str) {
		TriNode cur = root;

		int count=0;
		for (int i = 0; i < str.length(); i++) {
			int idx = str.charAt(i) - 'a';

			if (cur.children[idx] == null)
				return false;

			count++;
			cur = cur.children[idx];
		}
		if (count==str.length()) {
			return true;
		} else
			return false;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		root = new TriNode();
		for (int i = 0; i < N; i++) {
			String s=br.readLine();
			Insert(s);
		}
		for(int i=0;i<M;i++) {
			String s=br.readLine();
			//System.out.println(s);
			if(find(s)) {
				cnt++;
			}
		}
		System.out.println(cnt);

	}

}
