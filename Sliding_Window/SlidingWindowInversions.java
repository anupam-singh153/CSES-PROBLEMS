import java.io.*;
import java.util.*;

public class SlidingWindowInversions {
    static class Fenwick {
        long[] bit;
        int n;

        Fenwick(int size) {
            n = size + 2;  // extra space
            bit = new long[n];
        }

        void update(int idx, int delta) {
            while (idx < n) {
                bit[idx] += delta;
                idx += idx & -idx;
            }
        }

        long query(int idx) {
            long res = 0;
            while (idx > 0) {
                res += bit[idx];
                idx -= idx & -idx;
            }
            return res;
        }

        long query(int l, int r) {
            return query(r) - query(l - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        TreeSet<Integer> values = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            values.add(arr[i]);
        }

        // Coordinate compression
        Map<Integer, Integer> compress = new HashMap<>();
        int idx = 1;
        for (int v : values) {
            compress.put(v, idx++);
        }

        int maxVal = idx;
        Fenwick fenwick = new Fenwick(maxVal);

        long inv = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int val = compress.get(arr[i]);

            // Add new element
            inv += fenwick.query(maxVal) - fenwick.query(val);
            fenwick.update(val, 1);

            // Remove old element
            if (i >= k) {
                int outVal = compress.get(arr[i - k]);
                fenwick.update(outVal, -1);
                inv -= fenwick.query(outVal - 1);
            }

            if (i >= k - 1) {
                sb.append(inv).append(" ");
            }
        }

        pw.println(sb.toString().trim());
        pw.flush();
    }
}
