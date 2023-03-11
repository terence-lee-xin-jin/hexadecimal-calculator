package com.terence.hexadecimalcalculator.controllers;


import com.terence.hexadecimalcalculator.models.HexadecimalCalculator;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;


/**
 * @author Terence Lee
 *
 * A controller class for the hexadecimal-calculator-view.fxml file
 * */
public class HexadecimalCalculatorController {

    @FXML
    private TextField userInputFirstHexadecimalNumberTextField;

    @FXML
    private TextField userInputSecondHexadecimalNumberTextField;

    @FXML
    private TextField hexadecimalResultReadOnlyTextField;

    @FXML
    private TextField firstDecimalNumberReadOnlyTextField;

    @FXML
    private TextField secondDecimalNumberReadOnlyTextField;

    @FXML
    private TextField decimalResultReadyOnlyTextField;

    @FXML
    private Button calculateResultButton;

    @FXML
    private Label mathOperationLabel;

    @FXML
    private ComboBox<String> mathOperationComboBox;

    private String divideQuotientHexadecimalResult;

    private String divideRemainderHexadecimalResult;


    /**
     * Initialize all the views in the app
     * */
    public void initialize(){
        initializeMathOperationComboBox();

        initializeFirstAndSecondHexadecimalNumberTextFields();

        initializeCalculateResultButton();
    }


    /**
     * Initialize both the firstHexadecimalNumberTextField and secondHexadecimalTextField
     * such that if the changes the value of the hexadecimal, it will clear the
     * existing calculated results on the read-only textfields
     * */
    private void initializeFirstAndSecondHexadecimalNumberTextFields(){

        userInputFirstHexadecimalNumberTextField.textProperty().addListener(
                (observable, oldValue, newValue)->{
            if (!oldValue.equals(newValue)){
                clearAllReadOnlyTextFields();
            }
        });


        userInputSecondHexadecimalNumberTextField.textProperty().addListener(
                (observable, oldValue, newValue)->{
            if (!oldValue.equals(newValue)){
                clearAllReadOnlyTextFields();
            }
        });
    }


    /**
     * Initialize the math operation combobox
     *
     * Also initialize the combobox such that if the value change, clear all
     * the read-only result text fields
     * */
    private void initializeMathOperationComboBox(){
        mathOperationComboBox.getItems().add("+");
        mathOperationComboBox.getItems().add("-");
        mathOperationComboBox.getItems().add("×");
        mathOperationComboBox.getItems().add("÷");

        mathOperationComboBox.getSelectionModel().select(0);
        mathOperationComboBox.getStyleClass().add("math-operation-combo-box");

        mathOperationComboBox.valueProperty().addListener((options, oldValue, newValue)->{
            if (!oldValue.equals(newValue)){
                clearAllReadOnlyTextFields();
                updateMathOperationLabel();
            }
        });
    }


    /**
     * Update the math operation label for the decimal equivalent conversion
     * section based on the selected math operation from the math operation combobox
     * */
    private void updateMathOperationLabel(){

        String currentMathOperation =
                mathOperationComboBox.getSelectionModel().getSelectedItem();

        mathOperationLabel.setText(currentMathOperation);

    }

    /**
     * Initialize the calculate result button
     * */
    private void initializeCalculateResultButton(){

        this.calculateResultButton.setOnAction(actionEvent -> calculateAndDisplayResult());
    }



    /**
     * Calculate and display the results of the hexadecimal calculation.
     * Also display the equivalent values in decimal
     * */
    private void calculateAndDisplayResult() {

        boolean userInputIsValid = validateAllHexadecimalUserInputs();

        if (userInputIsValid){

            calculateAndDisplayHexadecimalResult();

            convertFirstHexadecimalNumberToDecimalAndDisplayValue();
            convertSecondHexadecimalNumberToDecimalAndDisplayValue();
            convertHexadecimalResultToDecimalAndDisplayValue();
        }


    }


