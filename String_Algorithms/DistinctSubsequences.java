import java.io.*;
import java.util.*;

public class DistinctSubsequences {
    static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = s.length();

        long[] dp = new long[n + 1];
        dp[0] = 1; // empty subsequence

        int[] lastOccurrence = new int[26];
        Arrays.fill(lastOccurrence, -1);

        for (int i = 1; i <= n; i++) {
            char c = s.charAt(i - 1);
            dp[i] = (2 * dp[i - 1]) % MOD;
            if (lastOccurrence[c - 'a'] != -1) {
                dp[i] = (dp[i] - dp[lastOccurrence[c - 'a'] - 1] + MOD) % MOD;
            }
            lastOccurrence[c - 'a'] = i;
        }

        System.out.println((dp[n] - 1 + MOD) % MOD); // subtract the empty subsequence
    }
}