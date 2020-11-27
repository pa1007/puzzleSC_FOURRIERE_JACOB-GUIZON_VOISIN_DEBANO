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
        sceneGame.getStylesheets().add(getClass().getResource("dark-theme.css").toExternalForm());
        rootHome.requestFocus();

        Button newGame = Homecontroller.getNewGame();
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean console = Homecontroller.getRadioButtonConsole();
                boolean number = Homecontroller.getRadioButtonNumber();
                boolean picture = Homecontroller.getRadioButtonPicture();

                if (console) {
                    /*stage.close();
                    PuzzleConsole p = new PuzzleConsole(4,4);
                    p.init();
                    p.startGameLine();*/
                } else if (number) {
                    int nb = 4;
                    String stringNb = Homecontroller.getGridSize();
                    if (!stringNb.isBlank()) {
                        nb = Integer.parseInt(stringNb);
                    }
                    rootGame.requestFocus();
                    PuzzleGraphic game = new PuzzleGraphic(nb, nb);
                    game.setNumberOnly(true);
                    game.init();
                    mainController.setGame(game);
                    stage.setScene(sceneGame);
                } else if (picture) {
                    int nb = 4;
                    String stringNb = Homecontroller.getGridSize();
                    if (!stringNb.isBlank()) {
                        nb = Integer.parseInt(stringNb);
                    }
                    rootGame.requestFocus();
                    if (Homecontroller.getImagePath() == null) {
                        PuzzleGraphic game = new PuzzleGraphic(nb, nb);
                        game.setNumberOnly(false);
                        game.init();
                        mainController.setGame(game);
                    } else {
                        String imagePath = Homecontroller.getImagePath();
                        PuzzleGraphic game = new PuzzleGraphic(nb, nb, imagePath);
                        game.setNumberOnly(false);
                        game.init();
                        mainController.setGame(game);
                    }
                    stage.setScene(sceneGame);
                }
            }
        });
        MenuItem whiteThemeMain = mainController.getWhiteTheme();
        MenuItem darkThemeMain = mainController.getDarkTheme();
        MenuItem blueThemeMain = mainController.getBlueTheme();
        MenuItem yellowThemeMain = mainController.getYellowTheme();
        MenuItem whiteThemeHome = Homecontroller.getWhiteTheme();
        MenuItem darkThemeHome = Homecontroller.getDarkTheme();
        MenuItem blueThemeHome = Homecontroller.getBlueTheme();
        MenuItem yellowThemeHome = Homecontroller.getYellowTheme();
        whiteThemeMain.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneGame.getStylesheets().clear();
                sceneGame.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            }
        });
        darkThemeMain.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneGame.getStylesheets().clear();
                sceneGame.getStylesheets().add(getClass().getResource("dark-theme.css").toExternalForm());
            }
        });
        blueThemeMain.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneGame.getStylesheets().clear();
                sceneGame.getStylesheets().add(getClass().getResource("blue-theme.css").toExternalForm());
            }
        });
        yellowThemeMain.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneGame.getStylesheets().clear();
                sceneGame.getStylesheets().add(getClass().getResource("yellow-theme.css").toExternalForm());
            }
        });
        whiteThemeHome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneHomePage.getStylesheets().clear();
                sceneHomePage.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
                sceneGame.getStylesheets().clear();
                sceneGame.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            }
        });
        darkThemeHome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneHomePage.getStylesheets().clear();
                sceneHomePage.getStylesheets().add(getClass().getResource("dark-theme.css").toExternalForm());
                sceneGame.getStylesheets().clear();
                sceneGame.getStylesheets().add(getClass().getResource("dark-theme.css").toExternalForm());
            }
        });
        blueThemeHome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneHomePage.getStylesheets().clear();
                sceneHomePage.getStylesheets().add(getClass().getResource("blue-theme.css").toExternalForm());
                sceneGame.getStylesheets().clear();
                sceneGame.getStylesheets().add(getClass().getResource("blue-theme.css").toExternalForm());
            }
        });
        yellowThemeHome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneHomePage.getStylesheets().clear();
                sceneHomePage.getStylesheets().add(getClass().getResource("yellow-theme.css").toExternalForm());
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
