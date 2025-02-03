package lk.ijse.gym_management.BO.Impl;

import javafx.scene.control.Alert;
import lk.ijse.gym_management.BO.OrderBO;
import lk.ijse.gym_management.DAO.DAOFactory;
import lk.ijse.gym_management.DAO.Impl.InventoryDaoImpl;
import lk.ijse.gym_management.DAO.Impl.OrderDaoImpl;
import lk.ijse.gym_management.DAO.Impl.OrderDetailDaoImpl;
import lk.ijse.gym_management.DB.DBConnection;
import lk.ijse.gym_management.DTO.OrderDto;
import lk.ijse.gym_management.Entity.Order;
import lk.ijse.gym_management.Entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class OrderBOImpl implements OrderBO {
    OrderDaoImpl orderDao = (OrderDaoImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERS);
    OrderDetailDaoImpl orderDetailDao=(OrderDetailDaoImpl)DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERDETAILS);
    InventoryDaoImpl inventoryDao=(InventoryDaoImpl)DAOFactory.getInstance().getDAO(DAOFactory.DAOType.INVENTORY);
    @Override
    public String getNextId() throws SQLException {
        return orderDao.getNextId();
    }

    @Override
    public void setEntity(OrderDto dto) throws SQLException {
            orderDao.setEntity(new Order(dto.getOrderId(),dto.getCustomerId(),dto.getDate(),dto.getTotal(),dto.getDiscount()));
    }

    @Override
    public OrderDto getEntity() throws SQLException {
        Order order = orderDao.getEntity();
        return new OrderDto(order.getOrderId(),order.getCustomerId(),order.getDate(),order.getTotal(),order.getDiscount());
    }

    @Override
    public ArrayList<OrderDto> getAll() throws SQLException {
        ArrayList<Order> orders = orderDao.getAll();
        ArrayList<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders) {
            OrderDto orderDto = new OrderDto(order.getOrderId(),order.getCustomerId(),order.getDate(),order.getTotal(),order.getDiscount());
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }

    @Override
    public boolean update() throws SQLException {
        return orderDao.update();
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return orderDao.delete(id);
    }

    @Override
    public boolean save() throws SQLException {
        Connection connection=null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            if(orderDao.save()){
                System.out.println("Order added successfully");
                ArrayList<OrderDetail> orderDetails = orderDetailDao.getList();
                boolean isOrderDetailSaved=true;
                boolean isQtyReduced=true;
                for (OrderDetail orderDetail : orderDetails) {
                    orderDetailDao.setEntity(orderDetail);
                    if(orderDetailDao.save()){
                        if(!inventoryDao.isQtyReduced(orderDetail.getQty(),orderDetail.getProductId())){
                            isQtyReduced=false;
                        }
                    }else{
                        isOrderDetailSaved=false;
                    }
                }
                if(isOrderDetailSaved && isQtyReduced) {
                    System.out.println("Order saved successfully");
                    new Alert(Alert.AlertType.INFORMATION, "Order saved successfully").show();
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            System.out.println("Payment not saved");
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }

    }
}
