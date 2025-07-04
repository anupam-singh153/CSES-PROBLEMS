import java.io.*;
import java.util.*;
 
public class Distinct_Numbers {
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        Set<Integer> set = new HashSet<>();
        
        for(int i = 0; i < n; ++i)
            set.add(Integer.parseInt(st.nextToken()));
        
        System.out.println(set.size());
    }
}
