package dev.pa1007.controller;

import dev.pa1007.game.Puzzle;
import dev.pa1007.game.PuzzleGraphic;
import dev.pa1007.utils.LoadSaveException;
import dev.pa1007.utils.Save;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import java.io.IOException;

public class MainController {

    private PuzzleGraphic game;
    private int           count = 0;

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
    @FXML
    private Label    shiftingLabel;

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
        System.exit(0);
    }

    public void moveUP(ActionEvent actionEvent) {
        this.game.move(-1, 0);
        updateClock();
    }

    public void moveDOWN(ActionEvent actionEvent) {
        this.game.move(1, 0);
        updateClock();
    }

    public void moveLEFT(ActionEvent actionEvent) {
        this.game.move(0, -1);
        updateClock();
    }

    public void moveRIGHT(ActionEvent actionEvent) {
        this.game.move(0, 1);
        updateClock();
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
            updateClock();
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
        game.update(gameG, this.clock);
        count = 0;
    }

    @FXML
    void whiteTheme(ActionEvent event) {
        //getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
    }
    @FXML
    void darkTheme(ActionEvent event) {
        //getStylesheets().add(getClass().getResource("dark-theme.css").toExternalForm());
    }
    @FXML
    void blueTheme(ActionEvent event) {
        //getStylesheets().add(getClass().getResource("blue-theme.css").toExternalForm());
    }
    @FXML
    void yellowTheme(ActionEvent event) {
        //getStylesheets().add(getClass().getResource("yellow-theme.css").toExternalForm());
    }

    @FXML
    void loadGameHandler(ActionEvent event) throws IOException, LoadSaveException {
        Puzzle load = Save.load();
        setGame((PuzzleGraphic) load);
        //        FileChooser fc = new FileChooser();
        //        try {
        //            Puzzle load = Save.load(fc.showOpenDialog(gameG.getScene().getWindow()).getPath());
        //            setGame((PuzzleGraphic) load);
        //        }
        //        catch (IOException | LoadSaveException e) {
        //            Alert a = new Alert(Alert.AlertType.ERROR);
        //            a.setContentText(e.getMessage());
        //            a.setContentText("Cela n'a pas fonctionné");
        //            e.printStackTrace();
        //            a.show();
        //        }
    }

    @FXML
    void saveGameHandler(ActionEvent event) {
        try {
            Save.save(game);
        }
        catch (IOException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(e.getMessage());
            a.setContentText("Help");
            e.printStackTrace();
            a.show();
        }

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
    void playWithoutPicture(ActionEvent event) {
        gameG.getChildren().clear();
        PuzzleGraphic game1 = new PuzzleGraphic(4, 4);
        game1.setNumberOnly(true);
        game1.init();

        game.stopTimer();
        this.game = game1;

        game.update(gameG, this.clock);
        count = 0;
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
    //Menu handler stop

    @FXML
    void nButtonHandler(ActionEvent event) {
        Alert alertHtp = new Alert(Alert.AlertType.INFORMATION);
        alertHtp.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alertHtp.setTitle("Navigation button");
        alertHtp.setHeaderText("Navigation button ");
        alertHtp.setResizable(true);
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

    private void updateClock() {
        this.game.update(this.gameG, this.clock);
        game.startTimer(clock);
        count = count + 1;
        this.shiftingLabel.setText("Move : " + count);
    }

    private void initGame() {
        if (gameG.getRowConstraints().size() == 1 && gameG.getColumnConstraints().size() == 1) {
            RowConstraints    rowConstraints    = gameG.getRowConstraints().get(0);
            ColumnConstraints columnConstraints = gameG.getColumnConstraints().get(0);
            for (int i = 0; i < game.getMaxY() - 1; i++) {
                gameG.getRowConstraints().add(rowConstraints);
            }
            for (int i = 0; i < game.getMaxX() - 1; i++) {
                gameG.getColumnConstraints().add(columnConstraints);
            }
        }
        game.update(gameG, clock);
    }
}