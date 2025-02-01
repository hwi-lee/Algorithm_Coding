import java.util.*;
import java.awt.*;
import java.io.*;

class Stack<T> {
	int top;
	int size;
	Object[] s;

	public Stack(int size) {
		top = -1;
		this.size = size;
		s = new Object[size];
	}

	public void push(char n) {
		s[++top] = n;
	}

	int isEmpty() {
		if (top == -1)
			return 1;
		else
			return 0;

	}

	Object pop() {
		Object p = s[top];
		s[top] = 0;
		top--;
		return p;
	}

	Object peek() {
		return s[top];
	}
}

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		Stack<Integer> s = new Stack<Integer>(50);
        
		for (int j = 0; j < T; j++) {
			String str = br.readLine();
			int cnt = 0;
			s.top = -1;
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == '(') {
					s.push('(');
				} else if (str.charAt(i) == ')') {
					if (s.isEmpty() == 1)
						cnt++;
					else
						s.pop();
				}
			}
			if (s.isEmpty() == 1 && cnt == 0)
				bw.write("YES" + "\n");
			else
				bw.write("NO" + "\n");

		}

		bw.flush();
		bw.close();
	}

}
