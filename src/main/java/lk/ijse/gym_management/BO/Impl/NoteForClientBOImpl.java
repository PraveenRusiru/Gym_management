package lk.ijse.gym_management.BO.Impl;

import lk.ijse.gym_management.BO.NoteForClientBO;
import lk.ijse.gym_management.DAO.DAOFactory;
import lk.ijse.gym_management.DAO.Impl.NoteForClientDaoImpl;
import lk.ijse.gym_management.DAO.NoteForClientDao;
import lk.ijse.gym_management.DTO.NoteForClientDto;
import lk.ijse.gym_management.Entity.NoteForClient;

import java.sql.SQLException;
import java.util.ArrayList;

public class NoteForClientBOImpl implements NoteForClientBO {
    NoteForClientDaoImpl noteForClientDao = (NoteForClientDaoImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.NOTEFORCLIENT);
    @Override
    public String getNextId() throws SQLException {
        return noteForClientDao.getNextId();
    }

    @Override
    public void setEntity(NoteForClientDto dto) throws SQLException {
            noteForClientDao.setEntity(new NoteForClient(dto.getNoteId(),dto.getClientId(), dto.getDescription()));
    }

    @Override
    public NoteForClientDto getEntity() throws SQLException {
        NoteForClient noteForClient = noteForClientDao.getEntity();
        NoteForClientDto noteForClientDto=null;
        if (noteForClient != null) {
             noteForClientDto=new NoteForClientDto(noteForClient.getNoteId(),noteForClient.getClientId(),noteForClient.getDescription());
        }
        return noteForClientDto;
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
       return noteForClientDao.save();
    }
}
