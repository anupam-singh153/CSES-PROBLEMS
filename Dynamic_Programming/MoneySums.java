import java.io.*;
import java.util.*;

public class MoneySums {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] coins = new int[n];
        int total = 0;
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
            total += coins[i];
        }
        
        boolean[] dp = new boolean[total+1];
        dp[0] = true;
        
        for (int coin : coins) {
            for (int j = total; j >= coin; j--) {
                if (dp[j - coin]) {
                    dp[j] = true;
                }
            }
        }
        
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= total; i++) {
            if (dp[i]) {
                count++;
                sb.append(i).append(" ");
            }
        }
        
        pw.println(count);
        pw.println(sb.toString().trim());
        pw.flush();
    }
}
