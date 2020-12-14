package dev.pa1007.controller;

import dev.pa1007.game.PuzzleConsole;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;


public class HomeController {

    private   String        imagePath;

    @FXML
    private   TextField     gridSize;
    @FXML
    private   RadioButton   radioButtonNumber;
    @FXML
    private   RadioButton   radioButtonPicture;
    @FXML
    private   Button        newGame;
    @FXML
    private   MenuItem      whiteTheme;
    @FXML
    private   MenuItem      darkTheme;
    @FXML
    private   MenuItem      blueTheme;
    @FXML
    private   MenuItem      yellowTheme;
    @FXML
    private   Label         nomChemin;


    public String getImagePath() {
        return imagePath;
    }

    public String getGridSize() {
        return gridSize.getText();
    }

    public boolean getRadioButtonNumber() {
        return radioButtonNumber.isSelected();
    }

    public boolean getRadioButtonPicture() {
        return radioButtonPicture.isSelected();
    }

    public Button getNewGame() {
        return newGame;
    }

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

    @FXML
    public void newGameHandler(ActionEvent event) {

    }

    @FXML
    public void loadGameHandler(ActionEvent event) {
        FileChooser fc   = new FileChooser();
        File        game = fc.showOpenDialog(null);
    }

    @FXML
    public void quitHandler(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void chooseImageHandler(ActionEvent event) {
        FileChooser fc    = new FileChooser();
        fc.setTitle("Choose image");
        File        image = fc.showOpenDialog(null);
        if (image.canRead()) {
            imagePath = image.getPath();
            nomChemin.setText(imagePath);
        }
    }

    public void leaderboardShowHandler(ActionEvent actionEvent) throws IOException {
        MainController.createLeaderBoard();
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

    @FXML
    private void initialize() {
        ToggleGroup gui   = new ToggleGroup();
        radioButtonNumber.setToggleGroup(gui);
        radioButtonPicture.setToggleGroup(gui);
        radioButtonPicture.setSelected(true);
    }
}
