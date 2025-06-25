import java.io.*;
import java.util.*;

public class MovieFestival {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        
        int[][] movies = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            movies[i][0] = Integer.parseInt(st.nextToken());
            movies[i][1] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(movies, (a, b) -> a[1] - b[1]);
        
        int count = 0, lastEnd = 0;
        for (int[] movie : movies) {
            if (movie[0] >= lastEnd) {
                count++;
                lastEnd = movie[1];
            }
        }
        
        pw.println(count);
        pw.flush();
    }
}
