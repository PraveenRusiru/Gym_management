package lk.ijse.gym_management.BO;

import lk.ijse.gym_management.DTO.ClientDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SuperBO <T>{
    String getNextId() throws SQLException;
    void setEntity(T dto) throws SQLException;
    T  getEntity() throws SQLException;
    ArrayList<T > getAll() throws SQLException;
    boolean update() throws SQLException;
    boolean delete(String id) throws SQLException;
    boolean save() throws SQLException;
}
