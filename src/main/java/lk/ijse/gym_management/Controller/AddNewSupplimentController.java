package lk.ijse.gym_management.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import lk.ijse.gym_management.BO.BOFactory;
import lk.ijse.gym_management.BO.Impl.InventoryBOImpl;
import lk.ijse.gym_management.BO.Impl.SupplimentBOImpl;
import lk.ijse.gym_management.DTO.InventoryDto;
import lk.ijse.gym_management.DTO.SupplimentDto;
import lk.ijse.gym_management.Utill.ChangeValidUtill;
import lk.ijse.gym_management.Utill.ValidUtill;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AddNewSupplimentController implements Initializable {

    private InventoryDto inventoryDto;
    private SupplimentDto suppliementDto;
    SupplimentBOImpl supplimentBO=(SupplimentBOImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.SUPPLIMENT);
    InventoryBOImpl inventoryBO=(InventoryBOImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.INVENTORY);

    @FXML
    private JFXButton addBtn;

    @FXML
    private Pane applicationPane;

    @FXML
    private JFXButton backSuplliemnttBtn;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private Pane categoryPane;

    @FXML
    private Pane clientDataPane;

    @FXML
    private Label clientLbl;

    @FXML
    private Label dateLbl;

    @FXML
    private Pane datePane;

    @FXML
    private DatePicker datePicker;

    @FXML
    private JFXTextArea descriptionArea;

    @FXML
    private Pane inventoryIdPane;

    @FXML
    private JFXTextField inventoryIdTxt;

    @FXML
    private JFXTextField nameTxt;

    @FXML
    private Pane pricePane;

    @FXML
    private JFXTextField priceTxt;

    @FXML
    private JFXTextField qtyTxt;

    @FXML
    private Pane stockQtyPane;

    @FXML
    private Label suppIdLbl;

    @FXML
    private Pane supplierPane;

    @FXML
    private JFXTextField supplierTxt;

    @FXML
    private Pane supplimentPane;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private Pane wholesalePricePane;

    @FXML
    private JFXTextField wholesalePriceTxt;

    @FXML
    void backToSuppliemnt(ActionEvent event) throws SQLException {
        SupplimentRowController.isFromSupplimentRowController=false;
        suppliementDto=new SupplimentDto(suppIdLbl.getText(),nameTxt.getText(),descriptionArea.getText(),categoryComboBox.getValue().toString(),Double.parseDouble(priceTxt.getText()));
        inventoryDto=new InventoryDto(inventoryIdTxt.getText(),suppIdLbl.getText(),Integer.parseInt(qtyTxt.getText()),Double.parseDouble(wholesalePriceTxt.getText()), Date.valueOf(datePicker.getValue()),supplierTxt.getText());
            supplimentBO.setEntity(suppliementDto);
            inventoryBO.setEntity(inventoryDto);
            if(supplimentBO.update()  &&  inventoryBO.update()){
            try {
                clientDataPane.getChildren().clear();
                clientDataPane.getChildren().add(FXMLLoader.load(getClass().getResource("/View/Suppliment/Suppliment.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    void categorycomboBoxSelected(ActionEvent event) {

    }

    @FXML
    void navigateToAddNewSuppliment(ActionEvent event) throws SQLException {
        boolean isValidInventoryId= ValidUtill.isStringValid(inventoryIdTxt.getText());
        boolean isPriceValid=ValidUtill.isPriceValid(priceTxt.getText());
        boolean isQtyValid=ValidUtill.isIntegerValid(qtyTxt.getText());
        boolean isSupplierValid=ValidUtill.isStringValid(supplierTxt.getText());
        boolean isWholesalePriceValid=ValidUtill.isPriceValid(wholesalePriceTxt.getText());
        if(isValidInventoryId && isPriceValid && isQtyValid && isSupplierValid && isWholesalePriceValid){
            inventoryDto=new InventoryDto(inventoryIdTxt.getText(),suppIdLbl.getText(),Integer.parseInt(qtyTxt.getText()),Double.parseDouble(wholesalePriceTxt.getText()),Date.valueOf(datePicker.getValue()),supplierTxt.getText());
            inventoryBO.setEntity(inventoryDto);
            suppliementDto=new SupplimentDto(suppIdLbl.getText(),nameTxt.getText(),descriptionArea.getText(),categoryComboBox.getValue().toString(),Double.parseDouble(priceTxt.getText()));
            supplimentBO.setEntity(suppliementDto);

            try {
//                boolean isSaved=supplimentModel.saveSupplimentAndInventoyDetail();
                boolean isSupplimentSaved=supplimentBO.save();
                boolean isInventorySaved=inventoryBO.save();
                if(isSupplimentSaved && isInventorySaved){
                    clearTxt();
                    new Alert(Alert.AlertType.INFORMATION,"Suppliment Saved").show();
                    clientDataPane.getChildren().clear();
                    clientDataPane.getChildren().add(FXMLLoader.load(getClass().getResource("/View/Suppliment/Suppliment.fxml")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());

            }

        }
    }

    @FXML
    void navigateToSuppList(ActionEvent event) {
        try {
            clientDataPane.getChildren().clear();
            clientDataPane.getChildren().add(FXMLLoader.load(getClass().getResource("/View/Suppliment/Suppliment.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void pickdate(ActionEvent event) {

    }

    @FXML
    void validateInventoryId(KeyEvent event) {
        ChangeValidUtill.changeColours(inventoryIdPane,inventoryIdTxt, ValidUtill.isStringValid(inventoryIdTxt.getText()));
    }

    @FXML
    void validatePrice(KeyEvent event) {
        ChangeValidUtill.changeColours(pricePane,priceTxt, ValidUtill.isPriceValid(priceTxt.getText()));
    }

    @FXML
    void validateQty(KeyEvent event) {
        ChangeValidUtill.changeColours(stockQtyPane,qtyTxt,ValidUtill.isIntegerValid(qtyTxt.getText()));
    }

    @FXML
    void validateSupplier(KeyEvent event) {
        ChangeValidUtill.changeColours(supplierPane,supplierTxt, ValidUtill.isStringValid(supplierTxt.getText()));
    }

    @FXML
    void validateWholesalePrice(ActionEvent event) {
        ChangeValidUtill.changeColours(wholesalePricePane,wholesalePriceTxt, ValidUtill.isPriceValid(wholesalePriceTxt.getText()));
    }

    public void setDatePickerFormat(DatePicker datePicker, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        datePicker.setConverter(new StringConverter<>() {
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return formatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, formatter);
                } else {
                    return null;
                }
            }
        });

        datePicker.setPromptText(format);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> categoryList= FXCollections.observableArrayList("Amino Acids","Creatine","Vitamins","Energy","Collagens","Fat Burners","Mass Gainers");
        if(SupplimentRowController.isFromSupplimentRowController){
            updateBtn.setVisible(true);
            addBtn.setVisible(false);
            try {
                setValueForText();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }else{
            try {
                inventoryIdTxt.setText(inventoryBO.getNextId());
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            try {
                suppIdLbl.setText(supplimentBO.getNextId());
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            updateBtn.setVisible(false);

        }

        dateLbl.setVisible(false);
        categoryComboBox.setItems(categoryList);
        setDatePickerFormat(datePicker,"dd/MM/yyyy");
    }

    public void clearTxt(){
        try {
            suppIdLbl.setText(supplimentBO.getNextId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        nameTxt.clear();
        descriptionArea.clear();
        priceTxt.clear();
        qtyTxt.clear();
        supplierTxt.clear();
        wholesalePriceTxt.clear();
        try {
            inventoryIdTxt.setText(inventoryBO.getNextId());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        categoryComboBox.getSelectionModel().clearSelection();
        datePicker.getEditor().clear();
    }
    public void setValueForText() throws SQLException {
        SupplimentDto dto=supplimentBO.getEntity();
        InventoryDto dtoInventory=inventoryBO.getEntity();
        suppIdLbl.setText(dto.getSupplimentId());
        nameTxt.setText(dto.getSupplimentTitle());
        categoryComboBox.setValue(dto.getSupplimentCategory());
        qtyTxt.setText(String.valueOf(dtoInventory.getStock_qty()));
        inventoryIdTxt.setText(String.valueOf(dtoInventory.getInventoryId()));
        supplierTxt.setText(dtoInventory.getSupplier());
        priceTxt.setText(String.valueOf(dto.getSupplimentPrice()));
        wholesalePriceTxt.setText(String.valueOf(dtoInventory.getWholesale_price()));
        datePicker.setValue(dtoInventory.getPurchase_date().toLocalDate());
        descriptionArea.setText(dto.getSupplimentDescription());

    }
}
