package ConnectFour;

import java.awt.*;

abstract public class Circle {
    public final Color color;
    public Coordinates coordinates;

    public Circle(Color color, Coordinates coordinates) {
        this.color = color;
        this.coordinates = coordinates;
    }
}
