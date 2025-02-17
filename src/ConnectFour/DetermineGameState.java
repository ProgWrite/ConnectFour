package ConnectFour;

public class DetermineGameState {

    public GameState сheckGameState(Board board) {
        if(checkVerticalCells(board) != GameState.PLAYING){
            return checkVerticalCells(board);
        }
        else if(checkHorizontalCells(board) != GameState.PLAYING){
            return checkHorizontalCells(board);
        }
        else if(checkDiagonalLeftToRightCells(board) != GameState.PLAYING){
            return checkDiagonalLeftToRightCells(board);
        }
        else if(checkDiagonalRightToLeftCells(board) != GameState.PLAYING){
            return checkDiagonalRightToLeftCells(board);
        }
        return GameState.PLAYING;
    }

    private GameState checkVerticalCells(Board board) {
        for (int row = 0; row < board.getHeight() - 3; row++) {
            for (int column = 0; column < board.getWidth(); column++) {
                if(checkWin(row,column, 1, 0, board)){
                    return board.getCircle(new Coordinates(row,column)).getColor() == Color.RED? GameState.RED_WINS : GameState.YELLOW_WINS;
                }
            }
        }
        return GameState.PLAYING;
    }

    private GameState checkHorizontalCells(Board board) {
        for (int row = 0; row < board.getHeight(); row++) {
            for (int column = 0; column < board.getWidth() - 3; column++) {
                if(checkWin(row,column, 0, 1, board)){
                    return board.getCircle(new Coordinates(row,column)).getColor() == Color.RED? GameState.RED_WINS : GameState.YELLOW_WINS;
                }
            }
        }
        return GameState.PLAYING;
    }

    private GameState checkDiagonalLeftToRightCells(Board board) {
        for (int row = 0; row < board.getHeight() - 3; row++) {
            for (int column = 0; column < board.getWidth() - 3; column++) {
                if(checkWin(row,column, 1, 1, board)){
                    return board.getCircle(new Coordinates(row,column)).getColor() == Color.RED? GameState.RED_WINS : GameState.YELLOW_WINS;
                }
            }
        }
        return GameState.PLAYING;
    }

    private GameState checkDiagonalRightToLeftCells(Board board) {
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
}
