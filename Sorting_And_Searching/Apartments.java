import java.io.*;
import java.util.*;
 
public class Apartments {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[] applicants = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            applicants[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] apartments = new int[m];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++) {
            apartments[i] = Integer.parseInt(st.nextToken());
        }
        
        out.println(maxMatches(applicants, apartments, k));
	out.flush();
    }
    
    private static int maxMatches(int[] applicants, int[] apartments, int k) {
        Arrays.sort(applicants);
        Arrays.sort(apartments);
        
        int matches = 0;
        int i = 0, j = 0;
        
        while (i < applicants.length && j < apartments.length) {
            int desired = applicants[i];
            int current = apartments[j];
            
            if (current >= desired - k && current <= desired + k) {
                // Found a match
                matches++;
                i++;
                j++;
            } else if (current < desired - k) {
                // Apartment too small, try next apartment
                j++;
            } else {
                // Apartment too big, try next applicant
                i++;
            }
        }
        
        return matches;
    }
}
