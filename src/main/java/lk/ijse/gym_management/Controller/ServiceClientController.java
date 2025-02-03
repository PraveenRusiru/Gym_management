package lk.ijse.gym_management.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
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
import lk.ijse.gym_management.BO.Impl.NoteForClientBOImpl;
import lk.ijse.gym_management.BO.NoteForClientBO;
import lk.ijse.gym_management.DTO.MembershipDto;
import lk.ijse.gym_management.DTO.NoteForClientDto;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ServiceClientController implements Initializable {
    private NoteForClientDto noteForClientDto;
    MembershipBoImpl membershipBo=(MembershipBoImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.MEMBERSHIP);
    NoteForClientBOImpl noteForClientBO=(NoteForClientBOImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.NOTEFORCLIENT);
    private MembershipDto membershipDto;
    static String txt="";
    static double packagePrice=0;
    static String shedules="";
    @FXML
    private JFXButton createPrgrmBtn;

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
    private JFXTextArea noteAre;

    @FXML
    private JFXButton paymentBtn;

    @FXML
    private Pane servicePane;

    @FXML
    private RadioButton strrengthRB;

    @FXML
    void membershipOnAction(ActionEvent event) throws SQLException {
        int monthCount=0;
        if(introFitRB.isSelected()) {
            txt=introFitRB.getText();
            monthCount=3;
            packagePrice=3000;
        }else if(fitFocusRB.isSelected()) {
            txt=fitFocusRB.getText();
            monthCount=6;
            packagePrice=10000;
        }else if(strrengthRB.isSelected()) {
            txt=strrengthRB.getText();
            monthCount=12;
            packagePrice=20000;
        } else if (flexFitRB.isSelected()) {
            txt=flexFitRB.getText();
            monthCount=1;
            packagePrice=1000;
        }
        System.out.println("Selected selection :"+txt);
        membershipDto=new MembershipDto(memberIdLabel.getText(),new ClientBoImpl().getNextId(),Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.now().plusMonths(monthCount)),txt);
    }

    @FXML
    void navigateToShedule(ActionEvent event) throws SQLException {
        if(noteAre.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Note Ares is Empty").show();
        }else{
            noteForClientDto=new NoteForClientDto(noteForClientBO.getNextId(), new ClientBoImpl().getNextId(), noteAre.getText());
            if(noteForClientDto!=null){
                noteForClientBO.setEntity(noteForClientDto);
            }if(membershipDto!=null){
                membershipBo.setEntity(membershipDto);
            }
            shedules="Workout & Nutration Plan";//this is crucial
            servicePane.getChildren().clear();
            try {
               servicePane.getChildren().add(FXMLLoader.load(getClass().getResource("/View/Shedule/Shedule.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            //commonMethod.loadFrame(servicePane,"");
        }
    }

    @FXML
    void paymentOnAction(ActionEvent event) throws SQLException {
        membershipBo.setEntity(membershipDto);
        servicePane.getChildren().clear();
        try {
              servicePane.getChildren().add(FXMLLoader.load(getClass().getResource("/View/Payment/Payment.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            memberIdLabel.setText(membershipBo.getNextId());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
