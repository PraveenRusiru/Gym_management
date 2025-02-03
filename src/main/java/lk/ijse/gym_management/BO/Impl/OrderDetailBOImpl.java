package lk.ijse.gym_management.BO.Impl;

import lk.ijse.gym_management.BO.BOFactory;
import lk.ijse.gym_management.BO.OrderDetailBO;
import lk.ijse.gym_management.DAO.DAOFactory;
import lk.ijse.gym_management.DAO.Impl.OrderDaoImpl;
import lk.ijse.gym_management.DAO.Impl.OrderDetailDaoImpl;
import lk.ijse.gym_management.DTO.OrderDetailDto;
import lk.ijse.gym_management.Entity.Order;
import lk.ijse.gym_management.Entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailBOImpl implements OrderDetailBO {
    OrderDetailDaoImpl orderDetailDao=(OrderDetailDaoImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERDETAILS);
    @Override
    public String getNextId() throws SQLException {
        return orderDetailDao.getNextId();
    }

    @Override
    public void setEntity(OrderDetailDto dto) throws SQLException {
            orderDetailDao.setEntity(new OrderDetail(dto.getOrderId(),dto.getProductId(),dto.getQty(),dto.getUnitPrice()));
    }

    @Override
    public OrderDetailDto getEntity() throws SQLException {
        OrderDetail orderDetail=orderDetailDao.getEntity();
        return new OrderDetailDto(orderDetail.getOrderId(),orderDetail.getProductId(),orderDetail.getQty(),orderDetail.getUnitPrice());
    }

    @Override
    public ArrayList<OrderDetailDto> getAll() throws SQLException {
        ArrayList<OrderDetail> orderDetails=orderDetailDao.getAll();
        ArrayList<OrderDetailDto> orderDetailDtos=new ArrayList<>();
        for (OrderDetail orderDetail:orderDetails) {
            OrderDetailDto orderDetailDto=new OrderDetailDto(orderDetail.getOrderId(),orderDetail.getProductId(),orderDetail.getQty(),orderDetail.getUnitPrice());
            orderDetailDtos.add(orderDetailDto);
        }
        return orderDetailDtos;
    }

    @Override
    public boolean update() throws SQLException {
        return orderDetailDao.update();
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return orderDetailDao.delete(id);
    }

    @Override
    public boolean save() throws SQLException {
        return orderDetailDao.save();
    }

    @Override
    public void setList(ArrayList<OrderDetailDto> entitylist) throws SQLException {
        ArrayList<OrderDetail> orderDetails=new ArrayList<>();
        for (OrderDetailDto orderDetailDto:entitylist){
            OrderDetail orderDetail=new OrderDetail(orderDetailDto.getOrderId(),orderDetailDto.getProductId(),orderDetailDto.getQty(),orderDetailDto.getUnitPrice());
            orderDetails.add(orderDetail);
        }
        orderDetailDao.setList(orderDetails);
    }

    @Override
    public ArrayList<OrderDetailDto> getList() throws SQLException {
        ArrayList<OrderDetail> orderDetails=orderDetailDao.getList();
        ArrayList<OrderDetailDto> orderDetailDtos=new ArrayList<>();
        for (OrderDetail orderDetail:orderDetails) {
            OrderDetailDto orderDetailDto=new OrderDetailDto(orderDetail.getOrderId(),orderDetail.getProductId(),orderDetail.getQty(),orderDetail.getUnitPrice());
            orderDetailDtos.add(orderDetailDto);
        }
        return orderDetailDtos;
    }
}
