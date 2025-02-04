package lk.ijse.gym_management.DAO.Impl;

import javafx.scene.control.Alert;
import lk.ijse.gym_management.DAO.AdminsDao;
import lk.ijse.gym_management.Entity.Admins;
import lk.ijse.gym_management.Utill.CRUDUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminsDaoImpl implements AdminsDao {

    @Override
    public String getNextId() throws SQLException {
        return "";
    }

    @Override
    public void setEntity(Admins entity) throws SQLException {

    }

    @Override
    public Admins getEntity() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Admins> getAll() throws SQLException {
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
        return false;
    }

    @Override
    public boolean isUserValide(String username, String password) throws SQLException {
        boolean isValid = false;
        ResultSet rst = CRUDUtill.execute("SELECT AES_DECRYPT(password, 'your_secret_key') AS decrypted_password FROM Admin WHERE admin_name = ?",username );
        if(rst.next()){
            String decrypted_password = rst.getString("decrypted_password");
            isValid= password.equals(decrypted_password);
        }
        return isValid;
    }

    @Override
    public boolean isPasswordUpdated(String newPasswrod) throws SQLException {
        boolean isUpdated = CRUDUtill.execute("UPDATE Admin SET password=AES_ENCRYPT(?,'your_secret_key') WHERE admin_id='A001'",newPasswrod);
        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Password updated successfully.").show();
            return true;
        }else{
            new Alert(Alert.AlertType.ERROR,"Password not updated successfully.").show();
            return false;
        }
    }
}
