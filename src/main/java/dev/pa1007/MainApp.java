package dev.pa1007;

import dev.pa1007.controller.MainController;
import dev.pa1007.game.Puzzle;
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
        Puzzle         game       = new Puzzle(15, 10);
        game.init();
        System.out.println(game.createString());
        controller.setGame(game);
        Scene scene = new Scene(root);
        stage.setTitle("Taquin");
        stage.setMinWidth(250);
        // scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
