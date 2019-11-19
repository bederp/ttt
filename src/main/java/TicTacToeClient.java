import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class TicTacToeClient extends Application {
    private static GameManager gameManager;

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 55556);
        gameManager = new GameManager();
        gameManager.setSocket(socket);

        Thread thread = new Thread(() -> Application.launch(TicTacToeClient.class));
        thread.start();

        thread.join();
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Dupa");
        Scene scene = gameManager.getGameScene();
        scene.getStylesheets().add(
                getResource(
                        "tictactoe-blueskin.css"
                )
        );
        System.out.println("Dupa2");
        stage.setTitle("TTT-Client");
        stage.getIcons().add(SquareSkin.crossImage);
        stage.setScene(scene);
        // gameManager.getGame().updateState(0,0,"kolko");
        // gameManager.getGame().updateState(1,1,"blahblah");
        System.out.println("Dupa3");
        stage.show();

    }

    private String getResource(String resourceName) {
        return getClass().getResource(resourceName).toExternalForm();
    }


}
