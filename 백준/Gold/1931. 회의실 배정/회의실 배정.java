import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	static int s, e;

	static class Meeting implements Comparable<Meeting> {
		int s;
		int e;

		public Meeting(int s, int e) {
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Meeting o) {
			if (this.e == o.e)
				return Integer.compare(this.s, o.s);
			return Integer.compare(this.e, o.e);
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		ArrayList<Meeting> arr = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			arr.add(new Meeting(s, e));
		}
		Collections.sort(arr);

		int time = 0;
		int ans = 0;
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i).s >= time) {
				ans++;
				time = arr.get(i).e;
			}
		}
		System.out.println(ans);

	}

}
