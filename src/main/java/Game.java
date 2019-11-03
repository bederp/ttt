import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.Parent;

class Game {
    private GameSkin skin;
    private Board board = new Board(this);
    private WinningStrategy winningStrategy = new WinningStrategy(board);

    private Square.State currentPlayer = Square.State.CROSS;

    private ReadOnlyObjectWrapper<Square.State> winner = new ReadOnlyObjectWrapper<>(Square.State.EMPTY);

    public ReadOnlyObjectProperty<Square.State> winnerProperty() {
        return winner.getReadOnlyProperty();
    }

    private ReadOnlyBooleanWrapper draw = new ReadOnlyBooleanWrapper(false);

    public Square.State getCurrentPlayer() {
        return currentPlayer;
    }

    public ReadOnlyBooleanProperty drawProperty() {
        return draw.getReadOnlyProperty();
    }

    public boolean getDraw() {
        return draw.get();
    }

    private ReadOnlyBooleanWrapper gameOver = new ReadOnlyBooleanWrapper(false);

    public ReadOnlyBooleanProperty gameOverProperty() {
        return gameOver.getReadOnlyProperty();
    }

    public boolean isGameOver() {
        return gameOver.get();
    }

    public Game(GameManager gameManager) {
        gameOver.bind(
                winnerProperty().isNotEqualTo(Square.State.EMPTY)
                        .or(drawProperty())
        );

        skin = new GameSkin(gameManager, this);
    }

    public Board getBoard() {
        return board;
    }

    public void nextTurn() {
        if (isGameOver()) return;

        switch (currentPlayer) {
            case EMPTY:
            case NOUGHT:
                currentPlayer = Square.State.CROSS;
                break;
            case CROSS:
                currentPlayer = Square.State.NOUGHT;
                break;
        }
    }

    private void checkForWinner() {
        winner.set(winningStrategy.getWinner());
        draw.set(winningStrategy.isDraw());

        if (getDraw()) {
            currentPlayer = Square.State.EMPTY;
        }
    }

    public void boardUpdated() {
        checkForWinner();
    }

    public Parent getSkin() {
        return skin;
    }
}
