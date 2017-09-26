package sample;

import com.coen.Dto.ScoreBoardResult;
import com.coen.Dto.ScoreBoardResults;
import com.coen.Dto.StandardResult;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import sun.rmi.runtime.Log;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Observable;

public class GameController {
    private String username;
    private String password;
    private Form login;

    @FXML private Label labelName;
    @FXML private Label labelRollResult;
    @FXML private TableView scoreTable;
    @FXML private TableColumn<ScoreBoardResult, String> userColumn;
    @FXML private TableColumn<ScoreBoardResult, String>  winColumn;
    @FXML private TableColumn<ScoreBoardResult, String>  lossColumn;

    public void initialize(String user, String pass){
        // Set values
        username = user;
        password = pass;
        login = new Form();
        login.param("name", username);
        login.param("password", password);

        labelName.setText(user);
        // Configure table
        userColumn.setCellValueFactory(x -> new SimpleStringProperty(x.getValue().getName()));
        winColumn.setCellValueFactory(x -> new SimpleStringProperty(String.valueOf(x.getValue().getWins())));
        lossColumn.setCellValueFactory(x -> new SimpleStringProperty(String.valueOf(x.getValue().getLoss())));
        showScoreBoard();
    }

    @FXML
    private void rollDiceClicked(ActionEvent event) {
        WebTarget target = TargetSingle.getInstance().getTarget()
                .path("game").path("roll");
        Invocation.Builder requestBuilder = target.request().accept(MediaType.APPLICATION_JSON);
        Response response = requestBuilder.put(Entity.entity(login, MediaType.APPLICATION_FORM_URLENCODED));
        StandardResult result = response.readEntity(StandardResult.class);
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            labelRollResult.setText(result.isSucces() ? "Won the dice roll :)" : "Lost the dice roll");
            showScoreBoard();
        }
        else
        {
            labelRollResult.setText("Couldn't connect to server");
        }
    }

    @FXML
    private void showScoreBoardClicked(ActionEvent event) {
        showScoreBoard();
    }

    private void showScoreBoard() {
        WebTarget target = TargetSingle.getInstance().getTarget()
                .path("scoreboard");
        Invocation.Builder requestBuilder = target.request().accept(MediaType.APPLICATION_JSON);
        Response response = requestBuilder.get();
        ScoreBoardResults result = response.readEntity(ScoreBoardResults.class);
        ObservableList<ScoreBoardResult> tableDate = FXCollections.observableArrayList(result.getResults());
        scoreTable.setItems(tableDate);
    }
}
