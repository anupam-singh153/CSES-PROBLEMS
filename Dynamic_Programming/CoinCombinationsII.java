import java.io.*;
import java.util.*;

public class CoinCombinationsII {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        
        int[] coins = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(coins);
        
        final int MOD = 1_000_000_007;
        long[] dp = new long[x+1];
        dp[0] = 1;
        
        for (int coin : coins) {
            for (int i = coin; i <= x; i++) {
                dp[i] = (dp[i] + dp[i - coin]) % MOD;
            }
        }
        
        pw.println(dp[x]);
        pw.flush();
    }
}
