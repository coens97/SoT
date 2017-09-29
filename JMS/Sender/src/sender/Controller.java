package sender;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


public class Controller {
    private Main main;
    /* Login */
    @FXML private TextField textInput;
    @FXML private ListView listView;

    @FXML
    private void okClicked(ActionEvent event) {

    }



    public void setMain(Main main) {
        this.main = main;
    }
}
