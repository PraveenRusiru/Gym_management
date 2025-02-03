package lk.ijse.gym_management.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import lk.ijse.gym_management.BO.BOFactory;
import lk.ijse.gym_management.BO.Impl.ClientBoImpl;
import lk.ijse.gym_management.BO.Impl.MembershipBoImpl;
import lk.ijse.gym_management.DTO.ClientDto;
import lk.ijse.gym_management.DTO.MembershipDto;
import lk.ijse.gym_management.Utill.TextFieldManager;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddNewMembershipController implements Initializable {
  public   static boolean isFromAddNewMembership = false;
    TextFieldManager tfm = new TextFieldManager();
     ClientDto clientDto;
    MembershipDto membershipDto;
    MembershipBoImpl membershipBo=(MembershipBoImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.MEMBERSHIP);
    ClientBoImpl clientBo=(ClientBoImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.CLIENT);
public AddNewMembershipController(){
    clientDto=new ClientDto();
}
    @FXML
    private JFXButton addBtn;

    @FXML
    private Pane addMembershipPane;

    @FXML
    private Pane applicationPane;

    @FXML
    private JFXButton backMembershipBtn;

    @FXML
    private Label clientIdLbl;

    @FXML
    private Label clientLbl;

    @FXML
    private JFXButton doneBtn;

    @FXML
    private RadioButton fitFocusRB;

    @FXML
    private RadioButton flexFitRB;

    @FXML
    private RadioButton introFitRB;

    @FXML
    private Label memberIdLabel;

    @FXML
    private ToggleGroup membershipType;

    @FXML
    private JFXTextField nicTxt;

    @FXML
    private RadioButton strrengthRB;

    @FXML
    private JFXButton updateBtn;

    @FXML
    void backToMembership(ActionEvent event) throws SQLException {
        if(!ServiceClientController.txt.equals("")){
            membershipBo.setEntity(membershipDto);
            clientBo.setEntity(clientDto);
            applicationPane.getChildren().clear();
            try {
                //commonMethod.loadFrame(applicationPane,"/view/Payment/Payment.fxml");
                applicationPane.getChildren().add(FXMLLoader.load(getClass().getResource("/View/Payment/Payment.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Please select a client & choose a membership type").show();
        }
    }

    @FXML
    void membershipOnAction(ActionEvent event) throws SQLException {
        if(!clientIdLbl.getText().equals(" ")){
            int monthCount=0;
            if(introFitRB.isSelected()) {
                ServiceClientController.txt=introFitRB.getText();
                monthCount=3;
                ServiceClientController.packagePrice=3000;
            }else if(fitFocusRB.isSelected()) {
                ServiceClientController.txt=fitFocusRB.getText();
                monthCount=6;
                ServiceClientController.packagePrice=10000;
            }else if(strrengthRB.isSelected()) {
                ServiceClientController.txt=strrengthRB.getText();
                monthCount=12;
                ServiceClientController.packagePrice=20000;
            } else if (flexFitRB.isSelected()) {
                ServiceClientController.txt=flexFitRB.getText();
                monthCount=1;
                ServiceClientController.packagePrice=1000;
            }
            // System.out.println(clientContactModel.getClientContactDto().getClientId());
            System.out.println("Selected selection :"+ServiceClientController.txt);
          //  membershipDto=new MembershipDto(memberIdLabel.getText(), Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.now().plusMonths(monthCount)),ServiceClientController.txt);
            membershipDto=new MembershipDto(memberIdLabel.getText(),clientIdLbl.getText(),Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.now().plusMonths(monthCount)),ServiceClientController.txt);
            clientDto.setClientId(clientIdLbl.getText());
        }else{
            new Alert(Alert.AlertType.ERROR,"Please select a client ").show();
        }
    }

    @FXML
    void navigateToMembershipList(ActionEvent event) {

    }

    @FXML
    void navigateToPayment(ActionEvent event) throws SQLException {
        if(!clientIdLbl.getText().equals(" ") && !ServiceClientController.txt.equals("")) {
            //membershipModel.setMembershipDto(membershipDto);
            membershipBo.setEntity(membershipDto);
            clientBo.setEntity(clientDto);
            applicationPane.getChildren().clear();
            try {
                //commonMethod.loadFrame(applicationPane,"/view/Payment/Payment.fxml");
                applicationPane.getChildren().add(FXMLLoader.load(getClass().getResource("/View/Payment/Payment.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Please select a client & choose a membership type").show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isFromAddNewMembership = true;
        if(MembershipRowController.isFromMembershipRowController){
            nicTxt.setVisible(false);
            updateBtn.setVisible(true);
            addBtn.setVisible(false);

            try {
                memberIdLabel.setText(membershipBo.getEntity().getMemberId());
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            //clientIdLbl.setText(clientModel.getClientDto().getClientId());
            try {
                clientIdLbl.setText(clientBo.getEntity().getClientId());
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }

        }else{
            tfm.setLblAndTxt(nicTxt,clientIdLbl);
            updateBtn.setVisible(false);
            //nicTxt.setOnKeyPressed(this::);
            try {
                memberIdLabel.setText(membershipBo.getNextId());
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }

    }
}


