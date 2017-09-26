package sample;

import com.coen.Dto.StandardResult;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        WebTarget target = TargetSingle.getInstance().getTarget()
                .path("users").path(name).path(pass);
        Invocation.Builder requestBuilder = target.request().accept(MediaType.APPLICATION_JSON);
        Response response = requestBuilder.get();
        StandardResult result = response.readEntity(StandardResult.class);
        if (response.getStatus() == Response.Status.OK.getStatusCode())
        {
            if (result.isSucces())
            {
                main.loginSucceeded(name, pass);
            }
            else
            {
                loginWarningText.setText(result.getMessage());
            }
        }
        else
        {
            loginWarningText.setText("Couldn't make request to server");
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
        WebTarget target = TargetSingle.getInstance().getTarget()
                .path("users").path(registerName.getText()).path(registerPassword.getText());
        Invocation.Builder requestBuilder = target.request().accept(MediaType.APPLICATION_JSON);
        Response response = requestBuilder.post(null);
        StandardResult result = response.readEntity(StandardResult.class);
        if (response.getStatus() == Response.Status.OK.getStatusCode())
        {
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
        else
        {
            registerWarning.setText("Couldn't make request to server");
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
