import java.io.*;
import java.util.*;
 
public class Ferris_Wheel {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bf.readLine().split(" ");
        
        int n = Integer.parseInt(s[0]);
        long x = Long.parseLong(s[1]);
        
        StringTokenizer st = new StringTokenizer(bf.readLine());
        PrintWriter out = new PrintWriter(System.out);
        
        long[] arr = new long[n];
        
        for (int i = 0; i < n; ++i)
            arr[i] = Long.parseLong(st.nextToken());
           
        Arrays.sort(arr);
        int i = 0, j = n - 1, count = 0;
        
        while (i <= j) {
            if (arr[i] + arr[j] <= x) {
                i++;
                j--;
                count++;
            } else {
                j--;
                count++;
            }
        }
        
        out.println(count);
        out.flush();
    }
}
