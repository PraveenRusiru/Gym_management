package lk.ijse.gym_management.DAO;

import lk.ijse.gym_management.Entity.Admins;

import java.sql.SQLException;

public interface AdminsDao extends SuperDAO<Admins> {
    public boolean isUserValide(String username,String password) throws SQLException ;
    public boolean isPasswordUpdated(String newPasswrod) throws SQLException ;
}
