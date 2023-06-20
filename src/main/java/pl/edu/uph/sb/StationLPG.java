package pl.edu.uph.sb;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import pl.edu.uph.sb.exceptions.BelowZeroException;
import pl.edu.uph.sb.exceptions.DistributorFailureException;
import pl.edu.uph.sb.exceptions.PaperException;
import pl.edu.uph.sb.exceptions.SpendingChangeException;

import java.io.Serializable;

public class StationLPG extends Station implements Serializable {

    private DoubleProperty gasPrice;
    private DoubleProperty gasAmount;

    public StationLPG(String name, Double gasAmount, Double gasPrice) {
        super(name);
        this.gasAmount = new SimpleDoubleProperty(gasAmount);
        this.gasPrice = new SimpleDoubleProperty(gasPrice);
    }

    @Override
    public void refuel(Double amount, String type) throws PaperException, DistributorFailureException, SpendingChangeException {
        if (type.equals("Gaz")) {
            setGasAmount(getGasAmount() - amount);
        }
    }

    public DoubleProperty gasPriceProperty() {
        return gasPrice;
    }

    public Double getGasPrice() {
        return gasPriceProperty().get();
    }

    public void setGasPrice(Double gasPrice) throws BelowZeroException {
        if(gasPrice < 0) {
            throw new BelowZeroException("Cena nie może być ujemna");
        }
        gasPriceProperty().set(gasPrice);
    }

    public DoubleProperty gasAmountProperty() {
        return gasAmount;
    }

    public Double getGasAmount() {
        return gasAmountProperty().get();
    }

    public void addGas(Double gasAmount) throws BelowZeroException {
        if(gasAmount < 0) {
            throw new BelowZeroException("Ilość nie może być ujemna");
        }
        gasAmountProperty().set(getGasAmount() + gasAmount);
    }

    public void setGasAmount(Double gasAmount) throws DistributorFailureException {
        if(gasAmount < 0) {
            throw new DistributorFailureException("Brak paliwa");
        }
        gasAmountProperty().set(gasAmount);
    }

    public String toString() {
        return getName() + " " + getGasAmount() + " " + getGasPrice();
    }


}
