import java.io.*;
import java.util.*;
 
public class Coin_Piles {
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	PrintWriter out = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());
 
        StringBuilder sb = new StringBuilder();
 
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
 
            boolean flag = (a + b) % 3 == 0 && a <= 2 * b && b <= 2 * a;
            sb.append(flag ? "YES" : "NO").append('\n');
        }
 
        out.print(sb);
	out.flush();
    }
}
