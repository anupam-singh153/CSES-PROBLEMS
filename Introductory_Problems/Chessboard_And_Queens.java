import java.io.*;
import java.util.*;
 
public class Chessboard_And_Queens {
    
    private static boolean isSafe(char[][] grid, int row, int col){
        
        int r, c;
        
        for(r = 0; r < row; ++r){
            
            if(grid[r][col] == 'Q')
                return false;
        }
      
        //North West
        r = row;
        c = col;
        
        while(r >= 0 && c >= 0){
            
            if(grid[r][c] == 'Q')
                return false;
                
            r--;
            c--;
        }
        
        //North East
        r = row;
        c = col;
        
        while(r >= 0 && c < 8){
            
            if(grid[r][c] == 'Q')
                return false;
                
            r--;
            c++;
        }
       
        return true;
    }
    private static long solve(char[][] grid, int r){
        
        if(r == 8)
            return 1;
        
        long ans = 0;
        
        for(int c = 0; c < 8; ++c){
            
            if(grid[r][c] == '.' && isSafe(grid, r, c)){
                grid[r][c] = 'Q';
                
                ans += solve(grid, r+1);
                
                grid[r][c] = '.';
            }
        }
        
        return ans;
    }
    
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
        char[][] grid = new char[8][8];
        
        for(int i = 0; i < 8; ++i)
            grid[i] = br.readLine().toCharArray();
            
        long ans = solve(grid, 0);
        System.out.println(ans);
    }
}
