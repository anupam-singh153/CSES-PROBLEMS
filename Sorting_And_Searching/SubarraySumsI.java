import java.io.*;
import java.util.*;

public class SubarraySumsI {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        
        int[] nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        
        Map<Long, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0L, 1);
        long prefix = 0;
        long count = 0;
        
        for (int num : nums) {
            prefix += num;
            count += prefixCount.getOrDefault(prefix - x, 0);
            prefixCount.put(prefix, prefixCount.getOrDefault(prefix, 0) + 1);
        }
        
        pw.println(count);
        pw.flush();
    }
}
