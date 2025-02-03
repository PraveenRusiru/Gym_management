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
import lk.ijse.gym_management.BO.Impl.MembershipBoImpl;
import lk.ijse.gym_management.DTO.MembershipDto;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class MembershipController implements Initializable {

    ArrayList<MembershipDto> membershipDtos;
    int start=0;
    int end=0;
    private FXMLLoader fxmlLoader;
    private MembershipRowController membershipRowController;
    MembershipBoImpl membershipBo= (MembershipBoImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.MEMBERSHIP);

    @FXML
    private ImageView FADown;

    @FXML
    private ImageView FAProgress;

    @FXML
    private ImageView FAProgress1;

    @FXML
    private ImageView FAProgress2;

    @FXML
    private Label clientCountPercentageLbl;

    @FXML
    private Label clientCountPercentageLbl2;

    @FXML
    private Label lbl1;

    @FXML
    private Label lbl2;

    @FXML
    private Label lbl3;

    @FXML
    private Pane mainPaneMembership;

    @FXML
    private JFXButton newMembershipBtn;

    @FXML
    private ImageView nextBtn;

    @FXML
    private ImageView previousBtn;

    @FXML
    private VBox tableVBox;

    @FXML
    void loadNextRow(MouseEvent event) {
        previousBtn.setVisible(true);
        start=end;
        end=end+10>membershipDtos.size()?membershipDtos.size():end+10;
    }

    @FXML
    void loadPreviousRows(MouseEvent event) {
        end=start;
        start=start-10<0?0:start-10;
    }

    @FXML
    void navigateToNewMembership(ActionEvent event) {
        mainPaneMembership.getChildren().clear();
        try {
            mainPaneMembership.getChildren().add(FXMLLoader.load(getClass().getResource("/View/Membership/AddNewMembership.fxml")));
            //commonMethod.loadFrame(mainPaneMembership,"/view/Membership/AddNewMembership.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        previousBtn.setVisible(false);

        try {
            membershipDtos=membershipBo.getAll();
            end=membershipDtos.size()<10?membershipDtos.size():10;
            loadTable();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    public void loadTable(){
        tableVBox.getChildren().clear();
        for (int i = start; i < end; i++) {
            fxmlLoader=new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/Membership/MembershipRow.fxml"));

            try {
                HBox hBox=(HBox)fxmlLoader.load();
                membershipRowController=fxmlLoader.getController();
                membershipRowController.setData(membershipDtos.get(i));
                membershipRowController.setComponent(tableVBox,mainPaneMembership);
                tableVBox.getChildren().add(hBox);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }
}
