import javafx.scene.Scene;

class GameManager {
    private Scene gameScene;

    GameManager() {
        newGame();
    }

    public void newGame() {
        Game game = new Game(this);

        if (gameScene == null) {
            gameScene = new Scene(game.getSkin());
        } else {
            gameScene.setRoot(game.getSkin());
        }
    }

    public void quit() {
        gameScene.getWindow().hide();
    }

    public Scene getGameScene() {
        return gameScene;
    }
}
