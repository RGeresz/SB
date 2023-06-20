package pl.edu.uph.sb;

import javafx.scene.control.Alert;

public class ErrorDialog extends Alert {
    public ErrorDialog(String text) {
        super(AlertType.ERROR);
        this.setTitle("Błąd");
        this.setHeaderText("Wystąpił błąd");
        this.setContentText(text);
    }
}
