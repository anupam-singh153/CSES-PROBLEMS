import java.io.*;
import java.util.*;

public class JosephusProblemI {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        
        TreeSet<Integer> alive = new TreeSet<>();
        for (int i = 1; i <= n; i++) {
            alive.add(i);
        }
        
        Iterator<Integer> it = alive.iterator();
        while (!alive.isEmpty()) {
            if (!it.hasNext()) {
                it = alive.iterator();
            }
            it.next();
            if (!it.hasNext()) {
                it = alive.iterator();
            }
            pw.print(it.next() + " ");
            it.remove();
        }
        
        pw.flush();
    }
}
