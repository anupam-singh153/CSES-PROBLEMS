import java.io.*;
import java.util.*;

public class MissingCoinSum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(coins);
        
        long smallestMissing = 1;
        for (int coin : coins) {
            if (coin > smallestMissing) {
                break;
            }
            smallestMissing += coin;
        }
        
        pw.println(smallestMissing);
        pw.flush();
    }
}
