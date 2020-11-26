package dev.pa1007.controller;

import dev.pa1007.game.PuzzleConsole;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;


public class HomeController {

    private PuzzleConsole gameWithoutGUI;

    @FXML
    private TextField gridSize;
    @FXML
    private RadioButton radioButtonConsole;
    @FXML
    private RadioButton radioButtonNumber;
    @FXML
    private RadioButton radioButtonPicture;
    @FXML
    protected RadioButton radioButtonWhiteTheme;
    @FXML
    protected RadioButton radioButtonDarkTheme;
    @FXML
    protected RadioButton radioButtonBlueTheme;
    @FXML
    protected RadioButton radioButtonYellowTheme;
    @FXML
    private Button newGame;


    public String getGridSize() {
        return gridSize.getText();
    }
    public boolean getRadioButtonConsole() {
        return radioButtonConsole.isSelected();
    }
    public boolean getRadioButtonNumber() {
        return radioButtonNumber.isSelected();
    }
    public boolean getRadioButtonPicture() {
        return radioButtonPicture.isSelected();
    }
    public boolean getRadioButtonWhiteTheme() {
        return radioButtonWhiteTheme.isSelected();
    }
    public boolean getRadioButtonDarkTheme() {
        return radioButtonDarkTheme.isSelected();
    }
    public boolean getRadioButtonBlueTheme() {
        return radioButtonBlueTheme.isSelected();
    }
    public boolean getRadioButtonYellowTheme() {
        return radioButtonYellowTheme.isSelected();
    }
    public Button getNewGame() {
        return newGame;
    }


    @FXML
    private void initialize () throws IOException {
        ToggleGroup theme = new ToggleGroup();
        ToggleGroup gui = new ToggleGroup();
        radioButtonWhiteTheme.setToggleGroup(theme);
        radioButtonDarkTheme.setSelected(true);
        radioButtonDarkTheme.setToggleGroup(theme);
        radioButtonBlueTheme.setToggleGroup(theme);
        radioButtonYellowTheme.setToggleGroup(theme);
        radioButtonConsole.setToggleGroup(gui);
        radioButtonNumber.setToggleGroup(gui);
        radioButtonPicture.setToggleGroup(gui);
        radioButtonPicture.setSelected(true);
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
    public void menuNewGameHandler(ActionEvent event) {

    }

    @FXML
    public void loadGameHandler(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File game = fc.showOpenDialog(null);
        if(game != null) {
            game.getAbsolutePath();
        }
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
        if(image != null) {
            image.getAbsolutePath();
        }
    }

    @FXML
    public void newGameHandler(ActionEvent event) {

        /*if(getWithGUI()) {
            gameWithoutGUI = new PuzzleConsole(4,4);
            gameWithoutGUI.init();
        }*/
    }

}
