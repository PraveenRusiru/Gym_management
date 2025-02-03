package lk.ijse.gym_management.DAO.Impl;

import javafx.scene.control.Alert;
import lk.ijse.gym_management.DAO.ClientContactDao;
import lk.ijse.gym_management.Entity.ClientContact;
import lk.ijse.gym_management.Utill.CRUDUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientContactDaoImpl implements ClientContactDao {
   static ClientContact clientContact;
    @Override
    public String getNextId() throws SQLException {
        return null;
    }

    @Override
    public void setEntity(ClientContact entity) throws SQLException {
            clientContact = entity;
    }

    @Override
    public ClientContact getEntity() throws SQLException {
        return clientContact;
    }

    @Override
    public ArrayList<ClientContact> getAll() throws SQLException {
        ArrayList<ClientContact> clientContactDtosDtos = new ArrayList<>();
        ResultSet rst= CRUDUtill.execute("select * from ClientContact order by Client_Id asc");
        while (rst.next()) {
            ClientContact clientContactDto1=new ClientContact();
            clientContactDto1.setNic(rst.getString(1));
            clientContactDto1.setPhone(rst.getString(2));
            clientContactDto1.setEmail(rst.getString(3));
            clientContactDto1.setClient_id(rst.getString(4));
            clientContactDtosDtos.add(clientContactDto1);
        }
        return clientContactDtosDtos;
    }

    @Override
    public boolean update() throws SQLException {
        ResultSet rst= CRUDUtill.execute("select NIC_Number from ClientContact where Client_Id=?",clientContact.getClient_id());
        String nic="";
        if(rst.next()){
            nic=rst.getString(1);
        }else{
            System.out.println("No such client");
        }
        boolean isClientContactUpdated=false;
        System.out.println("NIC "+nic);
        if(clientContact.getNic().equals(nic)){
            isClientContactUpdated=CRUDUtill.execute("update ClientContact set Phone_Number=?,Email=? where Client_Id=?",clientContact.getPhone(),clientContact.getEmail(),clientContact.getClient_id());
            if(isClientContactUpdated){
                new Alert(Alert.AlertType.INFORMATION,"Client Contact Updated").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Client Contact Not Updated").show();
            }
        }else{
            isClientContactUpdated=CRUDUtill.execute("update ClientContact set NIC_Number=?,Phone_Number=?,Email=? where Client_Id=?",clientContact.getNic(),clientContact.getPhone(),clientContact.getEmail(),clientContact.getClient_id());
            if(isClientContactUpdated){
//                new Alert(Alert.AlertType.INFORMATION,"Client Contact Updated").show();
                System.out.println("client contact updated");
            }else{
//                new Alert(Alert.AlertType.ERROR,"Client Contact Not Updated").show();
                System.out.println("client contact not updated");
            }
        }
        return isClientContactUpdated;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        boolean isClientContactDeleted=CRUDUtill.execute("delete from ClientContact where Client_Id=?",id);
        if(isClientContactDeleted){
//            new Alert(Alert.AlertType.INFORMATION,"Client Contact Deleted").show();
            System.out.println("Client Contact Deleted");
            return true;
        }else{
//            new Alert(Alert.AlertType.ERROR,"Client Contact  Delete Fail").show();
            System.out.println("Client Contact Delete Fail");
            return false;
        }
    }

    @Override
    public boolean save() throws SQLException {
        boolean isSaved= CRUDUtill.execute("insert into ClientContact values (?,?,?,?)",
                getEntity().getNic(),
                getEntity().getPhone(),
                getEntity().getEmail(),
                getEntity().getClient_id()
        );
        if(isSaved){
//            new Alert(Alert.AlertType.INFORMATION,"Client Contact Saved").show();
            System.out.println("Client Contact Saved");
            return true;
        }else{
//            new Alert(Alert.AlertType.ERROR,"Client Contact Save Fail").show();
            System.out.println("Client Contact Save Fail");
            return false;
        }
    }

    @Override
    public String getClientId(String clientNic) throws SQLException {
        ResultSet resultSet=CRUDUtill.execute("select client_id from ClientContact where NIC_Number=?",clientNic);
        String clientId="";
        if(resultSet.next()){
            clientId=resultSet.getString(1);
        }
        return clientId;
    }
}
