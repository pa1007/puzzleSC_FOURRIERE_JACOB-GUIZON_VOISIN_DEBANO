package dev.pa1007.controller;

import dev.pa1007.MainApp;
import dev.pa1007.ai.AI;
import dev.pa1007.ai.AIAlgo;
import dev.pa1007.ai.AIRandom;
import dev.pa1007.game.Puzzle;
import dev.pa1007.game.PuzzleGraphic;
import dev.pa1007.utils.LoadSaveException;
import dev.pa1007.utils.Save;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class MainController {


    @FXML
    public  MenuItem      nextMoveAIItem;
    private PuzzleGraphic game;
    private int           count;
    private boolean       stopAi;
    private AI            ai = new AIRandom();
    @FXML
    private MenuItem      startAIItem;
    @FXML
    private MenuItem      stopAIItem;
    @FXML
    private GridPane      mainG;
    @FXML
    private Text          clock;
    @FXML
    private GridPane      gameG;
    @FXML
    private Label         shiftingLabel;
    @FXML
    private MenuItem      whiteTheme;
    @FXML
    private MenuItem      darkTheme;
    @FXML
    private MenuItem      blueTheme;
    @FXML
    private MenuItem      yellowTheme;
    @FXML
    private MenuItem      newGameMain;
    private int           lastResult;

    public MenuItem getWhiteTheme() {
        return whiteTheme;
    }

    public MenuItem getDarkTheme() {
        return darkTheme;
    }

    public MenuItem getBlueTheme() {
        return blueTheme;
    }

    public MenuItem getYellowTheme() {
        return yellowTheme;
    }

    public MenuItem getNewGameMain() {
        return newGameMain;
    }

    public GridPane getGameG() {
        return gameG;
    }

    public Text getClock() {
        return clock;
    }

    public PuzzleGraphic getGame() {
        return game;
    }

    public void setGame(PuzzleGraphic game) {
        this.game = game;
        initGame();
    }

    public void setGame2(PuzzleGraphic game) {
        this.game = game;
    }

    public void setCount(int i) {
        count = i;
    }

    public void stopTimer(ActionEvent actionEvent) {
        game.stopTimer();
    }

    @FXML
    public void moveUP(ActionEvent actionEvent) {
        this.game.move(-1, 0);
        updateClock();
    }

    @FXML
    public void moveDOWN(ActionEvent actionEvent) {
        this.game.move(1, 0);
        updateClock();
    }

    @FXML
    public void moveLEFT(ActionEvent actionEvent) {
        this.game.move(0, -1);
        updateClock();
    }

    @FXML
    public void moveRIGHT(ActionEvent actionEvent) {
        this.game.move(0, 1);
        updateClock();
    }

    @FXML
    public void quit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void onKeyPressed(KeyEvent event) throws IOException {
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
        testVictory();
    }

    //Menu handler start
    @FXML
    void newGameHandler(ActionEvent event) {
        gameG.getChildren().clear();
        PuzzleGraphic game1 = new PuzzleGraphic(4, 4);
        game1.init();
        this.game = game1;
        game.stopTimer();
        game.update(gameG, this.clock);
        count = 0;
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
        stopAi = false;
        stopAIItem.setDisable(false);
        ai.setAuto(true);
        if (ai instanceof AIAlgo) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Temps de calcule");
            alert.setHeaderText("Le temps de calcule pour cette algorithme est long,");
            alert.setContentText(" cela peut prendre plusieurs minutes, voulez vous continuez ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                new Thread(() -> {
                    while (!stopAi && lastResult != 6) {
                        nextMoveAIHandler(event);
                        try {
                            Thread.sleep(100);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                }).start();
            }
        }
        else {
            new Thread(() -> {
                while (!stopAi) {
                    nextMoveAIHandler(event);
                    try {
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }).start();
        }


    }

    @FXML
    void stopAIHandler(ActionEvent event) {
        stopAi = true;
        stopAIItem.setDisable(true);
    }

    @FXML
    void nextMoveAIHandler(ActionEvent event) {
        Platform.runLater(() -> {
            try {
                testVictory();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
        lastResult = ai.faireChoix(game);
        switch (lastResult) {
            case 1: // Up
                Platform.runLater(() -> moveUP(event));
                break;
            case 2: //right
                Platform.runLater(() -> moveRIGHT(event));
                break;
            case 3: //down
                Platform.runLater(() -> moveDOWN(event));
                break;
            case 4: //left
                Platform.runLater(() -> moveLEFT(event));
                break;
            case 6:
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Impossible");
                    alert.setHeaderText("L'algorithme pense que :");
                    alert.setContentText("Ce plateau de jeu n'est pas possible a etre resolut");
                    alert.show();
                });
                break;
            case 5:
            case 0:
                break;
        }

    }

    @FXML
    void changeAIHandler(ActionEvent event) {
        ChoiceDialog<String> cd       = new ChoiceDialog<>("Random", List.of("Random", "Algorithm de Dijkstra"));
        Optional<String>     optional = cd.showAndWait();
        if (optional.isPresent()) {
            switch (optional.get()) {
                case "Random" -> ai = new AIRandom();
                case "Algorithm de Dijkstra" -> ai = new AIAlgo();
            }
            startAIItem.setDisable(false);
            nextMoveAIItem.setDisable(false);

        }

    }

    @FXML
    void leaderboardShowHandler(ActionEvent events) throws IOException {
        createLeaderBoard();
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
    //Menu handler stop

    private void testVictory() throws IOException {
        if (this.game.isSolved()) {
            stopAi = true;
            this.game.stopTimer();
            Stage         stage         = new Stage();
            FXMLLoader    loader        = new FXMLLoader(MainApp.class.getResource("win.fxml"));
            Parent        root          = loader.load();
            WinController winController = loader.getController();
            winController.setInfos(game.getTimer().toString(), count);
            Scene scene = new Scene(root);
            stage.setTitle("You Win !");
            stage.setMinWidth(625);
            scene.getStylesheets().add(MainApp.class.getResource("dark-theme.css").toExternalForm());
            stage.getIcons().add(new Image(MainApp.class.getResource("images/taquin.png").toExternalForm()));
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            root.requestFocus();
        }
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

    public static void createLeaderBoard() throws IOException {
        Stage                 stage  = new Stage();
        FXMLLoader            loader = new FXMLLoader(MainApp.class.getResource("leaderboard.fxml"));
        Parent                root   = loader.load();
        LeaderBoardController lbc    = loader.getController();
        lbc.init();
        Scene scene = new Scene(root);
        stage.setTitle("LeaderBoard");
        stage.setMinWidth(625);
        scene.getStylesheets().add(MainApp.class.getResource("dark-theme.css").toExternalForm());
        stage.getIcons().add(new Image(MainApp.class.getResource("images/taquin.png").toExternalForm()));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        root.requestFocus();
    }
}