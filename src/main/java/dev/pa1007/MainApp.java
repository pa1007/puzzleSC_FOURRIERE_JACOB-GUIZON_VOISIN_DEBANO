package dev.pa1007;

import dev.pa1007.controller.MainController;
import dev.pa1007.game.PuzzleGraphic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader     loader     = new FXMLLoader(getClass().getResource("scene.fxml"));
        Parent         root       = loader.load();
        MainController controller = loader.getController();
        PuzzleGraphic  game       = new PuzzleGraphic(15, 12);
        game.initImage();
        controller.setGame(game);
        Scene scene = new Scene(root);
        stage.setTitle("Taquin");
        stage.setMinWidth(250);
        // scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        root.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
