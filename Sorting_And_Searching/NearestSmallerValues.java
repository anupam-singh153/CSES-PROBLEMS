import java.io.*;
import java.util.*;

public class NearestSmallerValues {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[n];
        
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            
            if (stack.isEmpty()) {
                result[i] = 0;
            } else {
                result[i] = stack.peek() + 1; // 1-based index
            }
            
            stack.push(i);
        }
 	
	StringBuilder sb = new StringBuilder();
       
        for (int num : result) {
            sb.append(num).append(" ");
        }
        
	pw.println(sb);
        pw.flush();
    }
}
