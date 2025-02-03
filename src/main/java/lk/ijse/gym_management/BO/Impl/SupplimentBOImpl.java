package lk.ijse.gym_management.BO.Impl;

import lk.ijse.gym_management.BO.SupplimentBO;
import lk.ijse.gym_management.DAO.DAOFactory;
import lk.ijse.gym_management.DAO.Impl.SupplimentDaoImpl;
import lk.ijse.gym_management.DTO.SupplimentDto;
import lk.ijse.gym_management.Entity.Suppliment;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplimentBOImpl implements SupplimentBO {
    SupplimentDaoImpl supplimentDao=(SupplimentDaoImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIMENT);
    @Override
    public String getNextId() throws SQLException {
        return supplimentDao.getNextId();
    }

    @Override
    public void setEntity(SupplimentDto dto) throws SQLException {
            supplimentDao.setEntity(new Suppliment(dto.getSupplimentId(),dto.getSupplimentTitle(),dto.getSupplimentDescription(),dto.getSupplimentCategory(), dto.getSupplimentPrice()));
    }

    @Override
    public SupplimentDto getEntity() throws SQLException {
        Suppliment suppliment=supplimentDao.getEntity();
        return new SupplimentDto(suppliment.getSupplimentId(),suppliment.getSupplimentTitle(),suppliment.getSupplimentDescription(),suppliment.getSupplimentCategory(),suppliment.getSupplimentPrice());
    }

    @Override
    public ArrayList<SupplimentDto> getAll() throws SQLException {
        ArrayList<Suppliment> suppliments=supplimentDao.getAll();
        ArrayList<SupplimentDto> supplimentDtos=new ArrayList<>();
        for (Suppliment suppliment:suppliments) {
            SupplimentDto supplimentDto=new SupplimentDto(suppliment.getSupplimentId(),suppliment.getSupplimentTitle(),suppliment.getSupplimentDescription(),suppliment.getSupplimentCategory(),suppliment.getSupplimentPrice());
            supplimentDtos.add(supplimentDto);
        }
        return supplimentDtos;
    }

    @Override
    public boolean update() throws SQLException {
        return supplimentDao.update();
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return supplimentDao.delete(id);
    }

    @Override
    public boolean save() throws SQLException {
        return supplimentDao.save();
    }
}
