package lk.ijse.gym_management.DAO.Impl;

import javafx.scene.control.Alert;
import lk.ijse.gym_management.DAO.SupplimentDao;
import lk.ijse.gym_management.DTO.SupplimentDto;
import lk.ijse.gym_management.Entity.Suppliment;
import lk.ijse.gym_management.Utill.CRUDUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplimentDaoImpl implements SupplimentDao {
    static Suppliment suppliment;

    @Override
    public String getNextId() throws SQLException {
        ResultSet rst = CRUDUtill.execute("select Supplement.supplement_id from Supplement order by Supplement.supplement_id desc  limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(3); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("Sup%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "Sup001"; // Return the default customer ID if no data is found

    }

    @Override
    public void setEntity(Suppliment entity) throws SQLException {
        suppliment = entity;
    }

    @Override
    public Suppliment getEntity() throws SQLException {
        return suppliment;
    }

    @Override
    public ArrayList<Suppliment> getAll() throws SQLException {
        ArrayList<Suppliment> suppliementDtos = new ArrayList<>();
        ResultSet rst = CRUDUtill.execute("select * from Supplement");
        while (rst.next()) {
            Suppliment suppliement = new Suppliment();
            suppliement.setSupplimentId(rst.getString(1));
            suppliement.setSupplimentTitle(rst.getString(2));
            suppliement.setSupplimentDescription(rst.getString(3));
            suppliement.setSupplimentPrice(rst.getDouble(5));
            suppliement.setSupplimentCategory(rst.getString(4));
            suppliementDtos.add(suppliement);

        }
        return suppliementDtos;
    }

    @Override
    public boolean update() throws SQLException {
        boolean isSupplimentUpdated=CRUDUtill.execute("update Supplement set title=?,description=?,price=?,Category=? where supplement_id=?",
                suppliment.getSupplimentTitle(),
                suppliment.getSupplimentDescription(),
                suppliment.getSupplimentPrice(),
                suppliment.getSupplimentCategory(),
                suppliment.getSupplimentId()
        );

        if(isSupplimentUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Suppliment Updated").show();
        }else{
            new Alert(Alert.AlertType.INFORMATION,"Suppliment Update Failed").show();
        }
        return isSupplimentUpdated;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        boolean isSupplimentDeleted=CRUDUtill.execute("delete  from Supplement  where supplement_id=?",id);
        if(isSupplimentDeleted){
            new Alert(Alert.AlertType.INFORMATION,"Suppliment Deleted").show();
        }else{
            new Alert(Alert.AlertType.INFORMATION,"Suppliment Delete Failed").show();
        }
        return isSupplimentDeleted;
    }

    @Override
    public boolean save() throws SQLException {
        return CRUDUtill.execute("insert into Supplement values (?,?,?,?,?)",
                suppliment.getSupplimentId(),
                suppliment.getSupplimentTitle(),
                suppliment.getSupplimentDescription(),
                suppliment.getSupplimentCategory(),
                suppliment.getSupplimentPrice()
        );
    }
}
