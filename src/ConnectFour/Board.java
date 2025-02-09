package ConnectFour;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Coordinates, Circle> circles = new HashMap<>();
    private final int width;
    private final int height;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setCircle(Coordinates coordinates, Circle circle) {
        circles.put(coordinates, circle);
    }

    public Circle getCircle(Coordinates coordinates) {
        return circles.get(coordinates);
    }

    public boolean isCellEmpty(Coordinates coordinates) {
        return !circles.containsKey(coordinates);
    }

}
