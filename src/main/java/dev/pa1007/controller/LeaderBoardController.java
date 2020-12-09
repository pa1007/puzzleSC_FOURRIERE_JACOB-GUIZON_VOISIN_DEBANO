package dev.pa1007.controller;

import dev.pa1007.utils.ConnectionSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeaderBoardController {

    @FXML
    private Text score6;
    @FXML
    private Text score7;
    @FXML
    private Text score8;
    @FXML
    private Text score9;
    @FXML
    private Text score2;
    @FXML
    private Text score3;
    @FXML
    private Text score4;
    @FXML
    private Text score5;
    @FXML
    private Text number3;
    @FXML
    private Text number4;
    @FXML
    private Text number1;
    @FXML
    private Text number2;
    @FXML
    private Text number7;
    @FXML
    private Text number8;
    @FXML
    private Text number5;
    @FXML
    private Text number6;
    @FXML
    private Text number9;
    @FXML
    private Text name10;
    @FXML
    private Text score10;
    @FXML
    private Text number10;
    @FXML
    private Text name6;
    @FXML
    private Text name5;
    @FXML
    private Text name4;
    @FXML
    private Text name3;
    @FXML
    private Text name9;
    @FXML
    private Text name8;
    @FXML
    private Text name7;
    @FXML
    private Text score1;
    @FXML
    private Text name2;
    @FXML
    private Text name1;

    private List<Text> scores;
    private List<Text> names;


    public void init() {
        try {
            refreshLB();
        }
        catch (SQLException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Il y a une erreur avec la Base de donnée");
            a.setContentText(e.getMessage());
            a.setContentText("Cela n'a pas fonctionné");
            a.show();
            e.printStackTrace();
        }
    }

    public void mouseCLickText(MouseEvent mouseEvent) {

    }

    public void refreshHandler(ActionEvent actionEvent) {
        try {
            refreshLB();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void closeHandler(ActionEvent actionEvent) {

    }

    private void refreshLB() throws SQLException {
        refreshVar();
        int                 i          = 0;
        ConnectionSingleton instance   = ConnectionSingleton.getInstance();
        Connection          connection = instance.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(
                "SELECT * FROM LeaderBoard ORDER by score DESC LIMIT 10 ");
        while (resultSet.next()) {
            names.get(i).setText(resultSet.getString("Name"));
            scores.get(i).setText(resultSet.getString("score"));
            i++;
        }
    }

    private void refreshVar() {
        scores = new ArrayList<>();
        scores.add(score1);
        scores.add(score2);
        scores.add(score3);
        scores.add(score4);
        scores.add(score5);
        scores.add(score6);
        scores.add(score7);
        scores.add(score8);
        scores.add(score9);
        scores.add(score10);
        names = new ArrayList<>();
        names.add(name1);
        names.add(name2);
        names.add(name3);
        names.add(name4);
        names.add(name5);
        names.add(name6);
        names.add(name7);
        names.add(name8);
        names.add(name9);
        names.add(name10);
        scores.forEach((text -> text.setText("")));
        names.forEach((text -> text.setText("")));
    }
}
