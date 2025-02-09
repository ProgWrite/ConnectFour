package ConnectFour;

public class BoardConsoleRenderer {

    public void render(Board board) {

        for (int row = 0; row < board.getWidth() ; row++) {
            StringBuilder line = new StringBuilder();
            for (int columh = 0; columh < board.getHeight() ; columh++) {
                line.append(renderEmptyCell());
            }
            System.out.println(line);
        }
    }

    private String renderEmptyCell(){
        return ("  .  ");
    }

    private String selectPuctureForCircle(Circle circle) {
        switch (circle.getClass().getSimpleName()) {
            case "RedCircle":
                return "\uD83D\uDD34";
            case "YellowCircle":
                return "\uD83D\uDFE1";
            default:
                return "?";
        }
    }

    private String getCircleSquare(Circle circle) {
         return(" " + selectPuctureForCircle(circle) + " ");
    }
}
