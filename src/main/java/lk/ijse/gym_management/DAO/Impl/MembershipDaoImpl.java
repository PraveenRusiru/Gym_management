package lk.ijse.gym_management.DAO.Impl;

import javafx.scene.control.Alert;
import lk.ijse.gym_management.DAO.MembershipDao;
import lk.ijse.gym_management.Entity.Membership;
import lk.ijse.gym_management.Utill.CRUDUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MembershipDaoImpl implements MembershipDao {
   private static Membership membership;
    @Override
    public String getNextId() throws SQLException {
        ResultSet rst = CRUDUtill.execute("select member_id from Membership order by member_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("M%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "M001"; // Return the default customer ID if no data is found
    }

    @Override
    public void setEntity(Membership entity) throws SQLException {
            membership = entity;
    }

    @Override
    public Membership getEntity() throws SQLException {
        return membership;
    }

    @Override
    public ArrayList<Membership> getAll() throws SQLException {
        ResultSet rst=CRUDUtill.execute("select * from Membership");
        ArrayList<Membership> membershiplist=new ArrayList<>();
        while (rst.next()) {
            Membership membership=new Membership(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDate(3),
                    rst .getDate(4),
                    rst.getString(5)
            );
            membershiplist.add(membership);
        }
        return membershiplist;
    }

    @Override
    public boolean update() throws SQLException {
        System.out.println(membership.getStartDate()+" "+membership.getEndDate()+" "+membership.getMembershipType()+" "+membership.getMemberId());
        boolean updated=CRUDUtill.execute("update Membership set start_date=?,end_date=?,membership_type=? where member_id=?",membership.getStartDate(),membership.getEndDate(),membership.getMembershipType(),membership.getMemberId());
        if(updated){
            System.out.println("Membership updated");
            return true;
        }else{
            System.out.println("Membership not updated");
            return false;
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        boolean isMembershipDeleted=CRUDUtill.execute("delete from Membership where member_id=?", id);
        if(isMembershipDeleted){
            //new Alert(Alert.AlertType.INFORMATION, "Membership deleted").show();
            System.out.println("Membership deleted");
            return true;
        }else{
            //new Alert(Alert.AlertType.ERROR, "Membership could not be deleted").show();
            System.out.println("Membership could not be deleted");
            return false;
        }
    }

    @Override
    public boolean save() throws SQLException {
        boolean isSaved=CRUDUtill.execute("insert into Membership values (?,?,?,?,?)",
                membership.getMemberId(),
                membership.getClientId(),
                membership.getStartDate(),
                membership.getEndDate(),
                membership.getMembershipType()
        );
        if(isSaved){
            System.out.println("Membership saved");
            return true;
        }else{
            System.out.println("Membership not saved");
            return false;
        }
    }
}
