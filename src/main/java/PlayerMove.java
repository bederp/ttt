import java.util.Arrays;

public class PlayerMove {
    private int x;
    private int y;

    public void parseString(String line) {
        System.out.println(line);
        String[] table = line.split(" ");
        x = Integer.parseInt(table[0]);
        y = Integer.parseInt(table[1]);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}