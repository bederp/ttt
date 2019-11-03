import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

class StatusIndicator extends HBox {
    private final ImageView playerToken = new ImageView();
    private final Label playerLabel = new Label("Current Player: ");

    StatusIndicator(Game game) {
        getStyleClass().add("status-indicator");

        bindIndicatorFieldsToGame(game);

        playerToken.setFitHeight(32);
        playerToken.setPreserveRatio(true);

        playerLabel.getStyleClass().add("info");

        getChildren().addAll(playerLabel, playerToken);
    }

    private void bindIndicatorFieldsToGame(Game game) {
        playerLabel.textProperty().bind(
                Bindings.when(
                        game.gameOverProperty().not()
                )
                        .then("Current Player: ")
                        .otherwise(
                                Bindings.when(
                                        game.winnerProperty().isEqualTo(Square.State.EMPTY)
                                )
                                        .then("Draw")
                                        .otherwise("Winning Player: ")
                        )
        );
    }

    void playerToken(Square.State state) {
        switch (state) {
            case NOUGHT:
                playerToken.setImage(SquareSkin.noughtImage);
                break;
            case CROSS:
                playerToken.setImage(SquareSkin.crossImage);
            default:
                playerToken.setImage(null);
        }
    }
}
