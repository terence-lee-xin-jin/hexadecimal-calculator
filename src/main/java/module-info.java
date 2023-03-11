module com.terence.translateapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.terence.hexadecimalcalculator to javafx.fxml;
    exports com.terence.hexadecimalcalculator;
    exports com.terence.hexadecimalcalculator.controllers;
    opens com.terence.hexadecimalcalculator.controllers to javafx.fxml;
}