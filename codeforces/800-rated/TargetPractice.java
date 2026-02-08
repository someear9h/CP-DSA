/**
someear1h 
*/
 
import java.io.*;

public class TargetPractice {
    static int[][] score = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
		{1, 2, 2, 2, 2, 2, 2, 2, 2, 1},
		{1, 2, 3, 3, 3, 3, 3, 3, 2, 1},
		{1, 2, 3, 4, 4, 4, 4, 3, 2, 1},
		{1, 2, 3, 4, 5, 5, 4, 3, 2, 1},
		{1, 2, 3, 4, 5, 5, 4, 3, 2, 1},
		{1, 2, 3, 4, 4, 4, 4, 3, 2, 1},
		{1, 2, 3, 3, 3, 3, 3, 3, 2, 1},
		{1, 2, 2, 2, 2, 2, 2, 2, 2, 1},
		{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t-- > 0) {
                int n = 10;
                char[][] arr = new char[n][n];
                
                for(int i = 0; i < n; i++) {
                    String line = br.readLine();

                    arr[i] = line.toCharArray();
                }

                int total = 0;
                for(int i = 0; i < n; i++) {
                    for(int j = 0; j < n; j++) {
                        if(arr[i][j] == 'X') {
                            total += score[i][j];
                        }
                    }
                }

                out.println(total);
            }
            
        }
    }
}