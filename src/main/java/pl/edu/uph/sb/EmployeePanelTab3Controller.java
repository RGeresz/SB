package pl.edu.uph.sb;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import pl.edu.uph.sb.exceptions.BelowZeroException;

public class EmployeePanelTab3Controller {

    @FXML
    private Button addFuelBtn;

    @FXML
    private TextField addedAmountTxt;

    @FXML
    private Label availableFuel;

    @FXML
    private Button changePriceBtn;

    @FXML
    private ToggleGroup fuelTypeGrp;

    @FXML
    private RadioButton gasRBtn;

    @FXML
    private TextField newPriceTxt;

    @FXML
    private Label priceLbl;

    @FXML
    private VBox errorVBox;

    private StationLPG station;

    private ErrorLabel error;

    @FXML
    private void initialize() {
        station = (StationLPG) GasStation.getInstance().getStation(2);
        priceLbl.textProperty().bind(station.gasPriceProperty().asString());
        error = new ErrorLabel("");
        error.getBtn().visibleProperty().bind(station.workingProperty().not());
        error.getBtn().setOnAction(e -> {
            station.setWorking(true);
            station.setError("");
        });
        error.getLbl().textProperty().bind(station.errorProperty());
        errorVBox.getChildren().add(error);
        availableFuel.textProperty().bind(station.gasAmountProperty().asString());
    }


    @FXML
    void addFuel(ActionEvent event) {
        try {
            Double amount = Double.parseDouble(addedAmountTxt.getText());
            if(gasRBtn.isSelected()) {
                station.addGas(amount);
            }
        } catch (NumberFormatException e) {
            new ErrorDialog("Niepoprawna wartość").showAndWait();
        } catch (BelowZeroException e) {
            new ErrorDialog(e.getMessage()).showAndWait();
        }

    }


    @FXML
    void changePrice(ActionEvent event) {
        try {
            if (gasRBtn.isSelected()) {
                station.setGasPrice(Double.parseDouble(newPriceTxt.getText()));
            }
        } catch (NumberFormatException e) {
            new ErrorDialog("Niepoprawna wartość").showAndWait();
        } catch (BelowZeroException e) {
            new ErrorDialog(e.getMessage()).showAndWait();
        }

    }

    @FXML
    public void fuelTypeChange(ActionEvent event) {
        if (gasRBtn.isSelected()) {
            priceLbl.textProperty().bind(station.gasPriceProperty().asString());
            availableFuel.textProperty().bind(station.gasAmountProperty().asString());
        }
    }

}
