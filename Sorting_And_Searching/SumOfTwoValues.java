import java.io.*;
import java.util.*;

public class SumOfTwoValues {
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
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int complement = x - nums[i];
            if (map.containsKey(complement)) {
                pw.println((map.get(complement) + 1) + " " + (i + 1));
                pw.flush();
                return;
            }
            map.put(nums[i], i);
        }
        
        pw.println("IMPOSSIBLE");
        pw.flush();
    }
}
