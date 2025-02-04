package lk.ijse.gym_management.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {


    @FXML
    private Pane BackgroundLoginPane;

    @FXML
    private ImageView backgroundView;

    @FXML
    private AnchorPane mainAnchorPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BackgroundLoginPane.getChildren().clear();
        try {
            BackgroundLoginPane.getChildren().add(FXMLLoader.load(getClass().getResource("/View/Login/LoginPane.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
