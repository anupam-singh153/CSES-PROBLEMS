import java.util.*;
import java.io.*;

public class Dice_Combinations {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(bf.readLine());
        long[] dp = new long[n + 1];
        
        // Base case
        dp[0] = 1;
        
        // Fill the dp table in bottom-up manner
        for (int target = 1; target <= n; target++) {
            long ways = 0;
            for (int dice = 1; dice <= 6; dice++) {
                if (target >= dice) {
                    ways = (ways + dp[target - dice]) % 1000000007;
                }
            }
            dp[target] = ways;
        }
        
        out.println(dp[n]);
        out.flush();
    }
}