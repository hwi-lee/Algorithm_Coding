import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	static int T, N, cnt, ans = 1;
	static HashMap<String, String> h;
	static ArrayList<String> a;

	public static void function() {
		for (String value : h.values()) {
			if (!a.contains(value))
				a.add(value);
		}
		int size = a.size();
		for (int i = 0; i < size; i++) {
			cnt = 0;
			for (String value : h.values()) {
				if (a.get(i).equals(value)) {
					cnt++;
				}
			}
			ans *= cnt + 1;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			N = Integer.parseInt(br.readLine());
			h = new HashMap<String, String>();
			a = new ArrayList<String>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				String s1 = st.nextToken();
				String s2 = st.nextToken();
				h.put(s1, s2);
			}
			function();
			System.out.println(ans - 1);
			ans = 1;
		}
	}
}
