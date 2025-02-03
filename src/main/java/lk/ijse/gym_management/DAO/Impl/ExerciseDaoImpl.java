package lk.ijse.gym_management.DAO.Impl;

import lk.ijse.gym_management.DAO.ExerciseDao;
import lk.ijse.gym_management.Utill.CRUDUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExerciseDaoImpl implements ExerciseDao {
    @Override
    public ArrayList<String> getExercises(String muscle) throws SQLException {
        ResultSet rst = CRUDUtill.execute("select Exercise.description from Exercise where target_muscle=?",muscle);

        ArrayList<String> exercises = new ArrayList<>();
        while (rst.next()) {
            exercises.add(rst.getString(1));
        }
        return exercises;
    }

    @Override
    public ArrayList<String> getMuscles() throws SQLException {
        ResultSet rst = CRUDUtill.execute("select distinct target_muscle from Exercise");

        ArrayList<String> muscles = new ArrayList<>();
        while (rst.next()) {
            muscles.add(rst.getString(1));
        }
        return muscles;
    }

    @Override
    public String getExerciseDescription(String id) throws SQLException {
        ResultSet rst=CRUDUtill.execute("select description from Exercise where exercise_id=?",id);
        String description="";
        if(rst.next()){
            description=rst.getString(1);
        }
        return description;
    }

    @Override
    public String getExerciseId(String exerciseName) throws SQLException {
        ResultSet rst=CRUDUtill.execute("select Exercise.exercise_id from Exercise where description=?",exerciseName);
        String exerciseId="";
        if(rst.next()){
            exerciseId=rst.getString(1);
        }
        return exerciseId;
    }

    @Override
    public String getNextId() throws SQLException {
        return "";
    }

    @Override
    public void setEntity(Object entity) throws SQLException {

    }

    @Override
    public Object getEntity() throws SQLException {
        return null;
    }

    @Override
    public ArrayList getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update() throws SQLException {
            return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean save() throws SQLException {
    return false;
    }
}
