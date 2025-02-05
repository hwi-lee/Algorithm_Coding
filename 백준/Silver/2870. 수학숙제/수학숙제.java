import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main{
	static int N;
	static ArrayList<String> ans;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(br.readLine());
		ans = new ArrayList();

		int k = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			StringBuilder sb = new StringBuilder();

			for (int j = 0; j < s.length(); j++) {

				if (s.charAt(j) - '0' >= 0 && s.charAt(j) - '0' <= 9) {

					k = j;
					while (s.charAt(k) - '0' >= 0 && s.charAt(k) - '0' <= 9) {

						sb.append(s.charAt(k));
						k++;
						if (k >= s.length()) {
							break;
						}
					}
					j = k;
				}
				if (sb.length() > 0) {
					ans.add(sb.toString());
					sb.delete(0, sb.length());
				}

			}

		}

		BigInteger arr[] = new BigInteger[ans.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new BigInteger(ans.get(i));
		}

		Arrays.sort(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}

	}

}
