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
		Stack<Integer> s = new Stack<Integer>(100);

		while (true) {
			String str = br.readLine();
			if (str.equals("."))
				break;
			int cnt = 0;
			s.top = -1;
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == '(' || str.charAt(i) == '[') {
					s.push(str.charAt(i));
				} else if (str.charAt(i) == ')' || str.charAt(i) == ']') {
					if (s.isEmpty() == 1)
						cnt++;
					else {
						if ((char) s.peek() == '(' && str.charAt(i) == ')'
								|| (char) s.peek() == '[' && str.charAt(i) == ']')
							s.pop();
						else
							cnt++;

					}
				}
			}

			if (s.isEmpty() == 1 && cnt == 0)
				bw.write("yes" + "\n");
			else
				bw.write("no" + "\n");

		}

		bw.flush();
		bw.close();
	}

}
