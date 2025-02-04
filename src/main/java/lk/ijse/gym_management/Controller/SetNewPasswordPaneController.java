package lk.ijse.gym_management.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import lk.ijse.gym_management.BO.BOFactory;
import lk.ijse.gym_management.BO.Impl.AdminsBOImpl;
import lk.ijse.gym_management.Utill.TextFieldManager;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SetNewPasswordPaneController implements Initializable {

    String firstPw="";
    String secondPw="";
    TextFieldManager textFieldManager=new TextFieldManager();
    AdminsBOImpl adminsBO=(AdminsBOImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.ADMIN);

    @FXML
    private JFXButton backToLoginBtn;

    @FXML
    private Pane changePasswordPane;

    @FXML
    private JFXTextField enterAgainPassword;

    @FXML
    private JFXTextField newPasswordTxt;

    @FXML
    void enterAgainPasswordKeyPressed(KeyEvent event) {

    }

    @FXML
    void navigateToLogin(ActionEvent event) throws SQLException, IOException {
        secondPw=enterAgainPassword.getText();
        firstPw=newPasswordTxt.getText();
        System.out.println("firstPw "+firstPw+" secondPw "+secondPw);
        if(firstPw.equals(secondPw)) {
            boolean isUpdated=adminsBO.isPasswordUpdated(firstPw);
            if(isUpdated) {
                changePasswordPane.getChildren().clear();
                changePasswordPane.getChildren().add(FXMLLoader.load(getClass().getResource("/View/Login/LoginPane.fxml")));
                //commonMethod.loadFrame(changePasswordPane,"/view/Login/LoginPane.fxml");
            }
        }
    }

    @FXML
    void newPasswordKeyboardType(KeyEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textFieldManager.setupFieldforJtoJTxt(newPasswordTxt,enterAgainPassword);
    }
}
