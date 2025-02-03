package lk.ijse.gym_management.DAO.Impl;

import javafx.scene.control.Alert;
import lk.ijse.gym_management.DAO.SheduleDao;
import lk.ijse.gym_management.Entity.Shedule;
import lk.ijse.gym_management.Utill.CRUDUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SheduleDaoImpl implements SheduleDao {
    private static Shedule shedule;
    @Override
    public String getNextId() throws SQLException {
        ResultSet rst = CRUDUtill.execute("select schedule_id from Schedule order by schedule_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(4); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("SHDL%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "SHDL001"; // Return the default customer ID if no data is found
    }

    @Override
    public void setEntity(Shedule entity) throws SQLException {
        shedule = entity;
    }

    @Override
    public Shedule getEntity() throws SQLException {
        return shedule;
    }

    @Override
    public ArrayList<Shedule> getAll() throws SQLException {
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
        boolean isSaved= CRUDUtill.execute("insert into Schedule values (?,?,?,?,?)",
                shedule.getSheduleId(),
                shedule.getClientId(),
                shedule.getIssuedDate(),
                shedule.getEndDate(),
                shedule.getGoal()
        );

        if(isSaved){
//            new Alert(Alert.AlertType.INFORMATION,"Successfully saved shedule").show();
            System.out.println("Successfully saved shedule");
            return true;
        }else{
//            new Alert(Alert.AlertType.ERROR,"Something went wrong in saving Shedule !").show();
            System.out.println("Something went wrong in saving Shedule !");
            return false;
        }
    }
}
