import java.util.*;

public class SurroundedRegions {
    private static void solve(char[][] board) {
        int m = board.length, n = board[0].length;
        Queue<int[]> q = new LinkedList<>();

        // go through rows and push the 1st and last column cell
        for(int i = 0; i < m; i++) {
            if(board[i][0] == 'O') q.add(new int[] {i, 0});
            if(board[i][n - 1] == 'O') q.add(new int[] {i, n - 1});
        }

        // go through columns and add 1st and last row cell
        for(int j = 0; j < n; j++) {
            if(board[0][j] == 'O') q.add(new int[] {0, j});
            if(board[m - 1][j] == 'O') q.add(new int[] {m - 1, j});
        }

        // now queue contains all safe 'O' and we bfs from these safe zeros
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while(!q.isEmpty()) {
            int[] cell = q.poll();
            int row = cell[0], col = cell[1];

            if(board[row][col] != 'O') continue;
            
            board[row][col] = 'S';

            // explore all directions
            for(int[] dir : directions) {
                int nRow = row + dir[0];
                int nCol = col + dir[1];

                // if new row and new col is valid and the cell is 'O' then mark it as 'S'
                if(nRow < m && nRow >= 0 && nCol < n && nCol >= 0 && board[nRow][nCol] == 'O') {
                    q.add(new int[] {nRow, nCol});
                }
            }
        }

        // loop through and make 'S' 'O' and 'O' 'X'
        for(int i = 0;i < m; i++) {
            for(int j = 0; j < n; j++) {
            
                if(board[i][j] == 'O')  {
                    board[i][j] ='X';
                } else if(board[i][j] == 'S')  {
                    board[i][j] = 'O';
                }
            }
        }
    }   

    public static void main(String[] args) {
        char[][] board = {
            {'X','X','X','X'},
            {'X','O','O','X'},
            {'X','X','O','X'},
            {'X','O','X','X'}
        };

        solve(board);

        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }
}
