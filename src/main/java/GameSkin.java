import javafx.scene.layout.VBox;

class GameSkin extends VBox {
    GameSkin(Game game, PlayAgainChoice playAgainChoice, StatusIndicator statusIndicator) {
        getChildren().addAll(
                game.getBoard().getSkin(),
                statusIndicator,
                playAgainChoice
        );
    }
}
