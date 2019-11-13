import javafx.scene.Scene;

class GameManager {
    private Scene gameScene;

    private Game game;

    GameManager() {
        newGame();
    }

    public void newGame() {
        Game game = new Game(this);
        this.game = game;

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

    public Game getGame() {
        return this.game;
    }
}
