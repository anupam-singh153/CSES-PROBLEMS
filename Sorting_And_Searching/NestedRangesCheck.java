import java.io.*;
import java.util.*;

public class NestedRangesCheck {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        
        int[][] ranges = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            ranges[i][0] = Integer.parseInt(st.nextToken());
            ranges[i][1] = Integer.parseInt(st.nextToken());
            ranges[i][2] = i;
        }
        
        // Sort by start in ascending order, end in descending order
        Arrays.sort(ranges, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        
        int[] contains = new int[n];
        int[] contained = new int[n];
        int maxEnd = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i++) {
            if (ranges[i][1] <= maxEnd) {
                contained[ranges[i][2]] = 1;
            }
            maxEnd = Math.max(maxEnd, ranges[i][1]);
        }
        
        int minEnd = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (ranges[i][1] >= minEnd) {
                contains[ranges[i][2]] = 1;
            }
            minEnd = Math.min(minEnd, ranges[i][1]);
        }
        
	StringBuilder sb = new StringBuilder();

        for (int num : contains) {
            sb.append(num).append(" ");
        }
        pw.println(sb);

	sb = new StringBuilder();
        
        for (int num : contained) {
            sb.append(num).append(" ");
        }
        pw.println(sb);
	
        pw.flush();
    }
}
