package lk.ijse.gym_management.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lk.ijse.gym_management.BO.BOFactory;
import lk.ijse.gym_management.BO.Impl.InventoryBOImpl;
import lk.ijse.gym_management.BO.Impl.SupplimentBOImpl;
import lk.ijse.gym_management.DTO.InventoryDto;
import lk.ijse.gym_management.DTO.SupplimentDto;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupplimentController implements Initializable {

    private FXMLLoader fxmlLoader;
    private SupplimentRowController supplimentRowController;
    ArrayList<SupplimentDto> suppliementDtos;
    ArrayList<InventoryDto> inventoryDtos;
    SupplimentBOImpl supplimentBO= (SupplimentBOImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.SUPPLIMENT);
    InventoryBOImpl inventoryBO=(InventoryBOImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.INVENTORY);
    static   ArrayList<SupplimentDto> itemDtos=new ArrayList<>();
   int start=0;
    int end=0;
    @FXML
    private ImageView FADown;

    @FXML
    private ImageView FADown1;

    @FXML
    private ImageView FADown2;

    @FXML
    private ImageView FAProgress;

    @FXML
    private ImageView FAProgress1;

    @FXML
    private ImageView FAProgress2;

    @FXML
    private JFXButton checkoutBtn;

    @FXML
    private Label clientCountPercentageLbl1;

    @FXML
    private Label clientCountPercentageLbl2;

    @FXML
    private Label lbl1;

    @FXML
    private Label lbl2;

    @FXML
    private Label lbl3;

    @FXML
    private Pane mainPaneSuppliments;

    @FXML
    private JFXButton newSupplimentBtn;

    @FXML
    private ImageView nextBtn;

    @FXML
    private ImageView previousBtn;

    @FXML
    private JFXButton reportBtn;

    @FXML
    private VBox tableVBox;

    @FXML
    void getReport(ActionEvent event) {

    }

    @FXML
    void loadNextRow(MouseEvent event) {
        previousBtn.setVisible(true);
        start=end;
        end=end+10>suppliementDtos.size()?suppliementDtos.size():end+10;
        loadTable();
    }

    @FXML
    void loadPreviousRows(MouseEvent event) {
        end=start;
        start=start-10<0?0:start-10;
        loadTable();
    }

    @FXML
    void navigateToCart(ActionEvent event) {
        System.out.println("ItemDtos :"+itemDtos.size());
        try {
            mainPaneSuppliments.getChildren().clear();
            mainPaneSuppliments.getChildren().add(FXMLLoader.load(getClass().getResource("/View/Suppliment/Cart.fxml")));
            //commonMethod.loadFrame(mainPaneSuppliments,"/view/Suppliment/cart.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        checkoutBtn.setVisible(false);
    }

    @FXML
    void navigateToNewSuppliment(ActionEvent event) {
        try {
            newSupplimentBtn.setVisible(false);
            mainPaneSuppliments.getChildren().clear();
            mainPaneSuppliments.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Suppliment/AddNewSuppliment.fxml")));
            //commonMethod.loadFrame(mainPaneSuppliments,"/view/Suppliment/AddNewSuppliment.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (CartController.isFromCartController){
            reportBtn.setVisible(true);
        }else{
            reportBtn.setVisible(false);
        }
        previousBtn.setVisible(false);
        checkoutBtn.setVisible(false);
        try {
            suppliementDtos=supplimentBO.getAll();
            inventoryDtos=inventoryBO.getAll();
            end=suppliementDtos.size()<10?suppliementDtos.size():10;
            loadTable();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void loadTable(){

        try {
            suppliementDtos=supplimentBO.getAll();
            inventoryDtos=inventoryBO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tableVBox.getChildren().clear();
        for (int i = start; i < end; i++) {
            fxmlLoader=new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/View/Suppliment/SupplimentRow.fxml"));

            try {
                HBox hBox=(HBox) fxmlLoader.load();
                supplimentRowController=fxmlLoader.getController();
                supplimentRowController.setDataRow(suppliementDtos.get(i),inventoryDtos.get(i));
                supplimentRowController.setParentComponent(tableVBox,mainPaneSuppliments,checkoutBtn);
                tableVBox.getChildren().add(hBox);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }

        }

    }
}
