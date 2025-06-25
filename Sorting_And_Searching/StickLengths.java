import java.io.*;
import java.util.*;

public class StickLengths {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] sticks = new int[n];
        for (int i = 0; i < n; i++) {
            sticks[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(sticks);
        int median = sticks[n / 2];
        
        long cost = 0;
        for (int stick : sticks) {
            cost += Math.abs(stick - median);
        }
        
        pw.println(cost);
        pw.flush();
    }
}
