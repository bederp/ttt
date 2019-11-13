import javafx.scene.Node;

class Board {
    private final BoardSkin skin;

    private final Square[][] squares = new Square[3][3];

    public Board(Game game) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                squares[i][j] = new Square(game, i ,j);
            }
        }

        skin = new BoardSkin(this);
    }

    public Square getSquare(int i, int j) {
        return squares[i][j];
    }

    public Node getSkin() {
        return skin;
    }
}
