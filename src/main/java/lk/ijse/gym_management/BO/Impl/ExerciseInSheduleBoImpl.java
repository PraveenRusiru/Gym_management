package lk.ijse.gym_management.BO.Impl;

import lk.ijse.gym_management.BO.ExerciseInSheduleBO;
import lk.ijse.gym_management.DAO.DAOFactory;
import lk.ijse.gym_management.DAO.ExerciseInSheduleDao;
import lk.ijse.gym_management.DTO.ExercisesInSheduleDto;
import lk.ijse.gym_management.Entity.ExercisesInShedule;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExerciseInSheduleBoImpl implements ExerciseInSheduleBO {
    ExerciseInSheduleDao exerciseInSheduleDao= (ExerciseInSheduleDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EXERCISEINSHEDULE);
    @Override
    public ArrayList<ExercisesInSheduleDto> getAllExercisesInShedule() {
       ArrayList<ExercisesInShedule> exercisesInSheduleDtos = exerciseInSheduleDao.getAllExercisesInShedule();
       ArrayList<ExercisesInSheduleDto> exercisesInSheduleDtoArrayList = new ArrayList<>();
       for (ExercisesInShedule exercisesInShedule : exercisesInSheduleDtos) {
           ExercisesInSheduleDto exercisesInSheduleDto = new ExercisesInSheduleDto(exercisesInShedule.getScheduleId(),exercisesInShedule.getExerciseId(),exercisesInShedule.getDay(),exercisesInShedule.getPriorityNum(),exercisesInShedule.getWeight(),exercisesInShedule.getSets(),exercisesInShedule.getReps(),exercisesInShedule.getRest(),exercisesInShedule.getTempo());
           exercisesInSheduleDtoArrayList.add(exercisesInSheduleDto);
       }
        return exercisesInSheduleDtoArrayList;
    }

    @Override
    public void setExercisesInShedule(ArrayList<ExercisesInSheduleDto> exercisesInShedule) {
        ArrayList<ExercisesInShedule> exercisesInSheduleArrayList = new ArrayList<>();
        for (ExercisesInSheduleDto dto : exercisesInShedule) {
            ExercisesInShedule exercisesInShedule1=new ExercisesInShedule(dto.getScheduleId(),dto.getExerciseId(),dto.getDay(),dto.getPriorityNum(),dto.getWeight(),dto.getSets(),dto.getReps(),dto.getRest(),dto.getTempo());
            exercisesInSheduleArrayList.add(exercisesInShedule1);
        }
        exerciseInSheduleDao.setExercisesInShedule(exercisesInSheduleArrayList);
    }



    @Override
    public String getNextId() throws SQLException {
        return exerciseInSheduleDao.getNextId();
    }

    @Override
    public void setEntity(ExercisesInSheduleDto dto) throws SQLException {
        ExercisesInShedule exercisesInShedule = new ExercisesInShedule(dto.getScheduleId(),dto.getExerciseId(),dto.getDay(),dto.getPriorityNum(),dto.getWeight(),dto.getSets(),dto.getReps(),dto.getRest(),dto.getTempo());
         exerciseInSheduleDao.setEntity(exercisesInShedule);
    }

    @Override
    public ExercisesInSheduleDto getEntity() throws SQLException {
       ExercisesInShedule exercisesInShedule=exerciseInSheduleDao.getEntity();
        ExercisesInSheduleDto exercisesInSheduleDto=null;
        if(exercisesInShedule!=null){
            exercisesInSheduleDto = new ExercisesInSheduleDto(exercisesInShedule.getScheduleId(),exercisesInShedule.getExerciseId(),exercisesInShedule.getDay(),exercisesInShedule.getPriorityNum(),exercisesInShedule.getWeight(),exercisesInShedule.getSets(),exercisesInShedule.getReps(),exercisesInShedule.getRest(),exercisesInShedule.getTempo());
        }
        return exercisesInSheduleDto;
    }

    @Override
    public ArrayList<ExercisesInSheduleDto> getAll() throws SQLException {

        return null;
    }

    @Override
    public boolean update() throws SQLException {
            return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
      return   exerciseInSheduleDao.delete(id);
    }

    @Override
    public boolean save() throws SQLException {
      return   exerciseInSheduleDao.save();
    }
}
