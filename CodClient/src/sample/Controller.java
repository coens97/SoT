package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import soap.cod.CodSoap;
import soap.cod.MyCodSoapService;
import soap.cod.StandardResult;

public class Controller {
    @FXML private TextField nameField;
    @FXML private PasswordField passwordField;
    @FXML private Text loginWarningText;

    @FXML
    private void handleLoginClick(ActionEvent event) {
        loginWarningText.setText("");
        CodSoap server = new MyCodSoapService().getMyCodSoapPort();
        StandardResult result = server.login(nameField.getText(), passwordField.getText());

        if (result.isSucces())
        {
            System.out.println("Login succes");
        }
        else
        {
            loginWarningText.setText(result.getMessage());
        }
    }
}
