import java.io.*;
import java.util.*;

public class TrafficLights {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int x = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        
        TreeSet<Integer> positions = new TreeSet<>();
        positions.add(0);
        positions.add(x);
        
        TreeMap<Integer, Integer> lengths = new TreeMap<>();
        lengths.put(x, 1);
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int p = Integer.parseInt(st.nextToken());
            int lower = positions.lower(p);
            int higher = positions.higher(p);
            
            int segment = higher - lower;
            int count = lengths.get(segment);
            if (count == 1) {
                lengths.remove(segment);
            } else {
                lengths.put(segment, count - 1);
            }
            
            lengths.put(p - lower, lengths.getOrDefault(p - lower, 0) + 1);
            lengths.put(higher - p, lengths.getOrDefault(higher - p, 0) + 1);
            positions.add(p);
            
            pw.print(lengths.lastKey() + " ");
        }
        
        pw.flush();
    }
}
