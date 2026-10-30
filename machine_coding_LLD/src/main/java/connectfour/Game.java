package connectfour;

import lombok.Getter;

enum GameState {
    IN_PROGRESS, DRAW, WON
}

public class Game {
    private final Board board;

    private final Player player1;
    private final Player player2;

    // not final fields because they can change
    @Getter private GameState gameState;
    @Getter private Player currentPlayer;
    @Getter private Player winner;

    public Game(Player p1, Player p2, int rows, int cols) {
        this.board = new Board(rows, cols);
        this.gameState = GameState.IN_PROGRESS;
        this.player1 = p1;
        this.player2 = p2;
        this.currentPlayer = p1; // first player 1 will make move
        this.winner = null;
    }

    public boolean makeMove(Player player, int column) {
        if(gameState != GameState.IN_PROGRESS) {
            return false;
        }

        // use .equals() instead of ==
        if(player.equals(currentPlayer)) {
            return false;
        }

        int row = board.placeDisc(column, player.color());
        if(row == -1) {
            return false;
        }

        if(board.checkWin(row, column, player.color())) {
            this.gameState = GameState.WON;
            this.winner = player;
        }
        else if(board.isFull()) {
            this.gameState = GameState.DRAW;
        }
        else {
            currentPlayer = (currentPlayer == player1 ? player2 : player1);
        }
        return true;
    }

}
