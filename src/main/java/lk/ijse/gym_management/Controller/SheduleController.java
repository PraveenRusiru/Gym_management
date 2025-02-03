package lk.ijse.gym_management.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import lk.ijse.gym_management.BO.BOFactory;
import lk.ijse.gym_management.BO.Impl.ClientBoImpl;
import lk.ijse.gym_management.BO.Impl.SheduleBoImpl;
import lk.ijse.gym_management.DAO.Impl.SheduleDaoImpl;
import lk.ijse.gym_management.DTO.SheduleDto;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SheduleController implements Initializable {
    public static int count=-1;
    private SheduleDto sheduleDto;
    private SheduleBoImpl sheduleBo=(SheduleBoImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.SHEDULE);
    @FXML
    private ComboBox<Integer> comboBoxCount;

    @FXML
    private JFXButton doneBtn;

    @FXML
    private Label endDateLbl;

    @FXML
    private JFXTextArea goalArea;

    @FXML
    private Label issueDateLbl;

    @FXML
    private Label scheduleIdLbl;

    @FXML
    private Pane sheduleDetailsPane;

    @FXML
    void comboBoxSelected(ActionEvent event) {
        count=Integer.parseInt(String.valueOf(comboBoxCount.getValue()));
    }

    @FXML
    void doneBtnClick(ActionEvent event) throws SQLException, IOException {
        if(!goalArea.getText().isEmpty()){
            sheduleDto=new SheduleDto(scheduleIdLbl.getText(),new ClientBoImpl().getNextId(), Date.valueOf(issueDateLbl.getText()), Date.valueOf(endDateLbl.getText()),goalArea.getText());
           sheduleBo.setEntity(sheduleDto);
           sheduleDetailsPane.getChildren().clear();
           sheduleDetailsPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Shedule/SheduleMaking.fxml")));
            //commonMethod.loadFrame(sheduleDetailsPane,"/view/Shedule/SheduleMaking.fxml");
        }else{
            new Alert(Alert.AlertType.ERROR,"Goal Text are is Empty").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            scheduleIdLbl.setText(sheduleBo.getNextId());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
//            new Alert(Alert.AlertType.ERROR,"Fail to update customer...!").show();
        }

        issueDateLbl.setText(String.valueOf(LocalDate.now()));
        endDateLbl.setText(String.valueOf(LocalDate.now().plusWeeks(6)));
        ObservableList<Integer> countList= FXCollections.observableArrayList(1,2,3,4,5,6,7);
        comboBoxCount.setItems(countList);
    }
}
