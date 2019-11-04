import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

class PlayAgainChoice extends HBox {
    PlayAgainChoice(final GameManager gameManager, final Game game) {
        setVisible(false);
        getStyleClass().add("game-controls");

        Label playAgainLabel = new Label("Play Again?");
        playAgainLabel.getStyleClass().add("info");

        Button playAgainButton = new Button("Yes");
        playAgainButton.getStyleClass().add("play-again");
        playAgainButton.setDefaultButton(true);
        playAgainButton.setOnAction(actionEvent -> gameManager.newGame());

        Button exitButton = new Button("No");
        exitButton.getStyleClass().add("exit");
        exitButton.setCancelButton(true);
        exitButton.setOnAction(actionEvent -> gameManager.quit());

        getChildren().setAll(
                playAgainLabel,
                playAgainButton,
                exitButton
        );
    }

    public void gameOver() {
        setVisible(true);
    }
}
