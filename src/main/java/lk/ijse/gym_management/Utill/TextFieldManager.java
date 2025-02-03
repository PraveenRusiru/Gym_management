package lk.ijse.gym_management.Utill;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.gym_management.BO.Impl.ClientBoImpl;
import lk.ijse.gym_management.BO.Impl.ClientContactBoImpl;

import java.sql.SQLException;

public class TextFieldManager {
    public void setupFieldforJtoTTxt(JFXTextField currentField, TextField nextField) {
        currentField.setOnKeyPressed(event -> handleEnterKeyForJumptoAnotherTxt(event, currentField, nextField));
    }
    public void setupFieldforJtoJTxt(JFXTextField currentField, JFXTextField nextField) {
        currentField.setOnKeyPressed(event -> handleEnterKeyForJumptoAnotherTxt(event, currentField, nextField));
    }
    public void setLblAndTxt(TextField currentField, Label label) {
        currentField.setOnKeyPressed(event -> {handleEnterKey(event,currentField,label);});
    }
    //public void setupField

    public void jumpToButtonOnEnter(JFXTextField currentField, JFXButton button) {
        currentField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                button.requestFocus(); // Moves the focus to the button
                button.fire(); // Optionally "click" the button
            }
        });
    }

    private void handleEnterKeyForJumptoAnotherTxt(KeyEvent event, TextField currentField, TextField nextField) {
        if (event.getCode() == KeyCode.ENTER) {
            String value = currentField.getText();
//processValue(value); // Process the value (e.g., save to database)
            if (nextField != null) {
                nextField.requestFocus(); // Move cursor to the next TextField
            }
        }
    }

    //  private void processValue(String value) {
    //}
    private void handleEnterKey(KeyEvent event, TextField currentField, Label lbl) {
        if (event.getCode() == KeyCode.ENTER) {
            // Set the entered text as the value in the text field
            String enteredText = currentField.getText();
            currentField.setText(enteredText);
            try {
                lbl.setText(new ClientContactBoImpl().getClientId(enteredText));
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            System.out.println("Entered text: " + enteredText);
        }
    }
}
