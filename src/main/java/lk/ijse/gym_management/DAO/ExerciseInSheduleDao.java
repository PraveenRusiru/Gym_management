package lk.ijse.gym_management.DAO;

import lk.ijse.gym_management.DTO.ExercisesInSheduleDto;
import lk.ijse.gym_management.Entity.ExercisesInShedule;

import java.util.ArrayList;

public interface ExerciseInSheduleDao extends SuperDAO <ExercisesInShedule>{
    ArrayList<ExercisesInShedule> getAllExercisesInShedule();
    void setExercisesInShedule(ArrayList<ExercisesInShedule> exercisesInShedule);

}
