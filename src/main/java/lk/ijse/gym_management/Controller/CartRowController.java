package lk.ijse.gym_management.Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lk.ijse.gym_management.BO.BOFactory;
import lk.ijse.gym_management.BO.Impl.InventoryBOImpl;
import lk.ijse.gym_management.DTO.SupplimentDto;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CartRowController implements Initializable {

    SupplimentDto itemDto;
    InventoryBOImpl inventoryBO=(InventoryBOImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.INVENTORY);
    @FXML
    private ImageView deleteBtn;

    @FXML
    private JFXTextField finalPriceTxt;

    @FXML
    private HBox hBoxContainer;

    @FXML
    private Label priceLbl;

    @FXML
    private ComboBox<Integer> qtyComboBox;

    @FXML
    private Label supIdLbl;

    @FXML
    private Label titleLbl;

    @FXML
    private Label wholeSaleLbl;

    private Pane mainPaneOrderDetils;
    private VBox tableVBox;

    @FXML
    void deleteAction(MouseEvent event) {
        SupplimentController.itemDtos.removeIf(itemDto -> itemDto.getSupplimentId().equals(supIdLbl.getText()));
        tableVBox.getChildren().remove(hBoxContainer);
    }

    @FXML
    void qtyOnAction(ActionEvent event) {
        finalPriceTxt.setText(String.valueOf(qtyComboBox.getValue()*Double.parseDouble(priceLbl.getText())));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void setParentComponent(VBox tableVBox, Pane mainPaneOrderDetils) {
        this.mainPaneOrderDetils = mainPaneOrderDetils;
        this.tableVBox = tableVBox;
    }
    public void setDataRow(SupplimentDto itemDto) {
        this.itemDto = itemDto;
        supIdLbl.setText(itemDto.getSupplimentId());
        titleLbl.setText(itemDto.getSupplimentTitle());
        priceLbl.setText(Double.toString(itemDto.getSupplimentPrice()));
        finalPriceTxt.setText(Double.toString(itemDto.getSupplimentPrice()));

        int qty=0;
        try {
            qty=inventoryBO.howManyQtyRemained(itemDto.getSupplimentId());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        ObservableList<Integer> qtyList= FXCollections.observableArrayList();
        for (int i = 0; i < qty; i++) {
            qtyList.add(i+1);
        }
        qtyComboBox.setItems(qtyList);
    }
}
