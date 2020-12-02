package dev.pa1007.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;


public class WinController {

    @FXML
    private Image imageYouWon;
    @FXML
    private Button closeTheGame;


    @FXML
    void closeTheGameHandler (ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
}
