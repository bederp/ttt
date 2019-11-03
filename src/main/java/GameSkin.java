import javafx.scene.layout.VBox;

class GameSkin extends VBox {
    GameSkin(GameManager gameManager, Game game) {
        getChildren().addAll(
                game.getBoard().getSkin(),
                new StatusIndicator(game),
                new GameControls(gameManager, game)
        );
    }
}
