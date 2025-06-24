import java.io.*;
import java.util.*;

public class Concert_Tickets {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        TreeMap<Integer, Integer> tickets = new TreeMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int price = Integer.parseInt(st.nextToken());
            tickets.put(price, tickets.getOrDefault(price, 0) + 1);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int maxPrice = Integer.parseInt(st.nextToken());
            // Find the largest price <= maxPrice
            Map.Entry<Integer, Integer> entry = tickets.floorEntry(maxPrice);
            if (entry != null) {
                pw.println(entry.getKey());
                // Update or remove the ticket count
                if (entry.getValue() == 1) {
                    tickets.remove(entry.getKey());
                } else {
                    tickets.put(entry.getKey(), entry.getValue() - 1);
                }
            } else {
                pw.println(-1);
            }
        }

        pw.flush();
        pw.close();
    }
}
