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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lk.ijse.gym_management.BO.BOFactory;
import lk.ijse.gym_management.BO.Impl.ExerciseBoImpl;
import lk.ijse.gym_management.BO.Impl.ExerciseInSheduleBoImpl;
import lk.ijse.gym_management.BO.Impl.SheduleBoImpl;
import lk.ijse.gym_management.DTO.ExercisesInSheduleDto;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;

public class SheduleMakingController implements Initializable {

    FXMLLoader fxmlLoader;
    private ArrayList<ExercisesInSheduleDto> exercisesInShedule;
    private ExercisesInSheduleDto exercisesInSheduleDto;
    boolean isShowSheduleData = false;
    int completedSheduleCount = 0;
    int currentShownSheduleCount = 0;
    String targetMuscle;
    int day=-1;
    ExerciseInSheduleBoImpl exerciseInSheduleBoImpl=(ExerciseInSheduleBoImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.EXERCISEINSHEDULE);
    ExerciseBoImpl exerciseBoImpl= (ExerciseBoImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.EXERCISE);
    private SheduleRowController controller;
    public SheduleMakingController() {
        exercisesInShedule=new ArrayList<>();
    }
    @FXML
    private JFXButton addScheduleBtn;

    @FXML
    private ComboBox<Integer> dayComboBox;

    @FXML
    private JFXButton dayCompletedBtn;

    @FXML
    private Label dayLbl;

    @FXML
    private Label enddateLbl;

    @FXML
    private Label memberIdLbl1;

    @FXML
    private Label memberTypeLbl;

    @FXML
    private Label memberTypeLbl1;

    @FXML
    private Label memberTypeLbl11;

    @FXML
    private Label memberTypeLbl111;

    @FXML
    private JFXButton nextBtn;

    @FXML
    private JFXTextField priorityNumTxt;

    @FXML
    private ComboBox<Integer> repComboBox;

    @FXML
    private JFXButton resetBtn;

    @FXML
    private ComboBox<Integer> restComboBox;

    @FXML
    private ComboBox<Integer> setComboBox;

    @FXML
    private Pane sheduleMakingPane;

    @FXML
    private VBox sheduleTable;

    @FXML
    private JFXButton showScheduleBtn;

    @FXML
    private ComboBox<String> targetMuscleComboBox;

    @FXML
    private ComboBox<Integer> tempoComboBox;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private JFXTextField weightTxt;

    @FXML
    private ComboBox<String> workoutComboBox;

