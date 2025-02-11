package ConnectFour;


public class Main {

    public static void main(String[] args) {
        Board board = new Board(7, 7);
        Game game = new Game(board);
        game.gameLoop();
    }
}
