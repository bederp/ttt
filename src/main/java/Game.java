import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.Parent;

class Game {
    private GameSkin skin;
    private Board board = new Board(this);
    private WinningStrategy winningStrategy = new WinningStrategy(board);

    private ReadOnlyObjectWrapper<Square.State> currentPlayer = new ReadOnlyObjectWrapper<>(Square.State.CROSS);
    public ReadOnlyObjectProperty<Square.State> currentPlayerProperty() {
        return currentPlayer.getReadOnlyProperty();
    }
    public Square.State getCurrentPlayer() {
        return currentPlayer.get();
    }

    private ReadOnlyObjectWrapper<Square.State> winner = new ReadOnlyObjectWrapper<>(Square.State.EMPTY);
    public ReadOnlyObjectProperty<Square.State> winnerProperty() {
        return winner.getReadOnlyProperty();
    }

    private ReadOnlyBooleanWrapper drawn = new ReadOnlyBooleanWrapper(false);
    public ReadOnlyBooleanProperty drawnProperty() {
        return drawn.getReadOnlyProperty();
    }
    public boolean isDrawn() {
        return drawn.get();
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
                        .or(drawnProperty())
        );

        skin = new GameSkin(gameManager, this);
    }

    public Board getBoard() {
        return board;
    }

    public void nextTurn() {
        if (isGameOver()) return;

        switch (currentPlayer.get()) {
            case EMPTY:
            case NOUGHT: currentPlayer.set(Square.State.CROSS);  break;
            case CROSS:  currentPlayer.set(Square.State.NOUGHT); break;
        }
    }

    private void checkForWinner() {
        winner.set(winningStrategy.getWinner());
        drawn.set(winningStrategy.isDrawn());

        if (isDrawn()) {
            currentPlayer.set(Square.State.EMPTY);
        }
    }

    public void boardUpdated() {
        checkForWinner();
    }

    public Parent getSkin() {
        return skin;
    }
}
