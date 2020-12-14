package dev.pa1007.controller;

import dev.pa1007.utils.ConnectionSingleton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;


public class WinController {

    @FXML
    private Image  imageYouWon;
    @FXML
    private Button closeTheGame;

    private String  time;
    private int     nbCoups;
    private boolean done;

    public void setInfos(String time, int nb) {
        this.time = time;
        this.nbCoups = nb;
    }


    @FXML
    void closeTheGameHandler(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void leaderBoardAdd(ActionEvent event) {
        if (done) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Vous ne pouvez pas re envoyer vos scores");
            a.show();
        }
        else {
            TextInputDialog dialog = new TextInputDialog("Player");
            dialog.setTitle("Entrez votre nom");
            dialog.setHeaderText("Entrez votre nom pour ajouter au leaderboard");
            dialog.setContentText("Entrez votre nom ici :");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(s -> {
                Connection connection = ConnectionSingleton.getInstance().getConnection();
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            "INSERT INTO LeaderBoard(Name,date,score,time)values (?,current_date,?,?);");
                    preparedStatement.setString(1, s);
                    preparedStatement.setInt(2, nbCoups);
                    preparedStatement.setString(3, time);
                    preparedStatement.execute();
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                    a.setContentText("Vous avez ete ajoute au classement");
                    a.show();
                    done = true;
                }
                catch (SQLException throwables) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText(throwables.getMessage());
                    a.show();
                }
            });
        }
    }
}
