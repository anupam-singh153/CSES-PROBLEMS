import java.io.*;
import java.util.*;

public class JosephusProblemII {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        TreeSet<Integer> alive = new TreeSet<>();
        for (int i = 1; i <= n; i++) {
            alive.add(i);
        }
        
        int pos = 0;
        while (!alive.isEmpty()) {
            pos = (pos + k) % alive.size();
            Integer num = alive.higher(0); // Get first element
            for (int i = 0; i < pos; i++) {
                num = alive.higher(num);
            }
            pw.print(num + " ");
            alive.remove(num);
            pos = alive.headSet(num).size();
        }
        
        pw.flush();
    }
}
