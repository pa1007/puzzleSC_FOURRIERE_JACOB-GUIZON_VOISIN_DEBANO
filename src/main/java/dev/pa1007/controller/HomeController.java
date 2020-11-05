package dev.pa1007.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;


public class HomeController {

    @FXML
    private TextField gridSize;
    @FXML
    private CheckBox withGUI;

    public boolean getWithGUI() {
        return withGUI.isSelected();
    }

    @FXML
    public void menuNewGameHandler(ActionEvent event) {

    }

    @FXML
    public void loadGameHandler(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File game = fc.showOpenDialog(null);
        game.getAbsolutePath();
    }

    @FXML
    public void quitHandler(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void chooseImageHandler(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File image = fc.showOpenDialog(null);
        image.getAbsolutePath();
    }

    @FXML
    public void newGameHandler(ActionEvent event) {
        String stringSize = gridSize.getText();
        if(!stringSize.isEmpty()) {
            int intSize = Integer.parseInt(stringSize);
        }
        System.out.println(getWithGUI());
    }

}
