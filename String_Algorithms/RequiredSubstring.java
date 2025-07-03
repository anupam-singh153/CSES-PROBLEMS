import java.io.*;
import java.util.*;

public class RequiredSubstring {
    static final int MOD = (int)1e9 + 7;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String pat = br.readLine();
        int m = pat.length();

        int[] fail = buildKMP(pat);
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                if (dp[i][j] == 0) continue;
                for (char c = 'A'; c <= 'Z'; c++) {
                    int next = j;
                    while (next > 0 && pat.charAt(next) != c) {
                        next = fail[next - 1];
                    }
                    if (next < m && pat.charAt(next) == c) {
                        next++;
                    }

                    if (next == m) {
                        // Once we hit the pattern, remaining positions can be anything
                        dp[n][m] = (int)((dp[n][m] + (long)dp[i][j] * modPow(26, n - i - 1)) % MOD);
                    } else {
                        dp[i + 1][next] = (dp[i + 1][next] + dp[i][j]) % MOD;
                    }
                }
            }
        }

        System.out.println(dp[n][m]);
    }

    static int[] buildKMP(String pat) {
        int m = pat.length();
        int[] fail = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && pat.charAt(i) != pat.charAt(j)) {
                j = fail[j - 1];
            }
            if (pat.charAt(i) == pat.charAt(j)) {
                j++;
            }
            fail[i] = j;
        }
        return fail;
    }

    static long modPow(long a, int b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = res * a % MOD;
            a = a * a % MOD;
            b >>= 1;
        }
        return res;
    }
}
