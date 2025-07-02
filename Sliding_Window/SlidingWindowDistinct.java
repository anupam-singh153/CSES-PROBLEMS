import java.io.*;
import java.util.*;

public class SlidingWindowDistinct {
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
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            freq.put(arr[i], freq.getOrDefault(arr[i], 0) + 1);

            if (i >= k) {
                int out = arr[i - k];
                freq.put(out, freq.get(out) - 1);
                if (freq.get(out) == 0) {
                    freq.remove(out);
                }
            }

            if (i >= k - 1) {
                sb.append(freq.size()).append(" ");
            }
        }

        pw.println(sb.toString().trim());
        pw.flush();
    }
}
