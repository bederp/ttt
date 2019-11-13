import javafx.scene.Node;

class Square {
    enum State { EMPTY, NOUGHT, CROSS }

    private final SquareSkin skin;

    private State state = State.EMPTY;

    private int x;
    private int y;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        skin.setState(state);
    }

    private final Game game;

    public Square(Game game, int x, int y) {
        this.game = game;
        this.x = x;
        this.y = y;

        skin = new SquareSkin(this);
    }

    public void pressed() {
        if (!game.isGameOver() && state == State.EMPTY) {
            String toSend = (this.x + " " + this.y + " " + game.getCurrentPlayer());
            //tu wysylamy toSend
            setState(game.getCurrentPlayer());
            game.updateState();
        }
    }

    public void pressed(String z) {
        if (!game.isGameOver() && state == State.EMPTY) {
            setState(game.getCurrentPlayer());
        }
    }

    public Node getSkin() {
        return skin;
    }
}
