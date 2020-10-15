package dev.pa1007.controller;

import dev.pa1007.game.PuzzleGraphic;
import dev.pa1007.game.draw.StopwatchTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

public class MainController {

    private PuzzleGraphic  game;
    private StopwatchTimer stopwatchTimer;

    @FXML
    private MenuItem startAIItem;
    @FXML
    private MenuItem stopAIItem;
    @FXML
    private GridPane mainG;
    @FXML
    private Text     clock;
    @FXML
    private GridPane gameG;


    public void setGame(PuzzleGraphic game) {
        this.game = game;
        initGame();
    }

    public void stopTimer(ActionEvent actionEvent) {
        game.stopTimer();
    }

    @FXML
    public void quit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void onKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case DOWN -> this.game.move(1, 0);
            case UP -> this.game.move(-1, 0);
            case RIGHT -> this.game.move(0, 1);
            case LEFT -> this.game.move(0, -1);
        }
        if (event.getCode() == KeyCode.DOWN
            || event.getCode() == KeyCode.UP
            || event.getCode() == KeyCode.LEFT
            || event.getCode() == KeyCode.RIGHT) {
            this.game.update(this.gameG);
            game.startTimer(clock);
        }
        if(this.game.isSolved()) {
            this.game.stopTimer();
        }
    }

    //Menu handler start
    @FXML
    void newGameHandler(ActionEvent event) {

    }

    @FXML
    void loadGameHandler(ActionEvent event) {

    }

    @FXML
    void saveGameHandler(ActionEvent event) {

    }

    @FXML
    void startAIHandler(ActionEvent event) {

    }

    @FXML
    void stopAIHandler(ActionEvent event) {

    }

    @FXML
    void nextMoveAIHandler(ActionEvent event) {

    }

    @FXML
    void changeAIHandler(ActionEvent event) {

    }

    @FXML
    void leaderboardShowHandler(ActionEvent event) {

    }

    @FXML
    void htpHandler(ActionEvent event) {

    }

    @FXML
    void aboutHandler(ActionEvent event) {

    }
    //Menu handler stop

    private void initGame() {
        RowConstraints    rowConstraints    = gameG.getRowConstraints().get(0);
        ColumnConstraints columnConstraints = gameG.getColumnConstraints().get(0);
        for (int i = 0; i < game.getMaxY() - 1; i++) {
            gameG.getRowConstraints().add(rowConstraints);
        }
        for (int i = 0; i < game.getMaxX() - 1; i++) {
            gameG.getColumnConstraints().add(columnConstraints);
        }
        game.update(gameG);
    }


}