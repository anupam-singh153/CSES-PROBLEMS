import java.io.*;
import java.util.*;

public class ArrayDivision {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        long left = 0, right = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            right += arr[i];
            left = Math.max(left, arr[i]);
        }
        
        while (left < right) {
            long mid = (left + right) / 2;
            int subarrays = 1;
            long currentSum = 0;
            
            for (int num : arr) {
                if (currentSum + num > mid) {
                    subarrays++;
                    currentSum = 0;
                }
                currentSum += num;
            }
            
            if (subarrays <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        pw.println(left);
        pw.flush();
    }
}
