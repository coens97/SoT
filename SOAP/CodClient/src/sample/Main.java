package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        // Show window
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Cod application");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        // Make the controller know about main
        Controller myController = (Controller) fxmlLoader.getController();
        myController.setMain(this);
    }

    public void loginSucceeded(String name, String password) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root, 600, 400));
        // Pass information to the gamecontroller
        GameController myController = (GameController) fxmlLoader.getController();
        myController.initialize(name, password);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
