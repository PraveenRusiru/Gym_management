package lk.ijse.gym_management.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lk.ijse.gym_management.BO.BOFactory;
import lk.ijse.gym_management.BO.Impl.ClientBoImpl;
import lk.ijse.gym_management.BO.Impl.ClientContactBoImpl;
import lk.ijse.gym_management.DTO.ClientContactDto;
import lk.ijse.gym_management.DTO.ClientDto;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainClientController implements Initializable {
    private   FXMLLoader fxmlLoader;
    private  ClientRowController clientRowController;
    ArrayList<ClientDto> clientDtos;
    ArrayList<ClientContactDto> clientContactDtos;
    int start=0;
    int end=0;
    ClientContactBoImpl contactBo= (ClientContactBoImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.CLIENTCONTACT);
    ClientBoImpl clientBo= (ClientBoImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.CLIENT);
    @FXML
    private ImageView FAProgress;

    @FXML
    private ImageView SADown;

    @FXML
    private ImageView TAEqual;

    @FXML
    private Label clientCountLbl;

    @FXML
    private Label clientCountPercentageLbl;

    @FXML
    private Pane mainPaneClients;

    @FXML
    private Label membershipCountLbl;

    @FXML
    private Label membershipCountPercentageLbl;

    @FXML
    private JFXButton newClientBtn;

    @FXML
    private ImageView nextBtn;

    @FXML
    private ImageView previousBtn;

    @FXML
    private Label scheduleCountLbl;

    @FXML
    private Label scheduleCountPercentageLbl;

    @FXML
    private VBox tableVBox;

    @FXML
    void loadNextRow(MouseEvent event) throws SQLException {
        previousBtn.setVisible(true);
        start=end;
        end=end+10>clientDtos.size()?clientDtos.size():end+10;
        loadTable();
    }

    @FXML
    void loadPreviousRows(MouseEvent event) throws SQLException {
        end=start;
        start=start-10<0?0:start-10;
        loadTable();
    }

    @FXML
    void navigateToNewClient(ActionEvent event) {
        try {
            newClientBtn.setVisible(false);
            mainPaneClients.getChildren().clear();
            mainPaneClients.getChildren().add(FXMLLoader.load(getClass().getResource("/View/Client/AddNewClient.fxml")));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    public  void loadTable() throws SQLException {

        try {
            clientDtos=clientBo.getAll();
            clientContactDtos=contactBo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tableVBox.getChildren().clear();
        for (int i = start; i < end; i++) {
            fxmlLoader=new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/View/Client/ClientRow.fxml"));
            try {
                HBox hBox=(HBox) fxmlLoader.load();
                clientRowController=fxmlLoader.getController();
                clientRowController.setDataRow(clientDtos.get(i),clientContactDtos.get(i));
                clientRowController.setParentComponent(tableVBox,mainPaneClients);
                tableVBox.getChildren().add(hBox);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        previousBtn.setVisible(false);
        try {
            clientDtos=clientBo.getAll();
            clientContactDtos=contactBo.getAll();
            end=clientDtos.size()<10?clientDtos.size():10;
            loadTable();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
