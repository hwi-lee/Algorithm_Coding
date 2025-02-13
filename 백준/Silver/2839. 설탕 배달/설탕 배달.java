import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());

		int x = N / 3;
		int y = N / 5;
		int cnt = 0;
		int min = 5000;
		for (int i = 0; i <= x; i++) {
			for (int j = 0; j <= y; j++) {
				if (3 * i + 5 * j == N) {
					cnt++;
					min = Math.min(min, i + j);
				}
			}
		}

		if (cnt == 0)
			System.out.println("-1");
		else
			System.out.println(min);

	}

}
