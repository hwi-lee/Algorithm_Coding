
import java.util.Scanner;

public class Main {

	static int N;
	static int r, c, cnt;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();
		cnt = 0;
		z(0, 0, 1 << N);// 1<<N == 2^N

	}

	public static void z(int rr, int cc, int width) {

		if (rr == r && cc == c) {
			System.out.println(cnt);

			return;
		}
		if (r >= rr && r < rr + width && c >= cc && c < cc + width) {
			int w = width / 2;
			z(rr, cc, w);
			z(rr, cc + w, w);
			z(rr + w, cc, w);
			z(rr + w, cc + w, w);

		} else
			cnt += width * width;

	}

}
