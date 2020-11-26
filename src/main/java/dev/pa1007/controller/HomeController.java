package dev.pa1007.controller;

import dev.pa1007.game.PuzzleConsole;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
