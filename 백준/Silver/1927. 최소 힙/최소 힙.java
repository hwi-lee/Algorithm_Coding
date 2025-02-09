import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());

			if (n != 0)
				q.offer(n);
			else {
				if (q.isEmpty())
					bw.write(String.valueOf(0) + "\n");
				else
					bw.write(String.valueOf(q.poll()) + "\n");

			}
		}
		bw.flush();
		bw.close();

	}

}
