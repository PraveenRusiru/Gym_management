package lk.ijse.gym_management.BO.Impl;

import lk.ijse.gym_management.BO.SheduleBO;
import lk.ijse.gym_management.DAO.DAOFactory;
import lk.ijse.gym_management.DAO.Impl.SheduleDaoImpl;
import lk.ijse.gym_management.DTO.SheduleDto;
import lk.ijse.gym_management.Entity.Shedule;

import java.sql.SQLException;
import java.util.ArrayList;

public class SheduleBoImpl implements SheduleBO {
    SheduleDaoImpl sheduleDao = (SheduleDaoImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SHEDULE);
    @Override
    public String getNextId() throws SQLException {
        return sheduleDao.getNextId();
    }

    @Override
    public void setEntity(SheduleDto dto) throws SQLException {
        sheduleDao.setEntity(new Shedule(dto.getSheduleId(),dto.getClientId(),dto.getIssuedDate(),dto.getEndDate(),dto.getGoal()));
    }

    @Override
    public SheduleDto getEntity() throws SQLException {
        Shedule shedule = sheduleDao.getEntity();
        SheduleDto sheduleDto=null;
        if(shedule!=null){
            sheduleDto=new SheduleDto(shedule.getSheduleId(),shedule.getClientId(),shedule.getIssuedDate(),shedule.getEndDate(),shedule.getGoal());
        }
        return sheduleDto;
    }

    @Override
    public ArrayList<SheduleDto> getAll() throws SQLException {
        ArrayList<Shedule> shedules = sheduleDao.getAll();
        ArrayList<SheduleDto> sheduleDtos = new ArrayList<>();
        for (Shedule shedule : shedules) {
           SheduleDto sheduleDto= new SheduleDto(shedule.getSheduleId(),shedule.getClientId(),shedule.getIssuedDate(),shedule.getEndDate(),shedule.getGoal());
           sheduleDtos.add(sheduleDto);
        }
        return sheduleDtos;
    }

    @Override
    public boolean update() throws SQLException {
       return sheduleDao.update();
    }

    @Override
    public boolean delete(String id) throws SQLException {
      return   sheduleDao.delete(id);
    }

    @Override
    public boolean save() throws SQLException {
       return sheduleDao.save();
    }
}
