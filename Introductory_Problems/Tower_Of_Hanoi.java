import java.io.*;
import java.util.*;
 
public class Tower_Of_Hanoi {
    
    private static List<int []> result;
    
    private static void towerOfHanoi(int n, int s, int h, int d){
        
        if(n == 0)
            return;
     
        towerOfHanoi(n-1, s, d, h);
        
        result.add(new int[]{s,d});
        
        towerOfHanoi(n-1, h, s, d);
    }
    
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        result = new ArrayList<>();
        towerOfHanoi(n,1,2,3);
        
        System.out.println(result.size());
        for(int[] pair : result){
            System.out.println(pair[0]+" "+pair[1]);
        }
    }
}
