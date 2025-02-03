package lk.ijse.gym_management.DAO.Impl;

import lk.ijse.gym_management.DAO.NoteForClientDao;
import lk.ijse.gym_management.Entity.NoteForClient;
import lk.ijse.gym_management.Utill.CRUDUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NoteForClientDaoImpl implements NoteForClientDao {
    static NoteForClient noteForClient;
    @Override
    public String getNextId() throws SQLException {
        ResultSet rst = CRUDUtill.execute("select note_id from Note_for_Client order by note_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(2); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("NC%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "NC001"; // Return the default customer ID if no data is found
    }

    @Override
    public void setEntity(NoteForClient entity) throws SQLException {
        noteForClient=entity;
    }

    @Override
    public NoteForClient getEntity() throws SQLException {
        return noteForClient;
    }

    @Override
    public ArrayList<NoteForClient> getAll() throws SQLException {
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

           boolean isSaved= CRUDUtill.execute("insert into Note_for_Client values (?,?,?)",
                    noteForClient.getNoteId(),
                    noteForClient.getClientId(),
                    noteForClient.getDescription()
            );
           if(isSaved){
               System.out.println("Client notes has Saved !");
               return true;
           }else{
               System.out.println("Client notes has not Saved !");
               return false;
           }
    }

}
