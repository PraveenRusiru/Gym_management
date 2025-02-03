package lk.ijse.gym_management.DAO.Impl;

import lk.ijse.gym_management.DAO.OrderDetailDao;
import lk.ijse.gym_management.Entity.OrderDetail;
import lk.ijse.gym_management.Utill.CRUDUtill;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDaoImpl implements OrderDetailDao {
    static OrderDetail orderDetail;
  static   ArrayList<OrderDetail> orderDetails;
    @Override
    public String getNextId() throws SQLException {
        return "";
    }

    @Override
    public void setEntity(OrderDetail entity) throws SQLException {
            orderDetail = entity;
    }

    @Override
    public OrderDetail getEntity() throws SQLException {
        return orderDetail;
    }

    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException {
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
      // boolean isOrderDetailSaved=true;
        //for (OrderDetail orderDetail : orderDetails) {
           boolean isSaved=CRUDUtill.execute("insert into Order_Detail values (?,?,?,?)",
                   orderDetail.getOrderId(),
                   orderDetail.getProductId(),
                   orderDetail.getQty(),
                   orderDetail.getUnitPrice()
           );
          // if(!isSaved){
              // isOrderDetailSaved=false;
         //  }
       //}
        return  isSaved;
    }

    @Override
    public void setList(ArrayList<OrderDetail> entitylist) throws SQLException {
        orderDetails = entitylist;
    }

    @Override
    public ArrayList<OrderDetail> getList() throws SQLException {
        return orderDetails;
    }
}
