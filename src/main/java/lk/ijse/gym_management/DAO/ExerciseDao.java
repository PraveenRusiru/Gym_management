package lk.ijse.gym_management.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExerciseDao extends SuperDAO {
    public ArrayList<String> getExercises(String muscle) throws SQLException;
    public ArrayList<String> getMuscles() throws SQLException ;
    public String getExerciseDescription(String id) throws SQLException;
    public String getExerciseId(String exerciseName) throws SQLException;
}
