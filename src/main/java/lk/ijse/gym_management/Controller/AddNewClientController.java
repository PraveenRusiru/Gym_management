package lk.ijse.gym_management.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import lk.ijse.gym_management.BO.BOFactory;
import lk.ijse.gym_management.BO.Impl.ClientBoImpl;
import lk.ijse.gym_management.BO.Impl.ClientContactBoImpl;
import lk.ijse.gym_management.DTO.ClientContactDto;
import lk.ijse.gym_management.DTO.ClientDto;
import lk.ijse.gym_management.Utill.ChangeValidUtill;
import lk.ijse.gym_management.Utill.ValidUtill;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddNewClientController implements Initializable {
    static String clientId="";
    static String goal="";
    ClientDto clientDto;
    ClientContactDto clientContactDto;
    ClientBoImpl clientBo= (ClientBoImpl)BOFactory.getInstance().getBOType(BOFactory.BOType.CLIENT);
    ClientContactBoImpl clientContactBo=(ClientContactBoImpl)BOFactory.getInstance().getBOType(BOFactory.BOType.CLIENTCONTACT);
    static boolean isFromAddNewMembership = false;
    @FXML
    private Pane agePane;

    @FXML
    private JFXTextField ageTxt;

    @FXML
    private Pane applicationPane;

    @FXML
    private JFXButton backClientBtn;

    @FXML
    private Pane clientDataPane;

    @FXML
    private Label clientIdLbl;

    @FXML
    private Label clientLbl;

    @FXML
    private ComboBox<String> comboBox;
    ObservableList<String> genderList= FXCollections.observableArrayList("Male","Female");

    @FXML
    private ComboBox<String> comboBoxGoal;

    ObservableList<String> goalList= FXCollections.observableArrayList("Muscle gain","Fat loss","Strength Training","Endurance Training");
    @FXML
    private JFXButton continueBtn;

    @FXML
    private Label dateLbl;

    @FXML
    private Pane fatPerPane;

    @FXML
    private JFXTextField fatPerTxt;

    @FXML
    private Pane firstNamePane;

    @FXML
    private JFXTextField firstNameTxt;

    @FXML
    private Pane heightPane;

    @FXML
    private JFXTextField heightTxt;

    @FXML
    private Pane lastNamePane;

    @FXML
    private JFXTextField lastNameTxt;

    @FXML
    private Pane mailPane;

    @FXML
    private JFXTextField mailTxt;

    @FXML
    private Pane nicPane;

    @FXML
    private JFXTextField nicTxt;

    @FXML
    private Pane numberPane;

    @FXML
    private JFXTextField numberTxt;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private Pane weightPane;

    @FXML
    private JFXTextField weightTxt;

    @FXML
    void comboBoxGoalSelected(ActionEvent event) {
        goal=comboBoxGoal.getValue();
        System.out.println(comboBoxGoal.getValue());
    }

    @FXML
    void comboBoxSelected(ActionEvent event) {
        System.out.println(comboBox.getValue());
    }

    @FXML
    void navigateToClientList(ActionEvent event) {

    }

    @FXML
    void navigateToServiceClient(ActionEvent event) throws SQLException {
        boolean isValidFirstName= ValidUtill.isValidName(firstNameTxt.getText()) ;
        boolean isValidLastName= ValidUtill.isValidName(lastNameTxt.getText());
        boolean isValidPhone=ValidUtill.isValidPhone(numberTxt.getText());
        boolean isValidEmail= ValidUtill.isValidEmail(mailTxt.getText());
        boolean isValidAge=ValidUtill.isAgeValid(ageTxt.getText());
        boolean isValidHeight=ValidUtill.isHeightValid(heightTxt.getText());
        boolean isValidWeight=ValidUtill.isWeightValid(weightTxt.getText());
        boolean isValidFatPercentage=ValidUtill.isFatPercentageValid(fatPerTxt.getText());

        boolean isValidateNic=false;
        System.out.println("fat per : "+isValidFatPercentage);
        if(Integer.parseInt(ageTxt.getText())>=16){
            if(ValidUtill.isNicValid(nicTxt.getText())){

                isValidateNic=true;
            }
        }else{
            isValidateNic=true;
        }
        if(isValidFatPercentage && isValidFirstName && isValidLastName && isValidPhone && isValidAge && isValidateNic && isValidHeight && isValidWeight && isValidEmail) {

            clientDto=new ClientDto(clientIdLbl.getText(),firstNameTxt.getText()+" "+lastNameTxt.getText(),Integer.parseInt(ageTxt.getText()),comboBox.getValue(),comboBoxGoal.getValue(),Integer.parseInt(heightTxt.getText()),Integer.parseInt(weightTxt.getText()),Integer.parseInt(fatPerTxt.getText()), Date.valueOf(LocalDate.now()));
            clientBo.setEntity(clientDto);
            clientContactDto=new ClientContactDto(nicTxt.getText(),numberTxt.getText(),mailTxt.getText(),clientIdLbl.getText());
            clientContactBo.setEntity(clientContactDto);


            try {
                applicationPane.getChildren().clear();
                applicationPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Client/ServiceClient.fxml")));
            }catch (IOException e){
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        else{
            new Alert(Alert.AlertType.ERROR,"Fail to update customer...!").show();

        }
    }

    @FXML
    void navigateToShowClient(ActionEvent event) throws SQLException, IOException {
        ClientRowController.isFromClientRowController = false;
        clientDto = new ClientDto(clientIdLbl.getText(), firstNameTxt.getText() + " " + lastNameTxt.getText(), Integer.parseInt(ageTxt.getText()), comboBox.getValue(), comboBoxGoal.getValue(), Integer.parseInt(heightTxt.getText()), Integer.parseInt(weightTxt.getText()), Integer.parseInt(fatPerTxt.getText()), Date.valueOf(dateLbl.getText()));
        clientContactDto = new ClientContactDto(nicTxt.getText(), numberTxt.getText(), mailTxt.getText(), clientIdLbl.getText());
        clientBo.setEntity(clientDto);
        clientContactBo.setEntity(clientContactDto);
        if (clientBo.update() && clientContactBo.update()) {
            //commonMethod.loadFrame(clientDataPane,"/view/Clients/Clients.fxml");
            clientDataPane.getChildren().clear();
            clientDataPane.getChildren().add(FXMLLoader.load(getClass().getResource("/View/Client/MainClient.fxml")));
        }
    }
    @FXML
    void validateAge(KeyEvent event) {
        ChangeValidUtill.changeColours(agePane,ageTxt,ValidUtill.isAgeValid(ageTxt.getText()));
    }

    @FXML
    void validateFatPer(KeyEvent event) {
        ChangeValidUtill.changeColours(fatPerPane,fatPerTxt,ValidUtill.isFatPercentageValid(fatPerTxt.getText()));
    }

    @FXML
    void validateHeight(KeyEvent event) {
        ChangeValidUtill.changeColours(heightPane,heightTxt,ValidUtill.isHeightValid(heightTxt.getText()));
    }

    @FXML
    void validateLastName(KeyEvent event) {
        ChangeValidUtill.changeColours(lastNamePane, lastNameTxt, ValidUtill.isValidName(lastNameTxt.getText()));
    }

    @FXML
    void validateMail(KeyEvent event) {
        ChangeValidUtill.changeColours(mailPane, mailTxt,ValidUtill.isValidEmail(mailTxt.getText()));
    }

    @FXML
    void validateNic(KeyEvent event) {
        ChangeValidUtill.changeColours(nicPane,nicTxt,ValidUtill.isNicValid(nicTxt.getText()));
    }

    @FXML
    void validateNumber(KeyEvent event) {
        ChangeValidUtill.changeColours(numberPane,numberTxt,ValidUtill.isValidPhone(numberTxt.getText()));
    }

    @FXML
    void validateWeight(KeyEvent event) {
        ChangeValidUtill.changeColours(weightPane,weightTxt,ValidUtill.isWeightValid(weightTxt.getText()));
    }

    @FXML
    void validationFirstName(KeyEvent event) {
        ChangeValidUtill.changeColours(firstNamePane, firstNameTxt, ValidUtill.isValidName(firstNameTxt.getText()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(ClientRowController.isFromClientRowController){
            dateLbl.setVisible(true);
            try {
                setValueForTxt();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            updateBtn.setVisible(true);
            continueBtn.setVisible(false);

        }else{
            dateLbl.setVisible(false);
            updateBtn.setVisible(false);
            continueBtn.setVisible(true);

            try {
                clientIdLbl.setText(clientBo.getNextId());
                clientId=clientIdLbl.getText();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        comboBox.setItems(genderList);
        comboBoxGoal.setItems(goalList);
    }
    public void setValueForTxt() throws SQLException {

        dateLbl.setText(String.valueOf(clientBo.getEntity().getJoined_date()));

        clientIdLbl.setText(String.valueOf(clientBo.getEntity().getClientId()));
        String name=clientBo.getEntity().getClientName();
        if(name.contains(" ")){
            String[] namePart=clientBo.getEntity().getClientName().split(" ");
            String firstName=namePart[0];
            String lastName=namePart[1];
            firstNameTxt.setText(firstName);
            lastNameTxt.setText(lastName);
        }else{
            firstNameTxt.setText(name);
        }
        ageTxt.setText(String.valueOf(clientBo.getEntity().getAge()));
        nicTxt.setText(clientContactBo.getEntity().getNic());
        numberTxt.setText(clientContactBo.getEntity().getPhone());
        mailTxt.setText(clientContactBo.getEntity().getEmail());
        comboBox.setValue(clientBo.getEntity().getGender());
        heightTxt.setText(String.valueOf(clientBo.getEntity().getHeight()));
        weightTxt.setText(String.valueOf(clientBo.getEntity().getWeight()));
        fatPerTxt.setText(String.valueOf(clientBo.getEntity().getFat_per()));
        comboBoxGoal.setValue(clientBo.getEntity().getGoal());
    }
}
