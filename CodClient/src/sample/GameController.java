package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import soap.cod.CodSoap;
import soap.cod.MyCodSoapService;
import soap.cod.ScoreBoardResult;

import java.util.List;
import java.util.Observable;

public class GameController {
    private String username;
    private String password;

    @FXML private Label labelName;
    @FXML private Label labelRollResult;
    @FXML private TableView scoreTable;

    public void initialize(String user, String pass){
        username = user;
        password = pass;
        labelName.setText(user);
        showScoreBoard();
    }

    @FXML
    private void rollDiceClicked(ActionEvent event) {
        CodSoap server = new MyCodSoapService().getMyCodSoapPort();
        boolean won = server.rollDice(username, password);
        labelRollResult.setText(won ? "Won the dice roll :)" : "Lost the dice roll");
        showScoreBoard();
    }

    @FXML
    private void showScoreBoardClicked(ActionEvent event) {
        showScoreBoard();
    }

    private void showScoreBoard() {
        CodSoap server = new MyCodSoapService().getMyCodSoapPort();
        List<ScoreBoardResult> scores = server.getScoreBoard();
        ObservableList<ScoreBoardResult> tableDate = FXCollections.observableArrayList(scores);
        scoreTable.setItems(tableDate);
    }
}
