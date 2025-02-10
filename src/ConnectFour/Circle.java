package ConnectFour;

abstract public class Circle {
    public final Color color;

    public Circle(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
