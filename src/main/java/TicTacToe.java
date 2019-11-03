/**
 * Copyright 2013 John Smith
 * <p>
 * This file is part of Jewelsea Tic-Tac-Toe.
 * <p>
 * Jewelsea Tic-Tac-Toe is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * Jewelsea Tic-Tac-Toe is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with Jewelsea Tic-Tac-Toe.  If not, see <http://www.gnu.org/licenses/>.
 * <p>
 * Contact details: http://jewelsea.wordpress.com
 * <p>
 * icon image license => creative commons with attribution:
 * http://creativecommons.org/licenses/by/3.0/
 * icon image creator attribution:
 * http://www.doublejdesign.co.uk/products-page/icons/origami-colour-pencil
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TicTacToe extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GameManager gameManager = new GameManager();

        Scene scene = gameManager.getGameScene();
        scene.getStylesheets().add(
                getResource(
                        "tictactoe-blueskin.css"
                )
        );

        stage.setTitle("Tic-Tac-Toe");
        stage.getIcons().add(SquareSkin.crossImage);
        stage.setScene(scene);
        stage.show();
    }

    private String getResource(String resourceName) {
        return getClass().getResource(resourceName).toExternalForm();
    }

    public static void main(String[] args) {
        Application.launch(TicTacToe.class);
    }
}

