import java.io.*;
import java.util.*;

public class CollectingNumbersII {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[] nums = new int[n + 1];
        int[] pos = new int[n + 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            pos[nums[i]] = i;
        }
        
        int rounds = 1;
        for (int i = 2; i <= n; i++) {
            if (pos[i] < pos[i - 1]) {
                rounds++;
            }
        }
        
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            int numA = nums[a];
            int numB = nums[b];
            
            // Check adjacent numbers
            if (numA > 1 && pos[numA] < pos[numA - 1]) rounds--;
            if (numA < n && pos[numA + 1] < pos[numA]) rounds--;
            if (numB > 1 && numB - 1 != numA && pos[numB] < pos[numB - 1]) rounds--;
            if (numB < n && numB + 1 != numA && pos[numB + 1] < pos[numB]) rounds--;
            
            // Swap positions
            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
            pos[nums[a]] = a;
            pos[nums[b]] = b;
            
            // Check adjacent numbers again
            if (numA > 1 && pos[numA] < pos[numA - 1]) rounds++;
            if (numA < n && pos[numA + 1] < pos[numA]) rounds++;
            if (numB > 1 && numB - 1 != numA && pos[numB] < pos[numB - 1]) rounds++;
            if (numB < n && numB + 1 != numA && pos[numB + 1] < pos[numB]) rounds++;
            
            pw.println(rounds);
        }
        
        pw.flush();
    }
}
