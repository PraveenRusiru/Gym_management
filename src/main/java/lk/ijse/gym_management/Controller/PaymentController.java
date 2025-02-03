package lk.ijse.gym_management.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import lk.ijse.gym_management.BO.BOFactory;
import lk.ijse.gym_management.BO.Impl.MembershipBoImpl;
import lk.ijse.gym_management.BO.Impl.NoteForClientBOImpl;
import lk.ijse.gym_management.BO.Impl.PaymentBoImpl;
import lk.ijse.gym_management.BO.Impl.SheduleBoImpl;
import lk.ijse.gym_management.DTO.PaymentDto;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    PaymentDto paymentDto;
    PaymentBoImpl paymentBoImpl=(PaymentBoImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.PAYMENT);
    @FXML
    private Label dateLbl;

    @FXML
    private JFXButton finishMembershipAdd;

    @FXML
    private Pane nutrationSheduleMakingPane;

    @FXML
    private JFXButton paymentBtn;

    @FXML
    private Label paymentIdLbl;

    @FXML
    private Label service1Lbl;

    @FXML
    private Label service1PriceLbl;

    @FXML
    private Label service2Lbl;

    @FXML
    private Label service2PriceLbl;

    @FXML
    private Label totalAmountLbl;

    @FXML
    void finishMembershipAddAction(ActionEvent event) throws SQLException {
        if(MembershipRowController.isFromMembershipRowController){
           // paymentDto=new PaymentDto(paymentIdLbl.getText(),"Paid", Date.valueOf(dateLbl.getText()),Double.parseDouble(totalAmountLbl.getText()));
            paymentDto=new PaymentDto(paymentIdLbl.getText(),new MembershipBoImpl().getNextId(),new SheduleBoImpl().getNextId(),Double.parseDouble(totalAmountLbl.getText()),Date.valueOf(LocalDate.now()),"Paid");
            if(Double.parseDouble(totalAmountLbl.getText()) > 0){
                try {
                    paymentBoImpl.setEntity(paymentDto);
                    boolean isTransActionDone= paymentBoImpl.save();
                    if(isTransActionDone){
                        new Alert(Alert.AlertType.INFORMATION,"Payment successfully saved").show();
                        MembershipRowController.isFromMembershipRowController=false;
                        //commonMethod.loadFrame();
                    }else{
                        new Alert(Alert.AlertType.ERROR,"Payment failed").show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }else{
                new Alert(Alert.AlertType.ERROR,"Payment not saved").show();
            }
        }else{
            paymentDto=new PaymentDto(paymentIdLbl.getText(),new MembershipBoImpl().getNextId(),new SheduleBoImpl().getNextId(),Double.parseDouble(totalAmountLbl.getText()),Date.valueOf(LocalDate.now()),"Paid");
            if(Double.parseDouble(totalAmountLbl.getText()) > 0){
                try {
                   paymentBoImpl.setEntity(paymentDto);
                    if(paymentBoImpl.save()){
                        new Alert(Alert.AlertType.INFORMATION,"Payment successfully saved").show();
                        AddNewMembershipController.isFromAddNewMembership=false;

                    }else{
                        new Alert(Alert.AlertType.ERROR,"Payment failed").show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }else{
                new Alert(Alert.AlertType.ERROR,"Payment not saved").show();
            }
        }

    }

    @FXML
    void paymentBtnAction(ActionEvent event) throws SQLException {
       // paymentDto = new PaymentDto(paymentIdLbl.getText(),"Paid", Date.valueOf(dateLbl.getText()),Double.parseDouble(totalAmountLbl.getText()));
        paymentDto=new PaymentDto(paymentIdLbl.getText(),new MembershipBoImpl().getNextId(),new SheduleBoImpl().getNextId(),Double.parseDouble(totalAmountLbl.getText()),Date.valueOf(LocalDate.now()),"Paid");
        paymentBoImpl.setEntity(paymentDto);
        if(Double.parseDouble(totalAmountLbl.getText()) > 0){

               if (paymentBoImpl.save()){
                   new Alert(Alert.AlertType.INFORMATION,"Payment successfully saved").show();
               };

        }else{
            new Alert(Alert.AlertType.ERROR,"Payment not saved").show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateLbl.setText(LocalDate.now().toString());
        if(AddNewMembershipController.isFromAddNewMembership || MembershipRowController.isFromMembershipRowController){
            paymentBtn.setVisible(false);
            finishMembershipAdd.setVisible(true);
        }else{
            finishMembershipAdd.setVisible(false);
            paymentBtn.setVisible(true);
        }

        try {
            paymentIdLbl.setText(paymentBoImpl.getNextId());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        service1Lbl.setText(ServiceClientController.txt);

        service2Lbl.setText(ServiceClientController.shedules);

        service1PriceLbl.setText(String.valueOf(ServiceClientController.packagePrice));

        try {
            service2PriceLbl.setText(new NoteForClientBOImpl().getEntity()==null?"0.00":"13000.00");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        if(ServiceClientController.shedules.equals("")) {
            totalAmountLbl.setText(String.valueOf(ServiceClientController.packagePrice));
        }else{
            totalAmountLbl.setText(String.valueOf(ServiceClientController.packagePrice+13000));
        }
        ServiceClientController.packagePrice=0;
        ServiceClientController.shedules="";
        ServiceClientController.txt="";
        //totalAmountLbl.setText(String.valueOf(ServiceClientController.packagePrice+(ServiceClientController.shedules.equals(""))?0:13_000));
    }
}
