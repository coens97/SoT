package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import soap.cod.CodSoap;
import soap.cod.MyCodSoapService;

public class GameController {
    private String username;
    private String password;

    @FXML private Label labelName;
    @FXML private Label labelRollResult;

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

    }
}
