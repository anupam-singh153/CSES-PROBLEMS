import java.io.*;
import java.util.*;

public class Projects {
    static class Project implements Comparable<Project> {
        int start, end, reward;
        Project(int start, int end, int reward) {
            this.start = start;
            this.end = end;
            this.reward = reward;
        }
        public int compareTo(Project other) {
            return Integer.compare(this.end, other.end);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        
        Project[] projects = new Project[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            projects[i] = new Project(a, b, p);
        }
        
        Arrays.sort(projects);
        
        long[] dp = new long[n];
        dp[0] = projects[0].reward;
        
        for (int i = 1; i < n; i++) {
            int l = -1, r = i - 1;
            while (l < r) {
                int mid = (l + r + 1) / 2;
                if (projects[mid].end < projects[i].start) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            
            long include = projects[i].reward;
            if (l != -1) include += dp[l];
            
            dp[i] = Math.max(dp[i-1], include);
        }
        
        pw.println(dp[n-1]);
        pw.flush();
    }
}
