package ConnectFour;

public class Main {
    public static void main(String[] args) {
            Board board = new Board(9,9);
            BoardConsoleRenderer renderer = new BoardConsoleRenderer();
            renderer.render(board);
        }
    }
