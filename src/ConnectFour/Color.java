package ConnectFour;

public enum Color {
    RED,
    YELLOW;

    public Color opposite(){
        return this == RED ? YELLOW : RED;
    }
}
