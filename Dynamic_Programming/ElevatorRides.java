import java.io.*;
import java.util.*;

public class ElevatorRides {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = Integer.parseInt(st.nextToken());
        }
        
        // DP state: dp[mask] = {min_rides, min_last_weight}
        int[][] dp = new int[1 << n][2];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dp[0][0] = 1;
        dp[0][1] = 0;
        
        for (int mask = 0; mask < (1 << n); mask++) {
            int rides = dp[mask][0];
            int currentWeight = dp[mask][1];
            if (rides == Integer.MAX_VALUE) {
                continue;
            }
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) == 0) {
                    int newMask = mask | (1 << i);
                    int newRides = rides;
                    int newWeight = currentWeight + w[i];
                    if (newWeight > x) {
                        newRides++;
                        newWeight = w[i];
                    }
                    if (newRides < dp[newMask][0] || (newRides == dp[newMask][0] && newWeight < dp[newMask][1])) {
                        dp[newMask][0] = newRides;
                        dp[newMask][1] = newWeight;
                    }
                }
            }
        }
        
        System.out.println(dp[(1 << n) - 1][0]);
    }
}