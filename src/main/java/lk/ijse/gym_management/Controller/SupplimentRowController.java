package lk.ijse.gym_management.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import lk.ijse.gym_management.BO.BOFactory;
import lk.ijse.gym_management.BO.Impl.InventoryBOImpl;
import lk.ijse.gym_management.BO.Impl.SupplimentBOImpl;
import lk.ijse.gym_management.DTO.InventoryDto;
import lk.ijse.gym_management.DTO.SupplimentDto;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SupplimentRowController implements Initializable {

    SupplimentDto suppliementDto;
    SupplimentBOImpl supplimentBO= (SupplimentBOImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.SUPPLIMENT);
    InventoryBOImpl inventoryBO=(InventoryBOImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.INVENTORY);
    static boolean isFromSupplimentRowController=false;
    InventoryDto inventoryDto;

    @FXML
    private Rectangle addToCartRectangle;

    @FXML
    private ImageView addtoCartBtn;

    @FXML
    private Label dateLbl;

    @FXML
    private ImageView deleteBtn;

    @FXML
    private Label descriptionLbl;

    @FXML
    private ImageView detailUpdateBtn;

    @FXML
    private HBox hBoxContainer;

    @FXML
    private Label inventoryIdLbl;

    @FXML
    private Label priceLbl;

    @FXML
    private Label qtyLbl;

    @FXML
    private Label supIdLbl;

    @FXML
    private Label supTitleLbl;

    @FXML
    private Label supplierLbl;

    @FXML
    private Label typeLbl;

    @FXML
    private Label wholeSaleLbl;

    private VBox tableVBox;

    private Pane mainPane;

    private JFXButton checkoutBtn;

    @FXML
    void addtoCartOnAction(MouseEvent event) {
        SupplimentDto itemDto=new SupplimentDto();
        itemDto.setSupplimentId(supIdLbl.getText());
        itemDto.setSupplimentTitle(supTitleLbl.getText());
        itemDto.setSupplimentPrice(Double.parseDouble(priceLbl.getText()));
        SupplimentController.itemDtos.add(itemDto);
        addToCartRectangle.setVisible(false);
        addtoCartBtn.setVisible(false);
        checkoutBtn.setVisible(true);
    }

    @FXML
    void deleteAction(MouseEvent event) throws SQLException {
        if(supplimentBO.delete(supIdLbl.getText())   && tableVBox!=null){
            tableVBox.getChildren().remove(hBoxContainer);
        }
    }

    @FXML
    void detailUpdateAction(MouseEvent event) throws SQLException {
        isFromSupplimentRowController=true;

        suppliementDto=new SupplimentDto(supIdLbl.getText(),supTitleLbl.getText(),descriptionLbl.getText(),typeLbl.getText(),Double.parseDouble(priceLbl.getText()));
        supplimentBO.setEntity(suppliementDto);

        inventoryDto=new InventoryDto(inventoryIdLbl.getText(),supIdLbl.getText(),Integer.parseInt(qtyLbl.getText()),Double.parseDouble(wholeSaleLbl.getText()),Date.valueOf(dateLbl.getText()),supplierLbl.getText());
        inventoryBO.setEntity(inventoryDto);

        try {
            mainPane.getChildren().clear();
            mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("/View/Suppliment/AddNewSuppliment.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void setDataRow(SupplimentDto suppliementDto, InventoryDto inventoryDto) {
        supIdLbl.setText(suppliementDto.getSupplimentId());
        supTitleLbl.setText(suppliementDto.getSupplimentTitle());
        typeLbl.setText(suppliementDto.getSupplimentCategory());
        qtyLbl.setText(String.valueOf(inventoryDto.getStock_qty()));
        priceLbl.setText(String.valueOf(suppliementDto.getSupplimentPrice()));
        dateLbl.setText(String.valueOf(inventoryDto.getPurchase_date()));
        inventoryIdLbl.setText(String.valueOf(inventoryDto.getInventoryId()));
        supplierLbl.setText(inventoryDto.getSupplier());

    }
    public void setParentComponent(VBox tableVBox, Pane mainPane, JFXButton checkoutBtn){
        this.mainPane=mainPane;
        this.tableVBox=tableVBox;
        this.checkoutBtn=checkoutBtn;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wholeSaleLbl.setVisible(false);
        descriptionLbl.setVisible(false);
    }
}
