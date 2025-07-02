import java.io.*;
import java.util.*;

public class SlidingWindowMode {
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

        Map<Integer, Integer> countMap = new HashMap<>();
        TreeMap<Integer, TreeSet<Integer>> freqMap = new TreeMap<>();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int val = arr[i];

            // Remove old frequency entry if exists
            int oldFreq = countMap.getOrDefault(val, 0);
            if (oldFreq > 0) {
                TreeSet<Integer> set = freqMap.get(oldFreq);
                set.remove(val);
                if (set.isEmpty()) {
                    freqMap.remove(oldFreq);
                }
            }

            // Update to new frequency
            int newFreq = oldFreq + 1;
            countMap.put(val, newFreq);
            freqMap.computeIfAbsent(newFreq, z -> new TreeSet<>()).add(val);

            // Remove out of window
            if (i >= k) {
                int outVal = arr[i - k];
                int outFreq = countMap.get(outVal);
                TreeSet<Integer> set = freqMap.get(outFreq);
                set.remove(outVal);
                if (set.isEmpty()) {
                    freqMap.remove(outFreq);
                }

                if (outFreq == 1) {
                    countMap.remove(outVal);
                } else {
                    int newOutFreq = outFreq - 1;
                    countMap.put(outVal, newOutFreq);
                    freqMap.computeIfAbsent(newOutFreq, z -> new TreeSet<>()).add(outVal);
                }
            }

            if (i >= k - 1) {
                int maxFreq = freqMap.lastKey();
                int mode = freqMap.get(maxFreq).first();
                sb.append(mode).append(" ");
            }
        }

        pw.println(sb.toString().trim());
        pw.flush();
    }
}
