import javafx.application.Platform;
import javafx.scene.Scene;

import java.io.*;
import java.net.Socket;

class GameManager {

    private Scene gameScene;
    private Game game;
    private BufferedReader reader;
    private BufferedWriter writer;
    private Socket socket;


    GameManager() {
        newGame();
    }

    private BufferedReader getBufferedReader() throws IOException {
        InputStream input = socket.getInputStream();
        InputStreamReader reader = new InputStreamReader(input);
        return new BufferedReader(reader);
    }

    private BufferedWriter getBufferedWriter() throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        return new BufferedWriter(writer);
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

    public void sentToSocket(int x, int y) {
        try {
            String toWrite = x + " " + y;
            System.out.println("****************" + toWrite);
            writer.write(toWrite);
            writer.newLine();
            writer.flush();
            Thread thread1 = new Thread(() -> {
                try {
                    awaitOtherPlayerMove();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread1.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void awaitOtherPlayerMove() throws IOException {
        String line = reader.readLine();
        System.out.println(line);
        PlayerMove playerMove = new PlayerMove();
        playerMove.parseString(line);
        Platform.runLater(() -> getGame().updateState(playerMove));
    }

    public void setSocket(Socket socket) throws IOException {
        this.socket = socket;
        reader = getBufferedReader();
        writer = getBufferedWriter();
    }
}
