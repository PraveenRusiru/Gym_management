package lk.ijse.gym_management.BO.Impl;

import lk.ijse.gym_management.BO.NutrationBO;
import lk.ijse.gym_management.DAO.DAOFactory;
import lk.ijse.gym_management.DAO.Impl.NutrationProgrammeDaoImpl;
import lk.ijse.gym_management.DTO.NutraionProgrammeDto;
import lk.ijse.gym_management.Entity.NutraionProgramme;

import java.sql.SQLException;
import java.util.ArrayList;

public class NutrationBoImpl implements NutrationBO {
    NutrationProgrammeDaoImpl nutrationProgrammeDao=(NutrationProgrammeDaoImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.NUTRATIONPROGRAMME);
    @Override
    public String getNextId() throws SQLException {
        return nutrationProgrammeDao.getNextId();
    }

    @Override
    public void setEntity(NutraionProgrammeDto dto) throws SQLException {
            nutrationProgrammeDao.setEntity(new NutraionProgramme(dto.getProgrammeId(),dto.getSheduleId(),dto.getDay(),dto.getCalories(),dto.getProtein(),dto.getCarbs(),dto.getFat()));
    }

    @Override
    public NutraionProgrammeDto getEntity() throws SQLException {
         NutraionProgramme nutraionProgramme=nutrationProgrammeDao.getEntity();
         NutraionProgrammeDto nutraionProgrammeDto=null;
         if(nutraionProgramme!=null){
             nutraionProgrammeDto=new NutraionProgrammeDto(nutraionProgramme.getProgrammeId(),nutraionProgramme.getSheduleId(),nutraionProgramme.getDay(),nutraionProgramme.getCalories(),nutraionProgramme.getProtein(),nutraionProgramme.getCarbs(),nutraionProgramme.getFat());
         }
        return nutraionProgrammeDto;
    }

    @Override
    public ArrayList<NutraionProgrammeDto> getAll() throws SQLException {
        ArrayList<NutraionProgramme> nutraionProgrammes=nutrationProgrammeDao.getAll();
        ArrayList<NutraionProgrammeDto> nutraionProgrammeDtos=new ArrayList<>();
        for (NutraionProgramme nutraionProgramme:nutraionProgrammes){
            NutraionProgrammeDto nutraionProgrammeDto=new NutraionProgrammeDto(nutraionProgramme.getProgrammeId(),nutraionProgramme.getSheduleId(),nutraionProgramme.getDay(),nutraionProgramme.getCalories(),nutraionProgramme.getProtein(),nutraionProgramme.getCarbs(),nutraionProgramme.getFat());
            nutraionProgrammeDtos.add(nutraionProgrammeDto);
        }
        return nutraionProgrammeDtos;
    }

    @Override
    public boolean update() throws SQLException {
      return   nutrationProgrammeDao.update();
    }

    @Override
    public boolean delete(String id) throws SQLException {
       return nutrationProgrammeDao.delete(id);
    }

    @Override
    public boolean save() throws SQLException {
       return nutrationProgrammeDao.save();
    }
}
