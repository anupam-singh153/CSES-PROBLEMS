import java.io.*;
import java.util.*;

public class RoomAllocation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        
        int[][] customers = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            customers[i][0] = Integer.parseInt(st.nextToken());
            customers[i][1] = Integer.parseInt(st.nextToken());
            customers[i][2] = i;
        }
        
        Arrays.sort(customers, (a, b) -> a[0] - b[0]);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int[] roomAllocated = new int[n];
        int roomCount = 0;
        
        for (int[] customer : customers) {
            if (!pq.isEmpty() && pq.peek()[0] < customer[0]) {
                int[] available = pq.poll();
                roomAllocated[customer[2]] = available[1];
                pq.offer(new int[]{customer[1], available[1]});
            } else {
                roomCount++;
                roomAllocated[customer[2]] = roomCount;
                pq.offer(new int[]{customer[1], roomCount});
            }
        }
        
        pw.println(roomCount);
	
	StringBuilder sb = new StringBuilder();

        for (int room : roomAllocated) {
            sb.append(room).append(" ");
        }
        pw.println(sb);

        pw.flush();
    }
}
