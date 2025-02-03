package lk.ijse.gym_management.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lk.ijse.gym_management.BO.BOFactory;
import lk.ijse.gym_management.BO.Impl.ExerciseBoImpl;
import lk.ijse.gym_management.DTO.ExercisesInSheduleDto;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SheduleRowController implements Initializable {
    ExerciseBoImpl exerciseBo=(ExerciseBoImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.EXERCISE);
    @FXML
    private ImageView deleteBtn;

    @FXML
    private Label priorityLbl;

    @FXML
    private Label repLbl;

    @FXML
    private Label restLbl;

    @FXML
    private HBox rowBarHbox;

    @FXML
    private Label setLbl;

    @FXML
    private Label tempoLbl;

    @FXML
    private ImageView updateBtn;

    @FXML
    private ImageView updateDoneBtn;

    @FXML
    private Label weightLbl;

    @FXML
    private Label workoutLbl;

    private VBox parentContainer;
    private ComboBox<Integer> dayCombox;
    private JFXTextField priorityNumTxt;
    private ComboBox<String> targetMuscleComboBox;
    private JFXTextField weightTxt;
    private ComboBox<String> workoutComboBox;
    private ComboBox<Integer> setComboBox;
    private ComboBox<Integer> repComboBox;
    private ComboBox<Integer> tempoComboBox;
    private ComboBox<Integer> restComboBox;
    private JFXButton dayCompletedBtn;

    public void setDataForRow(ExercisesInSheduleDto exercisesInSheduleDto) throws SQLException {
        priorityLbl.setText(String.valueOf(exercisesInSheduleDto.getPriorityNum()));
        repLbl.setText(String.valueOf(exercisesInSheduleDto.getReps()));
        restLbl.setText(String.valueOf(exercisesInSheduleDto.getRest()));
        setLbl.setText(String.valueOf(exercisesInSheduleDto.getSets()));
        tempoLbl.setText(String.valueOf(exercisesInSheduleDto.getTempo()));
        weightLbl.setText(String.valueOf(exercisesInSheduleDto.getWeight()));
        workoutLbl.setText(String.valueOf(exerciseBo.getExerciseDescription(exercisesInSheduleDto.getExerciseId())));
    }


    @FXML
    void deleteAction(MouseEvent event) {
        if (parentContainer != null) {
            parentContainer.getChildren().remove(rowBarHbox);
        }else{
            System.out.println("Parent container is null");
        }
    }

    @FXML
    void selectionClick(MouseEvent event) {
        Label priorityLabel = (Label)rowBarHbox.lookup("#priorityLbl");
        Label tempoLabel = (Label)rowBarHbox.lookup("#tempoLbl");
        Label weightLabel = (Label)rowBarHbox.lookup("#weightLbl");
        Label workoutLabel = (Label)rowBarHbox.lookup("#workoutLbl");
        Label setLabel = (Label)rowBarHbox.lookup("#setLbl");
        Label repLabel = (Label)rowBarHbox.lookup("#repLbl");
        Label restLabel = (Label)rowBarHbox.lookup("#restLbl");
        System.out.println(priorityLabel.getText()+workoutLabel.getText()+setLabel.getText()+repLabel.getText()+tempoLabel.getText()+restLabel.getText());
       // sheduleRowDto=new SheduleRowDto(Integer.parseInt(priorityLabel.getText()),workoutLabel.getText(),Integer.parseInt(weightLabel.getText()),Integer.parseInt(setLabel.getText()),Integer.parseInt(repLabel.getText()),Integer.parseInt(tempoLabel.getText()),Integer.parseInt(restLabel.getText()));
    }

    @FXML
    void updateAction(MouseEvent event) {
        priorityNumTxt.setText(priorityLbl.getText());
        weightTxt.setText(weightLbl.getText());
        workoutComboBox.setValue(workoutLbl.getText());
        setComboBox.setValue(Integer.parseInt(setLbl.getText()));
        repComboBox.setValue(Integer.parseInt(repLbl.getText()));
        tempoComboBox.setValue(Integer.parseInt(tempoLbl.getText()));
        restComboBox.setValue(Integer.parseInt(restLbl.getText()));

        updateDoneBtn.setVisible(true);
        updateBtn.setVisible(false);
    }

    @FXML
    void updateDoneAction(MouseEvent event) {
        priorityLbl.setText(priorityNumTxt.getText());
        weightLbl.setText(weightTxt.getText());
        workoutLbl.setText(workoutComboBox.getValue());
        setLbl.setText(String.valueOf(setComboBox.getValue()));
        repLbl.setText(String.valueOf(repComboBox.getValue()));
        tempoLbl.setText(String.valueOf(tempoComboBox.getValue()));
        restLbl.setText(String.valueOf(restComboBox.getValue()));
        updateDoneBtn.setVisible(false);
        updateBtn.setVisible(true);
        clearAllComboBox();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateDoneBtn.setVisible(false);
    }
    public void clearAllComboBox() {
        priorityNumTxt.clear();
        priorityNumTxt.setPromptText("Priority");

        weightTxt.clear();
        weightTxt.setPromptText("Weight");


        setComboBox.setPromptText("Set");
        setComboBox.getSelectionModel().clearSelection();

        repComboBox.setPromptText("Rep");
        repComboBox.getSelectionModel().clearSelection();

        restComboBox.setPromptText("Rest");
        restComboBox.getSelectionModel().clearSelection();

        tempoComboBox.setPromptText("Tempo");
        tempoComboBox.getSelectionModel().clearSelection();

        targetMuscleComboBox.setPromptText("Target Muscle");
        targetMuscleComboBox.getSelectionModel().clearSelection();

    }
    public void setParentContainer(VBox parentContainer) {
        this.parentContainer = parentContainer;
    }
    public void setParentComponents(JFXButton dayCompletedBtn,ComboBox<Integer> dayCombox, JFXTextField priorityNumTxt,ComboBox<String> targetMuscleComboBox,JFXTextField weightTxt,ComboBox<String> workoutComboBox,ComboBox<Integer> setComboBox,ComboBox<Integer> repComboBox,ComboBox<Integer> tempoComboBox,ComboBox<Integer> restComboBox){
        this.dayCombox=dayCombox;
        this.priorityNumTxt=priorityNumTxt;
        this.targetMuscleComboBox=targetMuscleComboBox;
        this.weightTxt=weightTxt;
        this.workoutComboBox=workoutComboBox;
        this.setComboBox=setComboBox;
        this.repComboBox=repComboBox;
        this.tempoComboBox=tempoComboBox;
        this.restComboBox=restComboBox;
        this.dayCompletedBtn=dayCompletedBtn;

    }
}
