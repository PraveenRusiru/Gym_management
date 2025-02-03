package lk.ijse.gym_management.DAO;

import lk.ijse.gym_management.DTO.OrderDetailDto;
import lk.ijse.gym_management.Entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailDao extends SuperDAO<OrderDetail>{
    public void setList(ArrayList<OrderDetail> entitylist) throws SQLException;
    public ArrayList<OrderDetail> getList() throws SQLException ;
}
