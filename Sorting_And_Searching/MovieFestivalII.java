import java.io.*;
import java.util.*;

public class MovieFestivalII {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[][] movies = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            movies[i][0] = Integer.parseInt(st.nextToken());
            movies[i][1] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(movies, (a, b) -> a[1] - b[1]);
        
        TreeMap<Integer, Integer> endTimes = new TreeMap<>();
        endTimes.put(0, k); // Initially k people available at time 0
        
        int count = 0;
        for (int[] movie : movies) {
            Integer key = endTimes.floorKey(movie[0]);
            if (key != null) {
                count++;
                int cnt = endTimes.get(key);
                if (cnt == 1) {
                    endTimes.remove(key);
                } else {
                    endTimes.put(key, cnt - 1);
                }
                
                endTimes.put(movie[1], endTimes.getOrDefault(movie[1], 0) + 1);
            }
        }
        
        pw.println(count);
        pw.flush();
    }
}