    /**
     * Validate that all hexadecimal inputs (2 of them) are valid. If not valid,
     * display a blocking alert dialog informing user that the hexadecimal input(s)
     * are invalid
     *
     * @return if user input is valid, return true. Otherwise, return false
     * */
    private boolean validateAllHexadecimalUserInputs(){


        String firstHexadecimalNumber = userInputFirstHexadecimalNumberTextField.getText().trim();
        String secondHexadecimalNumber = userInputSecondHexadecimalNumberTextField.getText().trim();

        String errorMessage = "";

        errorMessage += validateHexadecimalNumberIsValid(firstHexadecimalNumber,
                            "first number");
        errorMessage += validateHexadecimalNumberIsValid(secondHexadecimalNumber,
                            "second number");

        errorMessage += validateSecondNumberIsNotZeroWhenDivision(secondHexadecimalNumber);


        boolean userInputIsValid = true;

        if (!errorMessage.equals("")){

            userInputIsValid = false;
            displayErrorMessageInAlertDialog(errorMessage);

        }

        return userInputIsValid;
    }


    /**
     * Validate that a particular hexadecimal number is a valid hexadecimal number
     *
     * @param hexadecimalNumber the hexadecimal number to be validated
     * @param inputName the name of the hexadecimal number input (e.g. first hexadecimal
     *                    number), to be included in the error message to identify the
     *                  hexadecimal number
     *
     * @return errorMessage if the hexadecimal number is valid, return an empty string.
     *      Otherwise, return a non-empty string explaining why the hexadecimal number
     *      is invalid
     * */
    private static String validateHexadecimalNumberIsValid(
                                String hexadecimalNumber,
                                String inputName) {

        final int HEXADECIMAL_RADIX = 16;
        String errorMessage = "";

        try{
            Long.parseLong(hexadecimalNumber, HEXADECIMAL_RADIX);

        }
        catch (NumberFormatException e){

            errorMessage = "The " + inputName + " need to contain 0-9 and A-F only, and \n" +
                    "cannot be more than 7FFFFFFFFFFFFFFF.\n\n";
        }

        return errorMessage;
    }


    /**
     * Validate that the second hexadecimal number is not zero in a division operation
     *
     * @param secondHexadecimalNumber the second hexadecimal number to be validated
     *
     *
     * @return errorMessage if the second hexadecimal number is zero and the math operation
     *                      to be carried out is division, return an error message stating that
     *                      the second number cannot be zero. Otherwise, the second hexadecimal
     *                      is considered valid and return an empty string
     * */
    private String validateSecondNumberIsNotZeroWhenDivision(String secondHexadecimalNumber)
    {
        int selectedMathOperationIndex = this.mathOperationComboBox.getSelectionModel().getSelectedIndex();
        final int DIVISION_OPERATION = 3;

        String errorMessage = "";

        if (selectedMathOperationIndex == DIVISION_OPERATION &&
            secondHexadecimalNumber.equals("0"))
        {
            errorMessage = "The second number cannot be zero in a division operation";
        }

        return errorMessage;
    }

    /**
     * Display an error message in a pop-up alert dialog
     *
     * @param errorMessage the error message to be displayed
     * */
    private void displayErrorMessageInAlertDialog(String errorMessage){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(errorMessage);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }


    /**
     * Calculate and display the hexadecimal result in the hexadecimalResultReadOnlyTextField
     * */
    private void calculateAndDisplayHexadecimalResult(){

        try{
            String firstHexadecimalNumber =
                    userInputFirstHexadecimalNumberTextField.getText().trim();
            String secondHexadecimalNumber =
                    userInputSecondHexadecimalNumberTextField.getText().trim();

            int selectedMathOperationIndex = mathOperationComboBox.getSelectionModel().getSelectedIndex();

            String hexadecimalResult = switch (selectedMathOperationIndex) {

                case 0 -> HexadecimalCalculator.add(firstHexadecimalNumber,
                        secondHexadecimalNumber).toUpperCase();

                case 1 -> HexadecimalCalculator.subtract(firstHexadecimalNumber,
                        secondHexadecimalNumber).toUpperCase();

                case 2 -> HexadecimalCalculator.multiply(firstHexadecimalNumber,
                        secondHexadecimalNumber).toUpperCase();

                case 3 -> calculateAndDisplayDivideOperationResult(firstHexadecimalNumber,
                        secondHexadecimalNumber);

                default -> null;
            };


            this.hexadecimalResultReadOnlyTextField.setText(hexadecimalResult);

        }
        catch (ArithmeticException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setContentText("The result of the calculation is too large");
        }



    }


