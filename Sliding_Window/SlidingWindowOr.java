import java.io.*;
import java.util.*;

public class SlidingWindowOr {
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
        long ansXor = 0;

        for (int i = 0; i < n; i++) {
            if (i > 0) {
                x = (a * x + b) % c;
            }

            window[wIndex % k] = x;
            wIndex++;

            if (i >= k - 1) {
                long or = 0;
                for (int j = 0; j < k; j++) {
                    or |= window[(wIndex - k + j) % k];
                }
                ansXor ^= or;
            }
        }

        pw.println(ansXor);
        pw.flush();
    }
}
