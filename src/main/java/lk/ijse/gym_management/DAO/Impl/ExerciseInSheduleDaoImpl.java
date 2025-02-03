package lk.ijse.gym_management.DAO.Impl;

import javafx.scene.control.Alert;
import lk.ijse.gym_management.DAO.ExerciseInSheduleDao;
import lk.ijse.gym_management.Entity.ExercisesInShedule;
import lk.ijse.gym_management.Utill.CRUDUtill;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExerciseInSheduleDaoImpl implements ExerciseInSheduleDao {
  private static ArrayList<ExercisesInShedule> exercisesInShedules;

  @Override
    public String getNextId() throws SQLException {
        return null;
    }

    @Override
    public void setEntity(ExercisesInShedule entity) throws SQLException {

    }

    @Override
    public ExercisesInShedule getEntity() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<ExercisesInShedule> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update() throws SQLException {
            return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
            boolean isdeleted= CRUDUtill.execute("delete from ExercisesInShedule where ExercisesInShedule_Id=?",id);
            if(isdeleted){
//                new Alert(Alert.AlertType.INFORMATION,"Deleted sucessful !").show();
                System.out.println("Deleted sucessful");
                return true;
            }else{
                //new Alert(Alert.AlertType.ERROR,"Failed to delete !").show();
                System.out.println("Failed to delete sucessful");
                return false;
            }
    }

    @Override
    public boolean save() throws SQLException {
        boolean isEveryRowSaved = true;
        for (ExercisesInShedule exercisesInShedule : exercisesInShedules) {
            boolean isSaved=(CRUDUtill.execute("insert into Exercises_in_Schedule values (?,?,?,?,?,?,?,?,?)",
                    exercisesInShedule.getScheduleId(),
                    exercisesInShedule.getExerciseId(),
                    exercisesInShedule.getDay(),
                    exercisesInShedule.getPriorityNum(),
                    exercisesInShedule.getWeight(),
                    exercisesInShedule.getSets(),
                    exercisesInShedule.getReps(),
                    exercisesInShedule.getTempo(),
                    exercisesInShedule.getRest()
            ));
            if(!isSaved ){
                isEveryRowSaved = false;
            }
        }
       if(isEveryRowSaved){
           new Alert(Alert.AlertType.INFORMATION,"Exercises Saved sucessful !").show();
           return true;
       }else{
           new Alert(Alert.AlertType.ERROR,"Failed to save exercises !").show();
           return false;
       }
    }

    @Override
    public ArrayList<ExercisesInShedule> getAllExercisesInShedule() {
        return exercisesInShedules;
    }

    @Override
    public void setExercisesInShedule(ArrayList<ExercisesInShedule> exercisesInShedule) {
     exercisesInShedules = exercisesInShedule;
    }

}
