package ConnectFour;

import java.util.Scanner;

public class Game {
    private final Board board;
    private GameState state = GameState.PLAYING;
    private Color colorToMove = Color.RED;
    private final static Scanner SCANNER = new Scanner(System.in);
    private final DetermineGameState determineGameState = new DetermineGameState();
    private final Validation validation = new Validation();

    public Game(Board board) {
        this.board = board;
    }

    public void gameLoop() {
        BoardConsoleRenderer renderer = new BoardConsoleRenderer();
        renderer.render(board);

      while (state == GameState.PLAYING) {
            printMessage();
            Coordinates inputCoordinates = getCoordinatesFromUser();
            if(!validation.isCoordinatesCorrect(inputCoordinates, board)) {
                continue;
            }

            if (colorToMove == Color.RED) {
                RedCircle redCircle = new RedCircle(Color.RED);
                board.setCircle(inputCoordinates, redCircle);
            } else {
                YellowCircle yellowCircle = new YellowCircle(Color.YELLOW);
                board.setCircle(inputCoordinates, yellowCircle);
            }
            System.out.println("-----------------------------------------------");
            renderer.render(board);
            state = determineGameState.сheckGameState(board);
            colorToMove = colorToMove.opposite();
            congratulateWinner();
        }
    }

    private void printMessage(){
        if (colorToMove == Color.RED) {
            System.out.println("Красный игрок введите координаты (строка и столбец)");
        } else {
            System.out.println("Желтый игрок введите координаты (строка и столбец)");
        }
    }

    private Coordinates getCoordinatesFromUser(){
        String input = validation.isInputValid();
        String[] parts = input.split("");
        int printRow = Integer.parseInt(parts[0]);
        int printColumn = Integer.parseInt(parts[1]);
        Coordinates inputCoordinates = new Coordinates(printRow, printColumn);
        return inputCoordinates;
    }

    private void congratulateWinner(){
        if (state == GameState.RED_WINS) {
            System.out.println("Красный игрок победил!");
        }else if(state == GameState.YELLOW_WINS){
            System.out.println("Желтый игрок победил!");
        }
    }

}
