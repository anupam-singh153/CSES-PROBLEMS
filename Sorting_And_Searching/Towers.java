import java.io.*;
import java.util.*;

public class Towers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        TreeMap<Integer, Integer> towers = new TreeMap<>();
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            int cube = Integer.parseInt(st.nextToken());
            Integer higher = towers.higherKey(cube);
            
            if (higher != null) {
                int cnt = towers.get(higher);
                if (cnt == 1) {
                    towers.remove(higher);
                } else {
                    towers.put(higher, cnt - 1);
                }
            } else {
                count++;
            }
            
            towers.put(cube, towers.getOrDefault(cube, 0) + 1);
        }
        
        pw.println(count);
        pw.flush();
    }
}
