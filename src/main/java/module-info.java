module pl.edu.uph.sb {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.edu.uph.sb to javafx.fxml;
    exports pl.edu.uph.sb;
    exports pl.edu.uph.sb.exceptions;
    opens pl.edu.uph.sb.exceptions to javafx.fxml;
}