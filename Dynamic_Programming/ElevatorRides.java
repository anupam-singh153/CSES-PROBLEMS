import java.io.*;
import java.util.*;

public class ElevatorRides {
    static class Subset {
        int sum, mask;
        Subset(int sum, int mask) {
            this.sum = sum;
            this.mask = mask;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        
        int[] weights = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }
        
        int half = n / 2;
        List<Subset> firstHalf = new ArrayList<>();
        
        // Generate all subsets of first half
        for (int mask = 0; mask < (1 << half); mask++) {
            int sum = 0;
            for (int i = 0; i < half; i++) {
                if ((mask & (1 << i)) != 0) {
                    sum += weights[i];
                }
            }
            firstHalf.add(new Subset(sum, mask));
        }
        
        // Generate all subsets of second half
        int secondHalf = n - half;
        int minRides = Integer.MAX_VALUE;
        
        for (int mask = 0; mask < (1 << secondHalf); mask++) {
            int sum = 0;
            for (int i = 0; i < secondHalf; i++) {
                if ((mask & (1 << i)) != 0) {
                    sum += weights[half + i];
                }
            }
            
            // Find best complement in first half
            if (sum <= x) {
                int remaining = x - sum;
                int best = 0;
                
                for (Subset s : firstHalf) {
                    if (s.sum <= remaining) {
                        best = Math.max(best, s.sum);
                    }
                }
                
                int rides = Integer.bitCount(mask) + Integer.bitCount(best);
                minRides = Math.min(minRides, rides);
            }
        }
        
        pw.println(minRides);
        pw.flush();
    }
}
