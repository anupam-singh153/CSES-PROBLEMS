import java.io.*;
import java.util.*;
 
public class Removing_Digits {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        
        if (n == 0) {
            System.out.println(0);
            return;
        }
        
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int i = 1; i <= n; i++) {
            int num = i;
            while (num > 0) {
                int digit = num % 10;
                num /= 10;
                if (i - digit >= 0 && dp[i - digit] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - digit] + 1);
                }
            }
        }
        
        System.out.println(dp[n]);
    }
}
