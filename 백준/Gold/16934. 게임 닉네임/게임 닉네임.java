import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N, cnt;
	static TriNode root;
	static StringBuilder sb = new StringBuilder();

	static class TriNode {
		TriNode[] children;
		boolean isNumber;
		int cnt;

		public TriNode() {
			this.children = new TriNode[26];
			this.isNumber = false;
			this.cnt = 1;
		}
	}

	static void Insert(String str) {
		TriNode cur = root;

		for (int i = 0; i < str.length(); i++) {
			int idx = str.charAt(i) - 'a';

			if (cur.children[idx] == null) {
				cur.children[idx] = new TriNode();

				// cur = cur.children[idx];
			}
			cur = cur.children[idx];
		}
		cur.isNumber = true;
	}

	static boolean find(String str) {
		TriNode cur = root;

		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			int idx = str.charAt(i) - 'a';

			if (cur.children[idx] == null) {
				sb.append(str.charAt(i));
				return false;
			}
			sb.append(str.charAt(i));
			count++;
			cur = cur.children[idx];
		}
		if (count == str.length() && cur.isNumber == true) {
			sb.append(Integer.toString(cur.cnt + 1));
			cur.cnt++;
			return true;
		} else
			return false;

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st = new StringTokenizer(br.readLine());
		root = new TriNode();
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			find(s);
			Insert(s);
			System.out.println(sb.toString());
			sb.setLength(0);
		}

	}

}
