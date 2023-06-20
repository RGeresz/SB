package pl.edu.uph.sb;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import pl.edu.uph.sb.exceptions.BelowZeroException;
import pl.edu.uph.sb.exceptions.DistributorFailureException;
import pl.edu.uph.sb.exceptions.PaperException;
import pl.edu.uph.sb.exceptions.SpendingChangeException;

import java.util.Optional;
import java.util.Random;

public class ClientPanelTab3Controller {

    @FXML
    private TextField amountTxt;
    @FXML
    private ToggleGroup fuelTypeGrp;
    @FXML
    private RadioButton gasRBtn;
    @FXML
    private Label priceLbl;
    @FXML
    private Label toPayLbl;

    private StationLPG station;




    @FXML
    private void initialize() {
        station = (StationLPG) GasStation.getInstance().getStation(2);
        priceLbl.textProperty().bind(station.gasPriceProperty().asString());
    }

    @FXML
    public void amountTyped(KeyEvent event) {
        try {
            Double amount = Double.parseDouble(amountTxt.getText());
            if (amount < 0) {
                throw new BelowZeroException("Wartość nie może być ujemna");
            }
            if(gasRBtn.isSelected()) {
                toPayLbl.setText(String.format("%.2f", amount * station.getGasPrice()));
            }
        } catch (NumberFormatException e) {
            toPayLbl.setText("Niepoprawna wartość");
        } catch (BelowZeroException e) {
            toPayLbl.setText(e.getMessage());
        }
    }

    @FXML
    public void fuelTypeChange(ActionEvent event) {
        if (gasRBtn.isSelected()) {
            priceLbl.textProperty().bind(station.gasPriceProperty().asString());
        }
    }

    @FXML
    public void refuel(ActionEvent event) {
        if(!station.getWorking()) {
            new ErrorDialog("Stanowisko nieczynne").showAndWait();
            return;
        }
        try {
            if (Double.parseDouble(amountTxt.getText()) > station.getGasAmount()) {
                throw new DistributorFailureException("Za mało paliwa");
            }
            pay();
            station.refuel(Double.parseDouble(amountTxt.getText()), getFuelType());
        } catch (PaperException | DistributorFailureException | SpendingChangeException e) {
            new ErrorDialog(e.getMessage()).showAndWait();
            station.setError(e.getMessage());
            station.setWorking(false);
        } catch (NumberFormatException e) {
            new ErrorDialog("Podaj poprawną wartość paliwa").showAndWait();
        }
    }



    private void pay() throws SpendingChangeException, PaperException {
        Double toPay = Double.parseDouble(amountTxt.getText()) * station.getGasPrice();
        var dialog = new TextInputDialog();
        dialog.setTitle("Zapłać");
        dialog.setHeaderText("Zapłać");
        dialog.setContentText("Podaj kwotę");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            Double payed = Double.parseDouble(result.get());
            if (payed < toPay) {
                station.setError("Za mała kwota");
                pay();
            }
            showRecipt(payed, toPay);
        }
    }

    private void showRecipt(Double payed, Double toPay) throws PaperException, SpendingChangeException {
        Random random = new Random();
        Integer number = random.nextInt(1000000);
        if (number % 5 == 0) {
            station.setError("Brak papieru w drukarce");
            station.setWorking(false);
            throw new PaperException("Brak papieru w drukarce");
        }
        number = random.nextInt(1000000);
        if (number % 5 == 0) {
            station.setError("Brak drobnych");
            station.setWorking(false);
            throw new SpendingChangeException("Brak drobnych");
        }
        var dialog = new Dialog<>();
        dialog.setTitle("Paragon");
        dialog.setHeaderText("Paragon");
        dialog.setContentText(getFuelType() + "\t" + amountTxt.getText() + " x " + getFuelPrice() + "\n" +
                "Do zapłaty: " + String.format("%.2f", toPay) + "\n" +
                "Zapłacono: " + payed + "\n" +
                "Reszta: " + String.format("%.2f", payed - toPay));
        var btn = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(btn);
        dialog.showAndWait();
    }

    private String getFuelType() {
        return gasRBtn.getText();
    }
    private String getFuelPrice() {
        return station.getGasPrice().toString();
    }

}

