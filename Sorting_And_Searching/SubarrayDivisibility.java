import java.io.*;
import java.util.*;

public class SubarrayDivisibility {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        
        Map<Long, Integer> modCount = new HashMap<>();
        modCount.put(0L, 1);
        long prefix = 0;
        long count = 0;
        
        for (int num : nums) {
            prefix += num;
            long mod = ((prefix % n) + n) % n;
            count += modCount.getOrDefault(mod, 0);
            modCount.put(mod, modCount.getOrDefault(mod, 0) + 1);
        }
        
        pw.println(count);
        pw.flush();
    }
}
