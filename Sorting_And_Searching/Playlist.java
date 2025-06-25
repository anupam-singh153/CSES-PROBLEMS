import java.io.*;
import java.util.*;

public class Playlist {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        
        Map<Integer, Integer> lastSeen = new HashMap<>();
        int max = 0, left = 0;
        
        for (int right = 0; right < n; right++) {
            if (lastSeen.containsKey(nums[right])) {
                left = Math.max(left, lastSeen.get(nums[right]) + 1);
            }
            lastSeen.put(nums[right], right);
            max = Math.max(max, right - left + 1);
        }
        
        pw.println(max);
        pw.flush();
    }
}
