package lk.ijse.gym_management.DAO.Impl;

import lk.ijse.gym_management.DAO.OrdersDao;
import lk.ijse.gym_management.Entity.Order;
import lk.ijse.gym_management.Utill.CRUDUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDaoImpl implements OrdersDao {
    static Order order;
    @Override
    public String getNextId() throws SQLException {
        ResultSet rst = CRUDUtill.execute("select order_id from Orders order by order_id desc  limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("O%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "O001"; // Return the default customer ID if no data is found
    }

    @Override
    public void setEntity(Order entity) throws SQLException {
        order = entity;
    }

    @Override
    public Order getEntity() throws SQLException {
        return order;
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException {
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
       return   CRUDUtill.execute("insert into Orders values (?,?,?,?,?)",
                order.getOrderId(),
                order.getCustomerId(),
                order.getDate(),
                order.getTotal(),
                order.getDiscount()
        );
    }
}
