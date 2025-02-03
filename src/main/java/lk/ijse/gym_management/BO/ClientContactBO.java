package lk.ijse.gym_management.BO;

import lk.ijse.gym_management.DTO.ClientContactDto;
import lk.ijse.gym_management.Entity.ClientContact;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ClientContactBO extends SuperBO<ClientContactDto> {
    String getClientId(String clientNic) throws SQLException;
}
