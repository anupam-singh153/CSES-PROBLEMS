import java.io.*;
import java.util.*;

public class DistinctValuesSubarray {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Map<Integer, Integer> lastSeen = new HashMap<>();
        long count = 0;
        int left = 0;
        
        for (int right = 0; right < n; right++) {
            // If current element was seen in current window, move left pointer
            if (lastSeen.containsKey(arr[right]) && lastSeen.get(arr[right]) >= left) {
                left = lastSeen.get(arr[right]) + 1;
            }
            
            // Update last seen position of current element
            lastSeen.put(arr[right], right);
            
            // Add all subarrays ending at right with distinct elements
            count += right - left + 1;
        }
        
        pw.println(count);
        pw.flush();
    }
}
