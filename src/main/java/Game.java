import javafx.scene.Parent;

class Game {
    private GameSkin skin;
    private PlayAgainChoice playAgainChoice;
    private StatusIndicator statusIndicator;
    private Board board = new Board(this);
    private WinningStrategy winningStrategy = new WinningStrategy(board);
    private Square.State currentPlayer = Square.State.CROSS;
    private WinningStrategy.GameStatus winner = WinningStrategy.GameStatus.IN_PROGRESS;
    private boolean gameOver = false;

    public Game(GameManager gameManager) {
        this.playAgainChoice = new PlayAgainChoice(gameManager, this);
        this.statusIndicator = new StatusIndicator(this);
        this.skin = new GameSkin(this, playAgainChoice, statusIndicator);
    }

    public Square.State getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    void setGameOver() {
        gameOver = true;
        playAgainChoice.gameOver();
    }

    public Board getBoard() {
        return board;
    }

    public void updateState() {
        checkForWinner();
        setNextPlayer();
    }

    public void setNextPlayer() {
        if (gameOver) return;

        switch (currentPlayer) {
            case EMPTY:
            case NOUGHT:
                currentPlayer = Square.State.CROSS;
                break;
            case CROSS:
                currentPlayer = Square.State.NOUGHT;
                break;
        }
        statusIndicator.playerToken(currentPlayer);
    }

    private void checkForWinner() {
        winner = winningStrategy.getWinner();
        System.out.println("Winner:"+winner);
        statusIndicator.playerLabel(winner);

        if (WinningStrategy.GameStatus.IN_PROGRESS != winner) {
            setGameOver();
        }

        if (WinningStrategy.GameStatus.DRAW == winner) {
            currentPlayer = Square.State.EMPTY;
        }
    }

    public Parent getSkin() {
        return skin;
    }
}
