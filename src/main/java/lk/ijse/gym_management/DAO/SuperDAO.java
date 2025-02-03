package lk.ijse.gym_management.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SuperDAO<T> {
    String getNextId() throws SQLException;
    void setEntity(T entity) throws SQLException;
    T getEntity() throws SQLException;
    ArrayList<T> getAll() throws SQLException;
    boolean update() throws SQLException;
    boolean delete(String id) throws SQLException;
    boolean save() throws SQLException;

}
