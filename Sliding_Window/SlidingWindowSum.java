import java.io.*;
import java.util.*;

public class SlidingWindowSum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long x = Long.parseLong(st.nextToken());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long c = Long.parseLong(st.nextToken());

        long[] window = new long[k];
        int wIndex = 0;
        long sum = 0;
        long xor = 0;

        for (int i = 0; i < n; i++) {
            if (i > 0) {
                x = (a * x + b) % c;
            }

            if (i < k) {
                sum += x;
                window[wIndex++] = x;
                if (i == k - 1) {
                    xor ^= sum;
                }
            } else {
                sum += x;
                sum -= window[wIndex % k];
                window[wIndex % k] = x;
                wIndex++;
                xor ^= sum;
            }
        }

        pw.println(xor);
        pw.flush();
    }
}
