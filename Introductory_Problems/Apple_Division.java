import java.io.*;
import java.util.*;
 
public class Apple_Division {
   
    private static long solve(long[] arr, int i, long s1, long s2){
        
        if(i >= arr.length)
            return Math.abs(s1 - s2);
      
        //Group1 
        long include = solve(arr, i+1,s1 + arr[i], s2);
        
        //Group2
        long exclude = solve(arr, i+1, s1, s2 + arr[i]);
        
        return Math.min(include, exclude);
    }
    
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
      
        for(int i = 0; i < n; ++i)
            arr[i] = Long.parseLong(st.nextToken());
            
        long ans = solve(arr,0,0,0);
        System.out.println(ans);
    }
}
