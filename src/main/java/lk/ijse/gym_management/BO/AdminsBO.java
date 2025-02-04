package lk.ijse.gym_management.BO;

import lk.ijse.gym_management.DTO.AdminDto;

import java.sql.SQLException;

public interface AdminsBO extends SuperBO<AdminDto> {
    public boolean isUserValide(String username,String password) throws SQLException;
    public boolean isPasswordUpdated(String newPasswrod) throws SQLException ;
}
