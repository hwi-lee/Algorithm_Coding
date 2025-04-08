import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
	static int n, t, cnt;
	static TriNode root;
	static int result = 0;
	static String[] str;
	static HashSet<String> h = new HashSet<String>();

	static class TriNode {
		TriNode[] children;
		boolean isNumber;

		public TriNode() {
			this.children = new TriNode[10];
			this.isNumber = false;
		}
	}

	static void Insert(String str) {
		TriNode cur = root;

		for (int i = 0; i < str.length(); i++) {
			int idx = str.charAt(i) - '0';

			if (cur.children[idx] == null) {
				cur.children[idx] = new TriNode();
			}
			cur = cur.children[idx];
		}

		cur.isNumber = true;
	}

	static boolean find(String str) {
		TriNode cur = root;

		for (int i = 0; i < str.length(); i++) {
			int idx = str.charAt(i) - '0';

			if (cur.children[idx] == null)
				return false;

			cur = cur.children[idx];
		}
		if (cur.isNumber == true) { // 마지막 자리에 true이면서 배열 있는 경우
			for (int j = 0; j < 10; j++) {
				if (cur.children[j] != null) {
					return false;
				}
			}
			return true;
		} else
			return false;

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		t = Integer.parseInt(br.readLine());
		for (int test = 0; test < t; test++) {
			root = new TriNode();//
			cnt = 0;
			n = Integer.parseInt(br.readLine());
			str = new String[n];
			for (int i = 0; i < n; i++) {
				String s = br.readLine();
				str[i] = s;
				h.add(s);
				Insert(s);
			}

			for (int i = 0; i < str.length; i++) {
				if (find(str[i]) == false) {
					cnt++;
				}
			}

			if (cnt > 0 || str.length != h.size()) // 같은 값 존재 하는지 체크
				System.out.println("NO");
			else
				System.out.println("YES");
			h.clear();
		}
	}

}
