package pl.edu.uph.sb;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class EmployeePanelController {
    MainController mainController;

    @FXML
    private void initialize() {

    }
    public void onKeyPressed(KeyEvent event) {
        KeyCombination kc = new KeyCodeCombination(KeyCode.A, KeyCodeCombination.CONTROL_DOWN);
        if (kc.match(event)) {
            mainController.showClientPanel();
        }
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

}