    @FXML
    void addSchedule(ActionEvent event) throws SQLException {
        // sheduleTable.getChildren().clear();
        isShowSheduleData = false;
        dayCompletedBtn.setDisable(false);
      //  selectedSheduleDataDto=new SheduleDataDto(dayComboBox.getValue(),Integer.parseInt(priorityNumTxt.getText()),workoutComboBox.getValue(),Integer.parseInt(weightTxt.getText()),setComboBox.getValue(),repComboBox.getValue(),restComboBox.getValue(),tempoComboBox.getValue());
        exercisesInSheduleDto=new ExercisesInSheduleDto();
        exercisesInSheduleDto.setDay(dayComboBox.getValue());
        exercisesInSheduleDto.setPriorityNum(Integer.parseInt(priorityNumTxt.getText()));
        exercisesInSheduleDto.setWeight(Integer.parseInt(weightTxt.getText()));
        exercisesInSheduleDto.setSets(setComboBox.getValue());
        exercisesInSheduleDto.setReps(repComboBox.getValue());
        exercisesInSheduleDto.setRest(restComboBox.getValue());
        exercisesInSheduleDto.setTempo(tempoComboBox.getValue());
        exercisesInSheduleDto.setExerciseId(exerciseBoImpl.getExerciseId(workoutComboBox.getValue()));
        // sheduleDataDtos.add(selectedSheduleDataDto);

        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/View/Shedule/SheduleRow.fxml"));
        try{
            HBox box = fxmlLoader.load();
            controller=fxmlLoader.getController();
            controller.setDataForRow(exercisesInSheduleDto);
            controller.setParentContainer(sheduleTable);
            controller.setParentComponents(dayCompletedBtn,dayComboBox,priorityNumTxt,targetMuscleComboBox,weightTxt,workoutComboBox,setComboBox,repComboBox,tempoComboBox,restComboBox);

            sheduleTable.getChildren().add(box);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());

        }

    }

    @FXML
    void dayCompleted(ActionEvent event) throws SQLException {
        completedSheduleCount++;
        isShowSheduleData = false;
        boolean isDuplicated=false;
        //  sheduleDataDtos.add(selectedSheduleDataDto);

        for (var node:sheduleTable.getChildren()) {
            if(node instanceof  HBox){
                HBox hbox= (HBox) node;
                Label priorityLabel = (Label)hbox.lookup("#priorityLbl");
                Label tempoLabel = (Label)hbox.lookup("#tempoLbl");
                Label weightLabel = (Label)hbox.lookup("#weightLbl");
                Label workoutLabel = (Label)hbox.lookup("#workoutLbl");
                Label setLabel = (Label)hbox.lookup("#setLbl");
                Label repLabel = (Label)hbox.lookup("#repLbl");
                Label restLabel = (Label)hbox.lookup("#restLbl");
                exercisesInSheduleDto=new ExercisesInSheduleDto();
                exercisesInSheduleDto.setScheduleId(new SheduleBoImpl().getNextId());
                exercisesInSheduleDto.setDay(day);
                exercisesInSheduleDto.setPriorityNum(Integer.parseInt(priorityLabel.getText()));
                exercisesInSheduleDto.setWeight(Integer.parseInt(weightLabel.getText()));
                exercisesInSheduleDto.setSets(Integer.parseInt(setLabel.getText()));
                exercisesInSheduleDto.setReps(Integer.parseInt(repLabel.getText()));
                exercisesInSheduleDto.setRest(Integer.parseInt(restLabel.getText()));
                exercisesInSheduleDto.setTempo(Integer.parseInt(tempoLabel.getText()));
                exercisesInSheduleDto.setExerciseId(exerciseBoImpl.getExerciseId(workoutLabel.getText()));


                Optional<ExercisesInSheduleDto> exists=exercisesInShedule.stream()
                        .filter(dto->dto.getDay()==day && dto.getPriorityNum()==exercisesInSheduleDto.getPriorityNum())
                        .findFirst();

                if(exists.isPresent()){
                    Alert addingAlert = new Alert(Alert.AlertType.CONFIRMATION, "This day already has data! Do you want to modify it?");
                    Optional<ButtonType> result = addingAlert.showAndWait();

                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        // Replace existing item with the updated one
                        isDuplicated=true;
                        exercisesInShedule.set(exercisesInShedule.indexOf(exists.get()),exercisesInSheduleDto);

                        for (ExercisesInSheduleDto sheduleDataDto:exercisesInShedule){
                            System.out.println("shedule "+sheduleDataDto.getDay()+" "+sheduleDataDto.getPriorityNum()+" "+sheduleDataDto.getExerciseId()+""+exerciseBoImpl.getExerciseDescription(sheduleDataDto.getExerciseId())+sheduleDataDto.getSets()+" "+sheduleDataDto.getReps());
                        }

                    } else {
                        new Alert(Alert.AlertType.ERROR, "OK!").show();
                    }
                }else{

                    exercisesInShedule.add(exercisesInSheduleDto);
                }

            }
        }
        if(isDuplicated){
            --completedSheduleCount;
        }
        sortArrayList();
        sheduleTable.getChildren().clear();
        clearAllComboBox();
        //dayComboBox.getSelectionModel().clearSelection();

    }

    @FXML
    void nextBtnAction(ActionEvent event) throws IOException {
        if(!(exercisesInShedule.isEmpty())){
            exerciseInSheduleBoImpl.setExercisesInShedule(exercisesInShedule);
            sheduleMakingPane.getChildren().clear();
            sheduleMakingPane.getChildren().add(FXMLLoader.load(getClass().getResource("/View/Shedule/NutrationShedule.fxml")));
        }else{
            new Alert(Alert.AlertType.ERROR,"Complete Schedule !").show();
        }
    }

    @FXML
    void resetBtnClick(ActionEvent event) {
        sheduleTable.getChildren().clear();
        resetBtn.setVisible(false);
        addScheduleBtn.setVisible(true);
    }

    @FXML
    void selectRepComboBox(ActionEvent event) {
        isShowSheduleData = false;
    }

    @FXML
    void selectRestComboBox(ActionEvent event) {
        isShowSheduleData = false;
    }

    @FXML
    void selectSetComboBox(ActionEvent event) {
        isShowSheduleData = false;
    }

    @FXML
    void selectTargetMuscleComboBox(ActionEvent event) {
        isShowSheduleData = false;
        targetMuscle=targetMuscleComboBox.getValue();
        System.out.println("Muscles "+targetMuscle);
//        ArrayList<String> workouts = new ArrayList<>();
        try {
          //  ArrayList<String> workouts=exerciseModel.getExercises(targetMuscleComboBox.getValue());
            ArrayList<String> workouts=exerciseBoImpl.getExercises(targetMuscleComboBox.getValue());
            System.out.println("Workouts "+workouts);
            ObservableList<String> workoutList= FXCollections.observableArrayList();
            for (String str:workouts){
                workoutList.add(str);
            }
            workoutComboBox.setItems(workoutList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL EROOR").show();
        }
    }

    @FXML
    void selectTempoComboBox(ActionEvent event) {
        isShowSheduleData = false;
    }

    @FXML
    void selectWorkoutComboBox(ActionEvent event) {
        isShowSheduleData = false;
    }

    @FXML
    void selectdayComboBox(ActionEvent event) {
        isShowSheduleData = false;
        day=dayComboBox.getValue();
        dayLbl.setText("Day - "+day);
    }

    @FXML
    void showSchedule(ActionEvent event) throws SQLException {
        isShowSheduleData = true;
        resetBtn.setDisable(false);
        resetBtn.setVisible(true);
        showScheduleBtn.setText("Next Shedule");
        sheduleTable.getChildren().clear();


        System.out.println("Completed shedule count "+completedSheduleCount);
        if(currentShownSheduleCount>=completedSheduleCount){
            currentShownSheduleCount=0;
        }
        currentShownSheduleCount++;
        System.out.println("Current shown count "+currentShownSheduleCount);
        for (int i = 0; i < exercisesInShedule.size(); i++) {
            int day1=exercisesInShedule.get(i).getDay();
            System.out.println("Day :"+day1);

            if(day1==currentShownSheduleCount){
                System.out.println("row adding process started ");
                dayLbl.setText("Day - "+day1);
                dayComboBox.setValue(day1);
                fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/View/Shedule/SheduleRow.fxml"));
                try{
                    HBox box = fxmlLoader.load();
                    controller=fxmlLoader.getController();
                    System.out.println("Exercise Id "+exercisesInShedule.get(i).getExerciseId());
                    controller.setDataForRow(exercisesInShedule.get(i));
                    controller.setParentContainer(sheduleTable);
                    controller.setParentComponents(dayCompletedBtn,dayComboBox,priorityNumTxt,targetMuscleComboBox,weightTxt,workoutComboBox,setComboBox,repComboBox,tempoComboBox,restComboBox);
                    sheduleTable.getChildren().add(box);

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());

                }
            }
            System.out.println("row adding process Ended ");
        }
        //completedSheduleCount++;
    }

    @FXML
    void updateRow(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dayCompletedBtn.setDisable(true);
        updateBtn.setVisible(false);
        resetBtn.setDisable(true);
        resetBtn.setVisible(false);
        ObservableList<Integer> dayList= FXCollections.observableArrayList();
        for (int i = 0; i < SheduleController.count; i++) {
            dayList.add(i+1);
        }
        dayComboBox.setItems(dayList);
        loadObservarablaLists();
    }
    public void loadObservarablaLists(){
        try {
            ArrayList<String> muslces=exerciseBoImpl.getMuscles();
            System.out.println("Muscles "+muslces);
            ObservableList<String> muscleList=FXCollections.observableArrayList();
            for (String str:muslces){
                muscleList.add(str);
            }
            targetMuscleComboBox.setItems(muscleList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL EROOR").show();
        }



        ObservableList<Integer> setList=FXCollections.observableArrayList(1,2,3,4,5,6,7);
        setComboBox.setItems(setList);
        ObservableList<Integer> repList=FXCollections.observableArrayList(1,3,5,6,8,10,12,15,18,20,25,30);
        repComboBox.setItems(repList);
        ObservableList<Integer> restList=FXCollections.observableArrayList(30,60,90,120,150,300);
        restComboBox.setItems(restList);
        ObservableList<Integer> tempoList=FXCollections.observableArrayList(2010,2111,0010,0100,4020,3130,2121,3030);
        tempoComboBox.setItems(tempoList);
    }

    public void clearAllComboBox() {
        priorityNumTxt.clear();
        priorityNumTxt.setPromptText("Priority");

        weightTxt.clear();
        weightTxt.setPromptText("Weight");

        // Set prompt text before clearing selection
        //dayComboBox.setPromptText("Day");
        //dayComboBox.getSelectionModel().clearSelection();

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
    private void sortArrayList() {
        exercisesInShedule.sort(Comparator.comparing(ExercisesInSheduleDto::getPriorityNum));
    }
}
