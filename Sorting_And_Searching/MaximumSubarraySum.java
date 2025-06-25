import java.io.*;
import java.util.*;

public class MaximumSubarraySum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        long max = Long.MIN_VALUE, current = 0;
        while (n-- > 0) {
            int num = Integer.parseInt(st.nextToken());
            current = Math.max(num, current + num);
            max = Math.max(max, current);
        }
        
        pw.println(max);
        pw.flush();
    }
}
