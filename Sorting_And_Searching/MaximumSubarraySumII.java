import java.io.*;
import java.util.*;

public class MaximumSubarraySumII {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        
        long[] prefix = new long[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + Long.parseLong(st.nextToken());
        }
        
        // Using TreeMap to handle duplicate prefix sums
        TreeMap<Long, Integer> map = new TreeMap<>();
        map.put(0L, 1); // Initialize with prefix[0]
        
        long maxSum = Long.MIN_VALUE;
        
        for (int i = a; i <= n; i++) {
            // Remove elements that are outside the window when i > b
            if (i > b && map.containsKey(prefix[i - b - 1])) {
                int cnt = map.get(prefix[i - b - 1]);
                if (cnt == 1) {
                    map.remove(prefix[i - b - 1]);
                } else {
                    map.put(prefix[i - b - 1], cnt - 1);
                }
            }
            
            // Get the minimum prefix sum in the current window
            if (!map.isEmpty()) {
                long minPrefix = map.firstKey();
                maxSum = Math.max(maxSum, prefix[i] - minPrefix);
            }
            
            // Add the current prefix sum to the map (for next windows)
            if (i - a + 1 >= 0) {
                long currentPrefix = prefix[i - a + 1];
                map.put(currentPrefix, map.getOrDefault(currentPrefix, 0) + 1);
            }
        }
        
        pw.println(maxSum);
        pw.flush();
    }
}