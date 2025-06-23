import java.io.*;
import java.util.*;
 
public class Digit_Queries {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < q; i++) {
            long k = Long.parseLong(br.readLine());
            System.out.println(findDigit(k));
        }
    }
    
    private static char findDigit(long k) {
        long length = 1;
        long count = 9;
        long start = 1;
        
        // Determine which number group (1-digit, 2-digit, etc.) contains the k-th digit
        while (k > length * count) {
            k -= length * count;
            length++;
            count *= 10;
            start *= 10;
        }
        
        // Find the specific number that contains the k-th digit
        long number = start + (k - 1) / length;
        
        // Find the exact digit in that number
        int digitPos = (int)((k - 1) % length);
        return Long.toString(number).charAt(digitPos);
    }
}
