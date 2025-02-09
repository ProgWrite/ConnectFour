package ConnectFour;

import java.util.Scanner;


public class Main {
    private final static Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {

        Board board = new Board(8, 8);
        BoardConsoleRenderer renderer = new BoardConsoleRenderer();
        renderer.render(board);

        System.out.println("Введите координаты");
        int printRow = SCANNER.nextInt();
        int printColumn = SCANNER.nextInt();
        Coordinates redCoord = new Coordinates(printRow, printColumn);
        RedCircle redCircle = new RedCircle(Color.RED, redCoord);
        board.setCircle(redCoord, redCircle);
        System.out.println("-----------------------------------------------");
        renderer.render(board);


        System.out.println("Введите координаты для желтого круга");
        int printRowYellow = SCANNER.nextInt();
        int printColumnYellow = SCANNER.nextInt();
        Coordinates YellowCoord = new Coordinates(printRowYellow, printColumnYellow);
        YellowCircle yellowCircle = new YellowCircle(Color.YELLOW, redCoord);
        board.setCircle(YellowCoord, yellowCircle);
        System.out.println("-----------------------------------------------");
        renderer.render(board);

        }
    }
