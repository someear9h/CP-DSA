package connectfour;

public class Board {

    // for making board of different sizes we havent given a constant value to it
    // or else rows and cols would be 6 and 7 resp.
    // if we want to keep it constant then it works as well
    private final int rows;
    private final int cols;
    private final DiscColor[][] grid;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        grid = new DiscColor[rows][cols];
    }

    // places disc onto chosen column
    public int placeDisc(int column, DiscColor color) {
        if(!canPlace(column)) {
            return -1;
        }

        // go through rows from last row to first in the same column.
        // when a cell in the grid is not colored, color it and return row
        // else return -1
        for(int row = rows - 1; row >= 0; row--) {
            if(grid[row][column] == null) {
                grid[row][column] = color;
                return row;
            }
        }
        return -1;
    }

    public boolean checkWin(int row, int column, DiscColor color) {
        if(!inBounds(row, column) || grid[row][column] != color) {
            return false;
        }

        // check 4 directions and reverse them to check other 4 directions
        int[][] directions = {
                {1, 0}, // vertical
                {0, 1}, // horizontal
                {1, 1}, // diagonal (top, right)
                {-1, 1} // diagonal (bottom, right)
        };

        for(int[] dir : directions) {
            int count = 1; // the count for disc itself
            count += countInDirection(row, column, dir[0], dir[1], color);
            count += countInDirection(row, column, -dir[0], -dir[1], color);

            if(count >= 4) {
                return true;
            }
        }
        return false;
    }

    // checks if the grid is full
    // go through every column and check the top most row. if its null then
    // we still have space and players can make move. the grid is full otherwise
    public boolean isFull() {
        for(int col = 0; col < cols; col++) {
            if(grid[0][col] == null) {
                return false;
            }
        }
        return true;
    }

    // takes a direction and keeps on walking unless it is out of bounds
    // or encounter a different color
    private int countInDirection(int row, int column, int dr, int dc, DiscColor color) {
        int count = 0;
        int r = row + dr;
        int c = column + dc;

        while(inBounds(r, c) && grid[r][c] == color) {
            count++;
            r += dr;
            c += dc;
        }

        return count;
    }

    // this below function checks if the disc can be placed in the wanted column.
    // it does it by checking the top most row of the column, if its empty (null)
    // means there is space in that column, return false otherwise.

    // Why check the top row only? Because of gravity: discs always fall to the
    // lowest empty cell. If the top cell of a column is empty, the column has at
    // least one free space. If the top cell is filled, the column is completely
    // full(since filling goes bottom-up).

    // edge case: return false when column is out of bounds
    private boolean canPlace(int column) {
        if(column < 0 || column >= cols) return false;

        return grid[0][column] == null;
    }

    private boolean inBounds(int row, int col) {
        return row < rows && row >= 0 && col < cols && col >= 0;
    }
}
