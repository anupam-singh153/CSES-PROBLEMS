import java.io.*;
import java.util.*;

public class SumOfThreeValues {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        
        int[][] nums = new int[n][2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i][0] = Integer.parseInt(st.nextToken());
            nums[i][1] = i + 1;
        }
        
        Arrays.sort(nums, (a, b) -> a[0] - b[0]);
        
        for (int i = 0; i < n; i++) {
            int target = x - nums[i][0];
            int left = i + 1, right = n - 1;
            
            while (left < right) {
                int sum = nums[left][0] + nums[right][0];
                if (sum == target) {
                    pw.println(nums[i][1] + " " + nums[left][1] + " " + nums[right][1]);
                    pw.flush();
                    return;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        pw.println("IMPOSSIBLE");
        pw.flush();
    }
}
