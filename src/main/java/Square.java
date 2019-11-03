import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.Node;

class Square {
    enum State { EMPTY, NOUGHT, CROSS }

    private final SquareSkin skin;

    private State state = State.EMPTY;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        skin.setState(state);
    }

    private final Game game;

    public Square(Game game) {
        this.game = game;

        skin = new SquareSkin(this);
    }

    public void pressed() {
        if (!game.isGameOver() && state == State.EMPTY) {
            setState(game.getCurrentPlayer());
            game.boardUpdated();
            game.nextTurn();
        }
    }

    public Node getSkin() {
        return skin;
    }
}
