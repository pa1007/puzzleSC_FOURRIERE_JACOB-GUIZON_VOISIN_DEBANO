package dev.pa1007;

import dev.pa1007.controller.HomeController;
import dev.pa1007.controller.MainController;
import dev.pa1007.game.PuzzleConsole;
import dev.pa1007.game.PuzzleGraphic;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader         loaderGame     = new FXMLLoader(getClass().getResource("scene.fxml"));
        FXMLLoader         loaderHome    = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent             rootGame       = loaderGame.load();
        Parent             rootHome      = loaderHome.load();
        MainController mainController = loaderGame.getController();
        HomeController Homecontroller = loaderHome.getController();
        stage.setTitle("Taquin");
        /*stage.setMinWidth(625);
        stage.setMinHeight(775);*/
        stage.setWidth(625);
        stage.setHeight(755);
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResource("images/taquin.png").toExternalForm()));
        Scene sceneGame = new Scene(rootGame);
        Scene sceneHomePage = new Scene(rootHome);
        stage.setScene(sceneHomePage);
        stage.show();

        sceneHomePage.getStylesheets().add(getClass().getResource("dark-theme.css").toExternalForm());
        rootHome.requestFocus();

        Button newGame = Homecontroller.getNewGame();
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean whiteTheme = Homecontroller.getRadioButtonWhiteTheme();
                boolean darkTheme = Homecontroller.getRadioButtonDarkTheme();
                boolean blueTheme = Homecontroller.getRadioButtonBlueTheme();
                boolean yellowTheme = Homecontroller.getRadioButtonYellowTheme();
                boolean console = Homecontroller.getRadioButtonConsole();
                boolean number = Homecontroller.getRadioButtonNumber();
                boolean picture = Homecontroller.getRadioButtonPicture();
                if(whiteTheme) {
                    sceneGame.getStylesheets().clear();
                    sceneGame.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
                } else if (darkTheme) {
                    sceneGame.getStylesheets().clear();
                    sceneGame.getStylesheets().add(getClass().getResource("dark-theme.css").toExternalForm());
                } else if(blueTheme) {
                    sceneGame.getStylesheets().clear();
                    sceneGame.getStylesheets().add(getClass().getResource("blue-theme.css").toExternalForm());
                }else if (yellowTheme) {
                    sceneGame.getStylesheets().clear();
                    sceneGame.getStylesheets().add(getClass().getResource("yellow-theme.css").toExternalForm());
                }
                if(console) {
                    /*stage.close();
                    PuzzleConsole p = new PuzzleConsole(4,4);
                    p.init();
                    p.startGameLine();*/
                } else if (number) {
                    int nb = 4;
                    String stringNb = Homecontroller.getGridSize();
                    if(!stringNb.isBlank()) {
                        nb = Integer.parseInt(stringNb);
                    }
                    rootGame.requestFocus();
                    PuzzleGraphic game       = new PuzzleGraphic(nb, nb);
                    game.setNumberOnly(true);
                    game.init();
                    mainController.setGame(game);
                    stage.setScene(sceneGame);
                } else if (picture) {
                    int nb = 4;
                    String stringNb = Homecontroller.getGridSize();
                    if(!stringNb.isBlank()) {
                        nb = Integer.parseInt(stringNb);
                    }
                    rootGame.requestFocus();
                    PuzzleGraphic game       = new PuzzleGraphic(nb, nb);
                    game.setNumberOnly(false);
                    game.init();
                    mainController.setGame(game);
                    stage.setScene(sceneGame);
                }
            }
        });
        MenuItem whiteTheme = mainController.getWhiteTheme();
        MenuItem darkTheme = mainController.getDarkTheme();
        MenuItem blueTheme = mainController.getBlueTheme();
        MenuItem yellowTheme = mainController.getYellowTheme();
        whiteTheme.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneGame.getStylesheets().clear();
                sceneGame.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            }
        });
        darkTheme.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneGame.getStylesheets().clear();
                sceneGame.getStylesheets().add(getClass().getResource("dark-theme.css").toExternalForm());
            }
        });
        blueTheme.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneGame.getStylesheets().clear();
                sceneGame.getStylesheets().add(getClass().getResource("blue-theme.css").toExternalForm());
            }
        });
        yellowTheme.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneGame.getStylesheets().clear();
                sceneGame.getStylesheets().add(getClass().getResource("yellow-theme.css").toExternalForm());
            }
        });

        /*Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("dark-theme.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        root.requestFocus();*/
    }

    public static void main(String[] args) {
        launch(args);
    }
}
