package pl.edu.uph.sb;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ClientPanelController {
    @FXML
    private TabPane tabPane;

    MainController mainController;


    @FXML
    public void onKeyPressed(KeyEvent event) {
        KeyCombination kc = new KeyCodeCombination(KeyCode.A, KeyCodeCombination.CONTROL_DOWN);
        if (kc.match(event)) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Panel pracownika");
            dialog.setHeaderText("Wprowadź hasło");
            dialog.setContentText("Hasło:");
            PasswordField passwordField = new PasswordField();
            dialog.getDialogPane().setContent(passwordField);
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                if (passwordField.getText().equals("admin")){
                    mainController.showEmployeePanel();
                }
            }
        }
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}