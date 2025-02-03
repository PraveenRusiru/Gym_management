package lk.ijse.gym_management.DAO.Impl;

import lk.ijse.gym_management.DAO.NutrationDao;
import lk.ijse.gym_management.DTO.NutraionProgrammeDto;
import lk.ijse.gym_management.Entity.NutraionProgramme;
import lk.ijse.gym_management.Utill.CRUDUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NutrationProgrammeDaoImpl implements NutrationDao {
   private static NutraionProgramme nutraionProgramme;
    @Override
    public String getNextId() throws SQLException {
        ResultSet rst = CRUDUtill.execute("select id from Nutrition_Programme order by id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(2); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("NP%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "NP001"; // Return the default customer ID if no data is found
    }

    @Override
    public void setEntity(NutraionProgramme entity) throws SQLException {
            nutraionProgramme=entity;
    }

    @Override
    public NutraionProgramme getEntity() throws SQLException {
        return nutraionProgramme;
    }

    @Override
    public ArrayList<NutraionProgramme> getAll() throws SQLException {
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
        boolean isSaved= CRUDUtill.execute("insert into Nutrition_Programme values (?,?,?,?,?,?,?)",
                nutraionProgramme.getProgrammeId(),
                nutraionProgramme.getSheduleId(),
                nutraionProgramme.getDay(),
                nutraionProgramme.getCalories(),
                nutraionProgramme.getProtein(),
                nutraionProgramme.getCarbs(),
                nutraionProgramme.getFat()
        );
        if(isSaved){
            System.out.println("Nutration progrmme saved");
            return true;
        }else{
            System.out.println("Nutration progrmme not saved");
            return false;
        }
    }
}
