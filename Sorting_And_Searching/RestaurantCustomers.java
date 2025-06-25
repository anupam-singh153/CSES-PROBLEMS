import java.io.*;
import java.util.*;

public class RestaurantCustomers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        
        List<int[]> events = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            events.add(new int[]{a, 1});
            events.add(new int[]{b, -1});
        }
        
        events.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        
        int max = 0, current = 0;
        for (int[] event : events) {
            current += event[1];
            max = Math.max(max, current);
        }
        
        pw.println(max);
        pw.flush();
    }
}
