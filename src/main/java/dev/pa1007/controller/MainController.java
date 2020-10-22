package dev.pa1007.controller;

import dev.pa1007.game.PuzzleGraphic;
import dev.pa1007.game.draw.StopwatchTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
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
            this.game.update(this.gameG, this.clock);
            game.startTimer(clock);
        }
        if (this.game.isSolved()) {
            this.game.stopTimer();
        }
    }

    //Menu handler start
    @FXML
    void newGameHandler(ActionEvent event) {
        gameG.getChildren().clear();
        PuzzleGraphic game1 = new PuzzleGraphic(4, 4);
        game1.init();
        game.stopTimer();
        this.game = game1;
        game.update(gameG, clock);

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
        Alert alertHtp = new Alert(Alert.AlertType.INFORMATION);
        alertHtp.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alertHtp.setTitle("How To Play");
        alertHtp.setHeaderText("Regles du jeu");
        alertHtp.setResizable(true);
        String content = String.format("But du jeu : \n" +
                                       "Sur un plateau de 16 cases, il faut replacer, en les glissant, 15 pions dans le bon ordre avec le moins de mouvements possibles. Un click sur le bouton [New Game] melange les pions. Avec la souris, cliquez sur le pion que vous voulez deplacer vers la case libre.Au clavier, utilisez les fleches pour effectuer les deplacements");
        alertHtp.setContentText(content);
        alertHtp.showAndWait();
    }

    @FXML
    void aboutHandler(ActionEvent event) {
        Alert alertAbout = new Alert(Alert.AlertType.INFORMATION);
        alertAbout.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alertAbout.setTitle("About");
        alertAbout.setResizable(true);
        alertAbout.setHeaderText("About");
        String content = String.format("Java version : 14 \n"
                                       +
                                       "Auteur : Paul-Alexandre FOURRIERE | Paul DEBANO | Louis VOISIN | Amelie JACOB--GUIZON \n"
                                       +
                                       "Produit dans le cadre du cours Ingenierie logiciel de L3 MIASHS SC");
        alertAbout.setContentText(content);
        alertAbout.showAndWait();
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
        game.update(gameG, clock);
    }


}