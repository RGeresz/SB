package pl.edu.uph.sb;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Stacja benzynowa");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        GasStation.saveToFile();
    }

    public static void main(String[] args) {
        launch();
    }
}