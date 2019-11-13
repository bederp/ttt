import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TicTacToe extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GameManager gameManager = new GameManager();
        System.out.println("Dupa");
        Scene scene = gameManager.getGameScene();
        scene.getStylesheets().add(
                getResource(
                        "tictactoe-blueskin.css"
                )
        );
        System.out.println("Dupa2");
        stage.setTitle("Tic-Tac-Toe");
        stage.getIcons().add(SquareSkin.crossImage);
        stage.setScene(scene);
        gameManager.getGame().updateState(0,0,"kolko");
        gameManager.getGame().updateState(1,1,"blahblah");
        System.out.println("Dupa3");
        stage.show();

    }

    private String getResource(String resourceName) {
        return getClass().getResource(resourceName).toExternalForm();
    }

    public static void main(String[] args) {
        Application.launch(TicTacToe.class);
        System.out.println("MAIN");
    }
}

