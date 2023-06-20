package pl.edu.uph.sb;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ErrorLabel extends HBox {

    private Label lbl;
    private Button btn;
    public ErrorLabel(String text) {
        super();
        lbl = new Label(text);
        lbl.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 20px; -fx-min-width: 200px;");
        this.getChildren().add(lbl);
        btn = new Button("Napraw");
        btn.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 20px;");
        this.getChildren().add(btn);
    }

    public Label getLbl() {
        return lbl;
    }

    public Button getBtn() {
        return btn;
    }
}
