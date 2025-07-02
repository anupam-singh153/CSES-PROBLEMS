import java.io.*;
import java.util.*;

public class SlidingWindowMinimum {
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

        Deque<long[]> dq = new ArrayDeque<>();
        long xor = 0;

        for (int i = 0; i < n; i++) {
            if (i > 0) {
                x = (a * x + b) % c;
            }

            // Remove elements outside the window
            while (!dq.isEmpty() && dq.peekFirst()[1] <= i - k) {
                dq.pollFirst();
            }

            // Remove elements bigger than current from back
            while (!dq.isEmpty() && dq.peekLast()[0] >= x) {
                dq.pollLast();
            }

            dq.addLast(new long[]{x, i});

            if (i >= k - 1) {
                xor ^= dq.peekFirst()[0];
            }
        }

        pw.println(xor);
        pw.flush();
    }
}
