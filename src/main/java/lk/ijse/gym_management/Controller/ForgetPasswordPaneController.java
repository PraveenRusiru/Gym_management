package lk.ijse.gym_management.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.ijse.gym_management.Utill.SendMailUtill;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class ForgetPasswordPaneController implements Initializable {

    String to="";
    String from="";
    String subject="";
    String body="";
    String code="";
    String typedCode="";

    @FXML
    private ImageView backBtn;

    @FXML
    private JFXTextField codeTxt;

    @FXML
    private JFXButton confirmBtn;

    @FXML
    private Pane forgetPasswordPane;

    @FXML
    void codeKeyPressed(KeyEvent event) {

    }

    @FXML
    void navigateLogin(MouseEvent event) throws IOException {
        forgetPasswordPane.getChildren().clear();
        forgetPasswordPane.getChildren().add(FXMLLoader.load(getClass().getResource("/View/Login/LoginPane.fxml")));
    }

    @FXML
    void navigateToNewPassword(ActionEvent event) {
        typedCode = codeTxt.getText();
        System.out.println(typedCode);
        if(code.equals(typedCode)){
            new Alert(Alert.AlertType.INFORMATION,"Password Recovered !");
            try {
                forgetPasswordPane.getChildren().clear();
                forgetPasswordPane.getChildren().add(FXMLLoader.load(getClass().getResource("/View/Login/SetNewPasswordPane.fxml")));
                //commonMethod.loadFrame(forgetPasswordPane,"/view/Login/SetNewPassword.fxml");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Entered Code doesn't match !!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SendMailUtill sendMailUtill = new SendMailUtill();
        loadMailDetails();
        sendMailUtill.sendEmailWithGmail(from,to,subject,body);
    }

    private void loadMailDetails(){
        Random rand = new Random();
        code = String.valueOf(rand.nextInt(99999));
        from="praveenrusiru752@gmail.com";
        to="praveenrusiru031@gmail.com";
        subject="Recovering Password";
        body="Your standalone application password reset code : \n\n\n\n\t\t\t\t\t\t"+code;
    }

}
