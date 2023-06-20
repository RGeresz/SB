package pl.edu.uph.sb;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class MainController {
    @FXML
    private TabPane employeePanel;
    @FXML
    private TabPane clientPanel;
    @FXML
    private ClientPanelController clientPanelController;
    @FXML
    private EmployeePanelController employeePanelController;

    @FXML
    private void initialize() {
        showClientPanel();
        clientPanelController.setMainController(this);
        employeePanelController.setMainController(this);
    }

    public void showClientPanel() {
        employeePanel.setVisible(false);
        clientPanel.setVisible(true);
    }

    public void showEmployeePanel() {
        employeePanel.setVisible(true);
        clientPanel.setVisible(false);
    }
}
