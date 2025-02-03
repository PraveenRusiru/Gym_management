package lk.ijse.gym_management.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class DashboardController {
    @FXML
    private Pane BackgroundPane;

    @FXML
    private AreaChart<?, ?> areaChart;

    @FXML
    private ImageView clientBtn;

    @FXML
    private Label enddateLbl;

    @FXML
    private ImageView homeBtn;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Pane mainPane;

    @FXML
    private Label memberIdLbl;

    @FXML
    private Label memberIdLbl1;

    @FXML
    private Label memberTypeLbl;

    @FXML
    private ImageView membershipBtn;

    @FXML
    private PieChart pieChart;

    @FXML
    private ImageView suppliementShopBtn;

    @FXML
    private VBox tableBox;

    @FXML
    void navigateHome(MouseEvent event) {
        try {
            mainPane.getChildren().clear();
            mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("/View/Client/MainClient.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void navigateToClients(MouseEvent event) {
        try {
            mainPane.getChildren().clear();
            mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("/View/Client/MainClient.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void navigateToMembership(MouseEvent event) {

        try {
            mainPane.getChildren().clear();
            mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("/View/Membership/MainMembership.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void supplimentOnAction(MouseEvent event) {
        try {
            mainPane.getChildren().clear();
            mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("/View/Suppliment/Suppliment.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}
