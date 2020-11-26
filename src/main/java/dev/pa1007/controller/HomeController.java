package dev.pa1007.controller;

import dev.pa1007.game.PuzzleConsole;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;


public class HomeController {

    private PuzzleConsole gameWithoutGUI;

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

    public boolean getRadioButtonConsole() {
        return radioButtonConsole.isSelected();
    }

    @FXML private void initialize () {
        ToggleGroup theme = new ToggleGroup();
        ToggleGroup gui = new ToggleGroup();
        radioButtonWhiteTheme.setToggleGroup(theme);
        radioButtonWhiteTheme.setSelected(true);
        radioButtonDarkTheme.setToggleGroup(theme);
        radioButtonBlueTheme.setToggleGroup(theme);
        radioButtonYellowTheme.setToggleGroup(theme);
        radioButtonConsole.setToggleGroup(gui);
        radioButtonNumber.setToggleGroup(gui);
        radioButtonPicture.setToggleGroup(gui);
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
    public void newGameHandler(ActionEvent event) throws IOException {
        String stringSize = gridSize.getText();
        if(!stringSize.isEmpty()) {
            int intSize = Integer.parseInt(stringSize);
        }
        //System.out.println(getWithGUI());
        //System.out.println(getWithImage());

        /*if(getWithGUI()) {
            gameWithoutGUI = new PuzzleConsole(4,4);
            gameWithoutGUI.init();
        }*/
    }

}
