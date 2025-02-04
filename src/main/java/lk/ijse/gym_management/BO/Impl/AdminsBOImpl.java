package lk.ijse.gym_management.BO.Impl;


import lk.ijse.gym_management.BO.AdminsBO;
import lk.ijse.gym_management.DAO.DAOFactory;
import lk.ijse.gym_management.DAO.Impl.AdminsDaoImpl;
import lk.ijse.gym_management.DTO.AdminDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminsBOImpl implements AdminsBO {

    AdminsDaoImpl adminsDao=(AdminsDaoImpl)DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ADMIN);
    @Override
    public String getNextId() throws SQLException {
        return "";
    }

    @Override
    public void setEntity(AdminDto dto) throws SQLException {

    }

    @Override
    public AdminDto getEntity() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<AdminDto> getAll() throws SQLException {
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

    @Override
    public boolean isUserValide(String username, String password) throws SQLException {
        return adminsDao.isUserValide(username,password);
    }

    @Override
    public boolean isPasswordUpdated(String newPasswrod) throws SQLException {
        return adminsDao.isPasswordUpdated(newPasswrod);
    }
}
