package com.jeannius.NumericValidator;



import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.regex.Pattern;



/**
 * Created by Jlarrieux on 1/13/2016.
 */
public class NumericValidator {

    private JTextField currentTextField;
    Object currentType;
    String errorString;
    Type numericType;
    private static final String NO_TEXT = "No text";
    private static final String NOT_INT = "Not of Expected type int";
    private static final String NOT_DOUBLE = "Not of Expected type double";



    public enum Type {
        DOUBLE, INTEGER
    }



    public NumericValidator(NumericValidator.Type numericType) {
        this.numericType = numericType;
    }



    public boolean validate(JTextField textField) {
        currentTextField = textField;
        textField.setBorder(UIManager.getBorder("TextField.border"));
        boolean result = lengthValidation() && isNumericTypeInteger();
        return lengthValidation() && isNumericTypeInteger() && isNumericTypeDouble();
    }



    private boolean lengthValidation() {
        boolean result = currentTextField.getText().length() != 0;
        if (!result) errorCompilation(NO_TEXT);
        return result;
    }



    private boolean isNumericTypeInteger() {
        if (Pattern.matches("^\\d*$", currentTextField.getText())) return true;
        else{
            errorCompilation(NOT_INT);
            return false;
        }
    }


    private boolean isNumericTypeDouble(){
        if (Pattern.matches("^\\d*\\.?\\d*", currentTextField.getText())) return true;
        else{
            errorCompilation(NOT_INT);
            return false;
        }
    }


    private void errorCompilation(String text) {
        errorString += "\n" + text;
        Border border = BorderFactory.createLineBorder(Color.red, 2);
        currentTextField.setBorder(border);
    }





}
