import java.io.*;
import java.util.*;

public class IncreasingSubsequence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        List<Integer> lis = new ArrayList<>();
        
        for (int num : arr) {
            int idx = Collections.binarySearch(lis, num);
            if (idx < 0) idx = -(idx + 1);
            
            if (idx == lis.size()) {
                lis.add(num);
            } else {
                lis.set(idx, num);
            }
        }
        
        pw.println(lis.size());
        pw.flush();
    }
}
