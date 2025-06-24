import java.io.*;
import java.util.*;
 
public class Minimizing_Coins {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        
        int[] coins = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }
        
        System.out.println(minCoins(coins, x));
    }
    
    private static int minCoins(int[] coins, int x) {
        int[] dp = new int[x + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1); // Avoid overflow
        dp[0] = 0; // Base case: 0 coins needed for sum 0
        
        for (int i = 1; i <= x; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return dp[x] == Integer.MAX_VALUE - 1 ? -1 : dp[x];
    }
}
