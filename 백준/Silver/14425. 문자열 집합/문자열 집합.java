import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	static int N, M, cnt;
	static HashSet<String> h = new HashSet<String>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			h.add(s);
		}

		for (int i = 0; i < M; i++) {
			String s = br.readLine();
			if (h.contains(s))
				cnt++;
		}
		System.out.println(cnt);

	}

}
