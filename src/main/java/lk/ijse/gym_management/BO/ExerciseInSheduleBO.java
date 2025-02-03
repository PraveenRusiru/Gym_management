package lk.ijse.gym_management.BO;

import lk.ijse.gym_management.DTO.ClientContactDto;
import lk.ijse.gym_management.DTO.ExercisesInSheduleDto;
import lk.ijse.gym_management.Entity.ExercisesInShedule;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExerciseInSheduleBO extends SuperBO<ExercisesInSheduleDto> {
    ArrayList<ExercisesInSheduleDto> getAllExercisesInShedule();
    void setExercisesInShedule(ArrayList<ExercisesInSheduleDto> exercisesInShedule);

}
