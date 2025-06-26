import java.io.*;
import java.util.*;

public class FactoryMachines {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        
        int[] machines = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            machines[i] = Integer.parseInt(st.nextToken());
        }
        
        long left = 1;
        long right = (long)1e18;
        long answer = right;
        
        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = 0;
            
            for (int machine : machines) {
                sum += mid / machine;
                if (sum >= t) break;
            }
            
            if (sum >= t) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        pw.println(answer);
        pw.flush();
    }
}
