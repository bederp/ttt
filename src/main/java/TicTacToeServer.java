import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;

public class TicTacToeServer extends Application {
    private static GameManager gameManager = new GameManager();

    public static void main(String[] args) throws IOException, InterruptedException {
        Thread thread = new Thread(() -> Application.launch(TicTacToeServer.class));
        thread.start();
//        Application.launch(TicTacToeServer.class);
        ServerSocket serverSocket = new ServerSocket(55556);
        gameManager.setSocket(serverSocket.accept());
        gameManager.awaitOtherPlayerMove();

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
        stage.setTitle("TTT-Server");
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
