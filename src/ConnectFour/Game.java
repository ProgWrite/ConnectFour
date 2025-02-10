package ConnectFour;

import java.util.Scanner;

public class Game {
    private Board board;
    private GameState state = GameState.PLAYING;
    private Color colorToMove = Color.RED;
    private final static Scanner SCANNER = new Scanner(System.in);

    public Game(Board board) {
        this.board = board;
    }

    public void gameLoop() {
        BoardConsoleRenderer renderer = new BoardConsoleRenderer();
        renderer.render(board);

        while (state == GameState.PLAYING) {
            printMessage();
            if (colorToMove == Color.RED) {
                RedCircle redCircle = new RedCircle(Color.RED);
                board.setCircle(getCoordinatesFromUser(), redCircle);
            } else {
                YellowCircle yellowCircle = new YellowCircle(Color.YELLOW);
                board.setCircle(getCoordinatesFromUser(), yellowCircle);
            }
            System.out.println("-----------------------------------------------");
            renderer.render(board);
            state = determineGameState();
            colorToMove = colorToMove.opposite();
            congratulateWinner();
        }
    }

    private void printMessage(){
        if (colorToMove == Color.RED) {
            System.out.println("Красный игрок введите координаты (строка и столбец через пробел)");
        } else {
            System.out.println("Желтый игрок введите координаты (строка и столбец через пробел)");
        }
    }

    private Coordinates getCoordinatesFromUser(){
        String input = SCANNER.nextLine();
        String[] parts = input.split(" ");
        int printRow = Integer.parseInt(parts[0]);
        int printColumn = Integer.parseInt(parts[1]);
        Coordinates inputCoordinates = new Coordinates(printRow, printColumn);
        return inputCoordinates;
    }

    private GameState determineGameState() {
       if(checkVerticalCells() != GameState.PLAYING){
           return checkVerticalCells();
       }
       else if(checkHorizontalCells() != GameState.PLAYING){
           return checkHorizontalCells();
       }
       else if(checkDiagonalLeftToRightCells() != GameState.PLAYING){
           return checkDiagonalLeftToRightCells();
       }
       else if(checkDiagonalRightToLeftCells() != GameState.PLAYING){
           return checkDiagonalRightToLeftCells();
       }
       return GameState.PLAYING;
    }

    private GameState checkVerticalCells() {
        for (int row = 0; row < board.getHeight() - 3; row++) {
            for (int column = 0; column < board.getWidth(); column++) {
                if(checkWin(row,column, 1, 0, board)){
                    return board.getCircle(new Coordinates(row,column)).getColor() == Color.RED? GameState.RED_WINS : GameState.YELLOW_WINS;
                }
            }
        }
        return GameState.PLAYING;
    }

    private GameState checkHorizontalCells() {
        for (int row = 0; row < board.getHeight(); row++) {
            for (int column = 0; column < board.getWidth() - 3; column++) {
                if(checkWin(row,column, 0, 1, board)){
                    return board.getCircle(new Coordinates(row,column)).getColor() == Color.RED? GameState.RED_WINS : GameState.YELLOW_WINS;
                }
            }
        }
        return GameState.PLAYING;
    }

    private GameState checkDiagonalLeftToRightCells() {
        for (int row = 0; row < board.getHeight() - 3; row++) {
            for (int column = 0; column < board.getWidth() - 3; column++) {
                if(checkWin(row,column, 1, 1, board)){
                    return board.getCircle(new Coordinates(row,column)).getColor() == Color.RED? GameState.RED_WINS : GameState.YELLOW_WINS;
                }
            }
        }
        return GameState.PLAYING;
    }

    private GameState checkDiagonalRightToLeftCells() {
        for (int row = 0; row < board.getHeight() - 3; row++) {
            for (int column = 0; column < board.getWidth(); column++) {
                if(checkWin(row,column, 1, -1, board)){
                    return board.getCircle(new Coordinates(row,column)).getColor() == Color.RED? GameState.RED_WINS : GameState.YELLOW_WINS;
                }
            }
        }
        return GameState.PLAYING;
    }

    private boolean checkWin(int startRow, int startColumn, int deltaRow, int deltaColumn, Board board) {
        Circle firstCircle = board.getCircle(new Coordinates(startRow, startColumn));
        if(firstCircle == null){
            return false;
        }
        for (int i = 1; i < 4 ; i++) {
            Coordinates nextCoordinates = new Coordinates(startRow + i * deltaRow, startColumn + i * deltaColumn);
            Circle nextCircle = board.getCircle(nextCoordinates);
            if(nextCircle == null || !nextCircle.getColor().equals(firstCircle.getColor())){
                return false;
            }
        }
        return true;
    }

    private void congratulateWinner(){
        if (state == GameState.RED_WINS) {
            System.out.println("Красные выиграли");
        }else if(state == GameState.YELLOW_WINS){
            System.out.println("Желтые выиграли");
        }
    }


}
