package lk.ijse.gym_management.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lk.ijse.gym_management.BO.BOFactory;
import lk.ijse.gym_management.BO.Impl.ClientBoImpl;
import lk.ijse.gym_management.BO.Impl.MembershipBoImpl;
import lk.ijse.gym_management.DTO.ClientDto;
import lk.ijse.gym_management.DTO.MembershipDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class MembershipRowController {
    MembershipDto membershipDto;
    ClientDto clientDto;
   public static boolean isFromMembershipRowController=false;
    MembershipBoImpl membershipBo= (MembershipBoImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.MEMBERSHIP);
    ClientBoImpl clientBo= (ClientBoImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.CLIENT);

    @FXML
    private Label clientIdLbl;

    @FXML
    private ImageView deleteBtn;

    @FXML
    private Label endDateLbl;

    @FXML
    private HBox hBoxContainer;

    @FXML
    private Label membershipIdLbl;

    @FXML
    private Label startdateIdLbl;

    @FXML
    private Label typeLbl;

    @FXML
    private ImageView updateBtn;

    @FXML
    private Label wholeSaleLbl;

    private VBox tableVBox;

    private Pane mainPane;

    @FXML
    void deleteAction(MouseEvent event) throws SQLException {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this row?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            if(membershipBo.delete(membershipIdLbl.getText())){
                tableVBox.getChildren().remove(hBoxContainer);
            }
        }
    }

    @FXML
    void updateAction(MouseEvent event) throws SQLException {
        isFromMembershipRowController=true;

        membershipDto=new MembershipDto();
        membershipDto.setMemberId(membershipIdLbl.getText());
        membershipBo.setEntity(membershipDto);

        clientDto=new ClientDto();
        clientDto.setClientId(clientIdLbl.getText());
        clientBo.setEntity(clientDto);

        try {
           mainPane.getChildren().clear();
           mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Membership/AddNewMembership.fxml")));
            //commonMethod.loadFrame(mainPane,"/view/Membership/AddNewMembership.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    public void setData(MembershipDto membershipDto){
        membershipIdLbl.setText(membershipDto.getMemberId());
        clientIdLbl.setText(membershipDto.getClientId());
        startdateIdLbl.setText(String.valueOf(membershipDto.getStartDate()));
        endDateLbl.setText(membershipDto.getEndDate().toString());
        typeLbl.setText(membershipDto.getMembershipType());
    }
    public void setComponent(VBox vBox, Pane pane){
        this.tableVBox = vBox;
        this.mainPane = pane;
    }
}
