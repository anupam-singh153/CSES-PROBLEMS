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
        
        TreeSet<Long> set = new TreeSet<>();
        set.add(0L);
        
        long max = Long.MIN_VALUE;
        for (int i = a; i <= n; i++) {
            if (i > b) {
                set.remove(prefix[i - b - 1]);
            }
            max = Math.max(max, prefix[i] - set.first());
            set.add(prefix[i - a + 1]);
        }
        
        pw.println(max);
        pw.flush();
    }
}
