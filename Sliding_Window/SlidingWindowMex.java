import java.io.*;
import java.util.*;

public class SlidingWindowMex {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> freq = new HashMap<>();
        TreeSet<Integer> candidates = new TreeSet<>();

        // Initially add all possible mex candidates 0...n
        for (int i = 0; i <= n; i++) {
            candidates.add(i);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int val = arr[i];

            freq.put(val, freq.getOrDefault(val, 0) + 1);
            if (freq.get(val) == 1) {
                candidates.remove(val);
            }

            if (i >= k) {
                int outVal = arr[i - k];
                int cnt = freq.get(outVal);
                if (cnt == 1) {
                    freq.remove(outVal);
                    candidates.add(outVal);
                } else {
                    freq.put(outVal, cnt - 1);
                }
            }

            if (i >= k - 1) {
                sb.append(candidates.first()).append(" ");
            }
        }

        pw.println(sb.toString().trim());
        pw.flush();
    }
}
