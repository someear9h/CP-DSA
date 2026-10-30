package connectfour;

public class Main {
    public static void main(String[] args) {
        Player p1 = new Player("Samarth", DiscColor.BLUE);
        Player p2 = new Player("Vinu", DiscColor.RED);

        Game game = new Game(p1, p2, 4, 5);

        // should return false as first player plays initially
        boolean res1 = game.makeMove(p2, 3);
        System.out.println(res1);

        boolean res2 = game.makeMove(p1, 0); // valid move, should return true
        System.out.println(res2);

        game.makeMove(p2, 1);
        game.makeMove(p1, 0);
        game.makeMove(p2, 1);
        game.makeMove(p1, 0);
        game.makeMove(p2, 1);
        game.makeMove(p1, 0);
        game.makeMove(p2, 1); // game ends here player 1 wins
        System.out.println("Winner is: " + game.getWinner().name());

        game.makeMove(p1, 0); // game state WON
        System.out.println("Game Status is: " + game.getGameState());
    }
}
