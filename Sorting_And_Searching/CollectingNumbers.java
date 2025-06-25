import java.io.*;
import java.util.*;

public class CollectingNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] nums = new int[n];
        int[] pos = new int[n + 2];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            pos[nums[i]] = i;
        }
        
        int rounds = 1;
        for (int i = 2; i <= n; i++) {
            if (pos[i] < pos[i - 1]) {
                rounds++;
            }
        }
        
        pw.println(rounds);
        pw.flush();
    }
}
