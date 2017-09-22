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
    private Main main;
    /* Login */
    @FXML private TextField nameField;
    @FXML private PasswordField passwordField;
    @FXML private Text loginWarningText;

    /* Register */
    @FXML private TextField registerName;
    @FXML private PasswordField registerPassword;
    @FXML private PasswordField registerPasswordAgain;
    @FXML private Text registerWarning;

    @FXML
    private void handleLoginClick(ActionEvent event) {
        loginWarningText.setText("");
        String name = nameField.getText();
        String pass = passwordField.getText();
        CodSoap server = new MyCodSoapService().getMyCodSoapPort();
        StandardResult result = server.login(name, pass);

        if (result.isSucces())
        {
            System.out.println("Login success");
            main.loginSucceeded(name, pass);
        }
        else
        {
            loginWarningText.setText(result.getMessage());
        }
    }

    @FXML
    private void handleRegisterClick(ActionEvent event) {
        registerWarning.setText("");
        if (!registerPassword.getText().equals(registerPasswordAgain.getText()))
        {
            registerWarning.setText("Passwords are not the same");
            return;
        }
        CodSoap server = new MyCodSoapService().getMyCodSoapPort();
        StandardResult result = server.register(registerName.getText(), registerPassword.getText());

        if (result.isSucces())
        {
            registerWarning.setText("Succesfully registered");
            registerName.setText("");
            registerPassword.setText("");
            registerPasswordAgain.setText("");
        }
        else
        {
            registerWarning.setText(result.getMessage());
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
