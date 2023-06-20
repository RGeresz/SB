package pl.edu.uph.sb;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import pl.edu.uph.sb.exceptions.BelowZeroException;
import pl.edu.uph.sb.exceptions.DistributorFailureException;
import pl.edu.uph.sb.exceptions.PaperException;
import pl.edu.uph.sb.exceptions.SpendingChangeException;

import java.io.Serializable;
import java.util.Random;

public class StationGasAndDiesel extends Station implements Serializable {
    private DoubleProperty dieselAmount;
    private DoubleProperty dieselPrice;
    private DoubleProperty gasAmount;
    private DoubleProperty gasPrice;

    public StationGasAndDiesel(String name, Double dieselAmount, Double dieselPrice, Double gasAmount, Double gasPrice) {
        super(name);
        this.dieselAmount = new SimpleDoubleProperty(dieselAmount);
        this.dieselPrice = new SimpleDoubleProperty(dieselPrice);
        this.gasAmount = new SimpleDoubleProperty(gasAmount);
        this.gasPrice = new SimpleDoubleProperty(gasPrice);
    }

    @Override
    public void refuel(Double amount, String type) throws PaperException, DistributorFailureException, SpendingChangeException {
        Random random = new Random();
        Integer randomInt = random.nextInt(1000000);
        if (randomInt % 5 == 0) {
            throw new DistributorFailureException("Awaria dystrybutora");
        }
        if (type.equals("Diesel")) {
            setDieselAmount(getDieselAmount() - amount);
        }
        if (type.equals("Benzyna")) {
            setGasAmount(getGasAmount() - amount);
        }
    }

    public void setGasAmount(double amount) throws DistributorFailureException {
        if(amount < 0) {
            throw new DistributorFailureException("Brak paliwa");
        }
        gasAmountProperty().set(amount);
    }

    public void setDieselAmount(double amount) throws DistributorFailureException {
        if (amount < 0) {
            throw new DistributorFailureException("Brak paliwa");
        }
        dieselAmountProperty().set(amount);
    }

    public DoubleProperty dieselAmountProperty() {
        return dieselAmount;
    }

    public Double getDieselAmount() {
        return dieselAmountProperty().get();
    }

    public void addDiesel(Double dieselAmount) throws BelowZeroException {
        if(dieselAmount < 0) {
            throw new BelowZeroException("Ilość nie może być ujemna");
        }
        dieselAmountProperty().set(getDieselAmount() + dieselAmount);
    }

    public DoubleProperty dieselPriceProperty() {
        return dieselPrice;
    }

    public Double getDieselPrice() {
        return dieselPriceProperty().get();
    }

    public void setDieselPrice(Double dieselPrice) throws BelowZeroException {
        if(dieselPrice < 0) {
            throw new BelowZeroException("Cena nie może być ujemna");
        }
        dieselPriceProperty().set(dieselPrice);
    }

    public DoubleProperty gasAmountProperty() {
        return gasAmount;
    }

    public Double getGasAmount() {
        return gasAmountProperty().get();
    }

    public void addGas(Double gasAmount) throws BelowZeroException {
        if (gasAmount < 0) {
            throw new BelowZeroException("Ilość nie może być ujemna");
        }
        gasAmountProperty().set(getGasAmount() + gasAmount);
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

    public String toString() {
        return getName() + " " + getDieselAmount() + " " + getDieselPrice() + " " + getGasAmount() + " " + getGasPrice();
    }
}
