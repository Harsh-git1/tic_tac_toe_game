package com.example.ticktaktoe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTakToe extends Application {

    private Label playerXScoreLabel, playerOScoreLabel;
    private boolean playerXTurn = false;
    private int playerOscore = 0, playerXscore = 0;
    private Button[][] buttons = new Button[3][3];
    private BorderPane createContent() {
        //title
        BorderPane root = new BorderPane();
        Label titleLable = new Label("Tic Tac Toe");
        titleLable.setStyle("-fx-font-size : 24pt; -fx-font-weight : bold;");
        root.setTop(titleLable);
        root.setPadding(new Insets(20));
        BorderPane.setAlignment(titleLable, Pos.CENTER);
        //grid
        GridPane grid = new GridPane();
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                Button button = new Button("");
                button.setPrefSize(100, 100);
                button.setStyle("-fx-font-size : 20pt; -fx-font-weight : bold ;");
                button.setOnAction(event -> buttonPressed(button));
                buttons[i][j] = button;
                grid.add(button, j, i);
            }
        }

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        root.setCenter(grid);
        //BorderPane.setAlignment(grid, Pos.CENTER);
        //score
        HBox scoreBoard = new HBox(20);
        scoreBoard.setAlignment(Pos.CENTER);
        playerOScoreLabel = new Label("Player O Score : 0");
        playerOScoreLabel.setStyle("-fx-font-size : 14pt; -fx-font-weight : bold ;");
        playerXScoreLabel = new Label("Player X Score : 0 ");
        playerXScoreLabel.setStyle("-fx-font-size : 14pt; -fx-font-weight : bold ;");

        scoreBoard.getChildren().addAll(playerOScoreLabel, playerXScoreLabel);
        root.setBottom(scoreBoard);
        return root;

    }

    private void buttonPressed(Button button){

        if(button.getText().equals("")) {
            if (playerXTurn) button.setText("X");
            else button.setText("O");
            playerXTurn = !playerXTurn;
        }

        checkWinner();
    }

    private void checkWinner() {

        //row
        for(int row=0; row<3; row++) {
            if(buttons[row][0].getText().equals(buttons[row][1].getText())
                    && buttons[row][1].getText().equals(buttons[row][2].getText())
                    && !buttons[row][0].getText().isEmpty()) {
                    showWinnerDialog(buttons[row][0].getText());
                    updateScore(buttons[row][0].getText());
                    resetBoard();
                return;
            }

        }

        //col
        for(int col=0; col<3; col++) {
            if(buttons[0][col].getText().equals(buttons[1][col].getText())
                    && buttons[1][col].getText().equals(buttons[2][col].getText())
                    && !buttons[0][col].getText().isEmpty()) {
                    showWinnerDialog(buttons[0][col].getText());
                    updateScore(buttons[0][col].getText());
                    resetBoard();
                return;
            }

        }

        //digonal
        if(buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][2].getText())
                && !buttons[0][0].getText().isEmpty()) {
            showWinnerDialog(buttons[0][0].getText());
            updateScore(buttons[0][0].getText());
            resetBoard();
            return;
        }
        if(buttons[0][2].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][0].getText())
                && !buttons[2][0].getText().isEmpty()) {
            showWinnerDialog(buttons[2][0].getText());
            updateScore(buttons[0][2].getText());
            resetBoard();
            return;
        }

        Boolean tie = true;
        for(Button[] b : buttons) {
            for(Button button : b) {
                if(button.getText().isEmpty()) {
                    tie = false;
                    break;
                }
            }
        }

        if(tie) {
            showTieDialog();
            resetBoard();
        }
    }

    private void showWinnerDialog(String winner) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        if(winner.equals("O")) {
            alert.setContentText("Wow...!! Player O won the game \n\nNow the first turn will be of player 'X'");
        }else {
            alert.setContentText("Wow...!! Player X won the game \n\nNow the first turn will be of player 'O'");
        }
        alert.setHeaderText("");
        alert.showAndWait();
    }

    private void showTieDialog() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie");
        alert.setContentText("Ohh.. IT'S A TIE ");
        alert.setHeaderText("");
        alert.showAndWait();
    }

    private void updateScore(String winner) {
        if (winner.equals("X")) {
            playerXscore++;
            playerXScoreLabel.setText("Player X Score : " + playerXscore);
        }else {
            playerOscore++;
            playerOScoreLabel.setText("Player O Score : " + playerOscore);
        }
    }

    private void resetBoard() {

        for(Button[] b : buttons) {
            for(Button button : b) {
                button.setText("");
            }
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}