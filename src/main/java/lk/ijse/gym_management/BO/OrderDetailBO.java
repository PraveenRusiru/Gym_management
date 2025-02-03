package lk.ijse.gym_management.BO;

import lk.ijse.gym_management.DTO.OrderDetailDto;
import lk.ijse.gym_management.Entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailBO extends SuperBO<OrderDetailDto> {
    public void setList(ArrayList<OrderDetailDto> entitylist) throws SQLException;
    public ArrayList<OrderDetailDto> getList() throws SQLException ;
}
