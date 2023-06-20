package pl.edu.uph.sb;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.edu.uph.sb.exceptions.DistributorFailureException;
import pl.edu.uph.sb.exceptions.PaperException;
import pl.edu.uph.sb.exceptions.SpendingChangeException;

import java.io.Serializable;


public abstract class Station implements Serializable {
    private StringProperty name;

    private StringProperty error;

    private BooleanProperty working;

    public Station(String name) {
        this.name = new SimpleStringProperty(name);
        this.error = new SimpleStringProperty("");
        this.working = new SimpleBooleanProperty(true);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return nameProperty().get();
    }

    public void setName(String name) {
        nameProperty().set(name);
    }

    public StringProperty errorProperty() {
        return error;
    }

    public String getError() {
        return errorProperty().get();
    }

    public void setError(String error) {
        errorProperty().set(error);
    }

    public BooleanProperty workingProperty() {
        return working;
    }

    public Boolean getWorking() {
        return workingProperty().get();
    }

    public void setWorking(Boolean working) {
        workingProperty().set(working);
    }




    public abstract void refuel(Double amount, String type) throws PaperException, DistributorFailureException, SpendingChangeException;

}