    /**
     * Calculate and display the divide operation result (both quotient and remainder displayed)
     *
     * @param firstHexadecimalNumber the first hexadecimal number (dividend)
     * @param secondHexadecimalNumber  the second hexadecimal number (divisor)
     * */
    private String calculateAndDisplayDivideOperationResult(String firstHexadecimalNumber,
                                                          String secondHexadecimalNumber){


        this.divideQuotientHexadecimalResult = HexadecimalCalculator.divide(firstHexadecimalNumber,
                                                secondHexadecimalNumber).toUpperCase();

        this.divideRemainderHexadecimalResult = HexadecimalCalculator.modulus(firstHexadecimalNumber,
                                                   secondHexadecimalNumber).toUpperCase();

        return divideQuotientHexadecimalResult + " Remainder " +
                                        divideRemainderHexadecimalResult;
    }


    /**
     * Convert first hexadecimal number (user entered) to decimal number and
     * display the decimal equivalent
     * */
    private void convertFirstHexadecimalNumberToDecimalAndDisplayValue(){

        String firstHexadecimalNumber = this.userInputFirstHexadecimalNumberTextField
                                .getText().trim();

        long firstDecimalNumber = HexadecimalCalculator.convertHexadecimalToDecimal(firstHexadecimalNumber);
        this.firstDecimalNumberReadOnlyTextField.setText(Long.toString(firstDecimalNumber));

    }


    /**
     * Convert second hexadecimal number (user entered) to decimal number and
     * display the decimal equivalent
     * */
    private void convertSecondHexadecimalNumberToDecimalAndDisplayValue(){

        String secondHexadecimalNumber =
                this.userInputSecondHexadecimalNumberTextField.getText().trim();

        long secondDecimalNumber = HexadecimalCalculator.convertHexadecimalToDecimal(secondHexadecimalNumber);
        this.secondDecimalNumberReadOnlyTextField.setText(Long.toString(secondDecimalNumber));

    }


    /**
     * Convert the hexadecimal result to decimal and display the decimal equivalent in
     * the decimalResultReadyOnlyTextField
     * */
    private void convertHexadecimalResultToDecimalAndDisplayValue(){

        final String DIVISION_OPERATION = "÷";

        // if the selected operation is not a division operation
        // retrieve the hexadecimal result from the hexadecimalResultReadOnlyTextField and
        // convert it to decimal for display

        String selectedMathOperation = mathOperationComboBox.getSelectionModel().getSelectedItem();

        if (!selectedMathOperation.equals(DIVISION_OPERATION)) {

            String resultHexadecimalValue = this.hexadecimalResultReadOnlyTextField.getText();

            long resultDecimalValue = HexadecimalCalculator.convertHexadecimalToDecimal(resultHexadecimalValue);
            this.decimalResultReadyOnlyTextField.setText(Long.toString(resultDecimalValue));
        }
        else {
            // if this is a division operation
            // get the quotient and remainder from the two corresponding instance variable
            // and convert it to decimal before displaying it
            long quotientDecimalResult = HexadecimalCalculator.convertHexadecimalToDecimal(
                    divideQuotientHexadecimalResult);

            long remainderDecimalResult = HexadecimalCalculator.convertHexadecimalToDecimal(
                    divideRemainderHexadecimalResult);

            this.decimalResultReadyOnlyTextField.setText(quotientDecimalResult + " Remainder "
                                                        + remainderDecimalResult);

        }


    }


    /**
     * Clear out all the text on the read-only text-fields containing results from the calculations/
     * decimal-equivalent converted values
     * */
    private void clearAllReadOnlyTextFields() {

        this.hexadecimalResultReadOnlyTextField.setText("");

        this.firstDecimalNumberReadOnlyTextField.setText("");
        this.secondDecimalNumberReadOnlyTextField.setText("");
        this.decimalResultReadyOnlyTextField.setText("");
    }
}