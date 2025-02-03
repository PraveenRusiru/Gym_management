package lk.ijse.gym_management.DAO;

import lk.ijse.gym_management.Entity.ClientContact;

import java.sql.SQLException;

public interface ClientContactDao extends SuperDAO<ClientContact> {
    String getClientId(String clientNic) throws SQLException;
}
