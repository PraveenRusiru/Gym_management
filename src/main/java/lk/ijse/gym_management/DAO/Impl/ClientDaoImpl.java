package lk.ijse.gym_management.DAO.Impl;

import com.mysql.cj.xdevapi.Client;
import javafx.scene.control.Alert;
import lk.ijse.gym_management.DAO.ClientDao;
import lk.ijse.gym_management.DTO.ClientDto;
import lk.ijse.gym_management.Entity.Clients;
import lk.ijse.gym_management.Utill.CRUDUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientDaoImpl implements ClientDao {
    static Clients client;
    @Override
    public String getNextId() throws SQLException {

        ResultSet rst = CRUDUtill.execute("select client_id from Client order by client_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("C%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "C001"; // Return the default customer ID if no data is found
    }

    @Override
    public void setEntity(Clients entity) throws SQLException {
        client = entity;
    }

    @Override
    public Clients getEntity() throws SQLException {
        return client;
    }

    @Override
    public ArrayList<Clients> getAll() throws SQLException {
        ArrayList<Clients> clientEntities = new ArrayList<>();
        ResultSet rst=CRUDUtill.execute("select * from Client");
        while (rst.next()) {
            Clients clientEnty=new Clients();
            clientEnty.setClientId(rst.getString(1));
            clientEnty.setClientName(rst.getString(2));
            clientEnty.setAge(rst.getInt(3));
            clientEnty.setGender(rst.getString(4));
            clientEnty.setGoal(rst.getString(5));
            clientEnty.setHeight(rst.getInt(6));
            clientEnty.setWeight(rst.getInt(7));
            clientEnty.setFat_per(rst.getInt(8));
            clientEnty.setJoined_date(rst.getDate(9));
            clientEntities.add(clientEnty);
        }
        return clientEntities;
    }

    @Override
    public boolean update() throws SQLException {
        boolean isClientDataUpdated= CRUDUtill.execute("update Client set client_name =? ,age=?,gender=?,goal=?,height=?,weight=?,fat_per=?,joined_date=? where client_id=?",client.getClientName(),client.getAge(),client.getGender(),client.getGoal(),client.getHeight(),client.getWeight(),client.getFat_per(),client.getJoined_date(),client.getClientId());
        if(isClientDataUpdated){
//            new Alert(Alert.AlertType.INFORMATION,"Updated Successfully").show();
            System.out.println("Updated Successfully");
            return true;
        }else{
//            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
            System.out.println("Something went wrong in updaing");
            return false;
        }

    }

    @Override
    public boolean delete(String id) throws SQLException {
        boolean isaffectedRows=CRUDUtill.execute("delete from Client where client_id=?",id);
        if(isaffectedRows){
//            new Alert(Alert.AlertType.INFORMATION,"Deleted Successfully").show();
            System.out.println("Deleted Successfully");
            return true;
        }else{
//            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
            System.out.println("Deleted unsuccessful");
            return false;
        }

    }

    @Override
    public boolean save() throws SQLException {
        boolean isSaved= CRUDUtill.execute("insert into Client values (?,?,?,?,?,?,?,?,?)",
                getEntity().getClientId(),
                getEntity().getClientName(),
                getEntity().getAge(),
                getEntity().getGender(),
                getEntity().getGoal(),
                getEntity().getHeight(),
                getEntity().getWeight(),
                getEntity().getFat_per(),
                getEntity().getJoined_date()
        );
        if(isSaved){
//            new Alert(Alert.AlertType.INFORMATION,"Saved Successfully").show();
            System.out.println("Client Saved Successfully");
            return true;
        }else{
//            new Alert(Alert.AlertType.ERROR,"Something went wrong in saving Client").show();
            System.out.println("Client Saving Failed");
            return false;
        }

    }
}
