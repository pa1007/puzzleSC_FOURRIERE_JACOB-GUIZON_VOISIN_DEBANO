package dev.pa1007;

import dev.pa1007.controller.HomeController;
import dev.pa1007.controller.MainController;
import dev.pa1007.game.PuzzleGraphic;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.*;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader     loader     = new FXMLLoader(getClass().getResource("scene.fxml"));
        FXMLLoader     loader2    = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent         root       = loader.load();
        Parent         root2      = loader2.load();
        MainController controller = loader.getController();
        HomeController controller2= loader2.getController();
        PuzzleGraphic  game       = new PuzzleGraphic(4, 4);
        game.setNumberOnly(false);
        game.init();
        controller.setGame(game);
        Scene scene = new Scene(root);
        stage.setTitle("Taquin");
        stage.setMinWidth(625);
        stage.setMinHeight(775);
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
        scene.getStylesheets().add(getClass().getResource("dark-theme.css").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResource("images/taquin.png").toExternalForm()));
        stage.setScene(scene);
        stage.show();
        root.requestFocus();

        Scene scene2 = new Scene(root2);
        stage.setTitle("Home page");
        //stage.getIcons().add(new Image("images/taquin.png"));
        stage.setScene(scene2);
        stage.show();
        root2.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
