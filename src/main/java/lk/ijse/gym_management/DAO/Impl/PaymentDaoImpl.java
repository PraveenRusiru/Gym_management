package lk.ijse.gym_management.DAO.Impl;

import javafx.scene.control.Alert;
import lk.ijse.gym_management.DAO.DAOFactory;
import lk.ijse.gym_management.DAO.PaymentDao;
import lk.ijse.gym_management.DAO.SuperDAO;
import lk.ijse.gym_management.Entity.Payment;
import lk.ijse.gym_management.Utill.CRUDUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDaoImpl implements PaymentDao {
    private static Payment payment;
    MembershipDaoImpl memerbshipDao=(MembershipDaoImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MEMBERSHIP);
    SheduleDaoImpl sheduleDao=(SheduleDaoImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SHEDULE);
    @Override
    public String getNextId() throws SQLException {
        ResultSet rst = CRUDUtill.execute("select payment_id from Payment order by payment_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("P%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "P001"; // Return the default customer ID if no data is found
    }

    @Override
    public void setEntity(Payment entity) throws SQLException {
            payment = entity;
    }

    @Override
    public Payment getEntity() throws SQLException {
        return payment;
    }

    @Override
    public ArrayList<Payment> getAll() throws SQLException {
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
        boolean isSaved= CRUDUtill.execute("insert into Payment values (?,?,?,?,?,?)",
                payment.getPaymentId(),
                memerbshipDao.getEntity()==null?null:memerbshipDao.getEntity().getMemberId(),
                sheduleDao.getEntity()==null?null:sheduleDao.getEntity().getSheduleId(),
                payment.getAmount(),
                payment.getDate(),
                payment.getStatus()

        );
        if (isSaved) {
            System.out.println("Payment saved !");
            return true;
        }else{
            System.out.println("Payment not saved !");
            return false;
        }
    }

}
