package lk.ijse.gym_management.DAO;

import lk.ijse.gym_management.Entity.Inventory;

import java.sql.SQLException;

public interface InventoryDao extends SuperDAO<Inventory> {
    public boolean isQtyReduced(int stock_qty,String supplimentId) throws SQLException ;
    public int howManyQtyRemained(String supplimentId) throws SQLException;
}
