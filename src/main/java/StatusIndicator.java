import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

class StatusIndicator extends HBox {
    private final ImageView playerToken = new ImageView();
    private final Label playerLabel = new Label("Current Player: ");

    StatusIndicator(Game game) {
        getStyleClass().add("status-indicator");

        playerToken.setFitHeight(32);
        playerToken.setPreserveRatio(true);
        playerLabel.getStyleClass().add("info");
        playerToken.setImage(SquareSkin.crossImage);

        getChildren().addAll(playerLabel, playerToken);
    }

    void playerLabel(WinningStrategy.GameStatus gameStatus) {
        if (WinningStrategy.GameStatus.DRAW == gameStatus) {
            playerLabel.setText("Draw");
        } else if (WinningStrategy.GameStatus.IN_PROGRESS == gameStatus) {
            playerLabel.setText("Current Player: ");
        } else {
            playerLabel.setText("Winning Player: ");
        }
    }

    void playerToken(Square.State state) {
        switch (state) {
            case NOUGHT:
                playerToken.setImage(SquareSkin.noughtImage);
                break;
            case CROSS:
                playerToken.setImage(SquareSkin.crossImage);
                break;
            default:
                playerToken.setImage(null);
        }
    }
}
