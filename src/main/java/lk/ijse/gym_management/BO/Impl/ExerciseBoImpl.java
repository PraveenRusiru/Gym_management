package lk.ijse.gym_management.BO.Impl;

import lk.ijse.gym_management.BO.ExerciseBO;
import lk.ijse.gym_management.DAO.DAOFactory;
import lk.ijse.gym_management.DAO.Impl.ExerciseDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExerciseBoImpl implements ExerciseBO {
    ExerciseDaoImpl exerciseDao=(ExerciseDaoImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EXERCISE);
    @Override
    public ArrayList<String> getExercises(String muscle) throws SQLException {
        return exerciseDao.getExercises(muscle);
    }

    @Override
    public ArrayList<String> getMuscles() throws SQLException {
        return exerciseDao.getMuscles();
    }

    @Override
    public String getExerciseDescription(String id) throws SQLException {
        return exerciseDao.getExerciseDescription(id);
    }

    @Override
    public String getExerciseId(String exerciseName) throws SQLException {
        return exerciseDao.getExerciseId(exerciseName);
    }

    @Override
    public String getNextId() throws SQLException {
        return "";
    }

    @Override
    public void setEntity(Object dto) throws SQLException {

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
