package lk.ijse.gym_management.DAO.Impl;

import javafx.scene.control.Alert;
import lk.ijse.gym_management.DAO.InventoryDao;
import lk.ijse.gym_management.Entity.Inventory;
import lk.ijse.gym_management.Utill.CRUDUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryDaoImpl implements InventoryDao {
    static Inventory inventory;
    @Override
    public String getNextId() throws SQLException {
        ResultSet rst = CRUDUtill.execute("select inventory_id from Inventory order by inventory_id desc  limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("I%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "I001"; // Return the default customer ID if no data is found
    }

    @Override
    public void setEntity(Inventory entity) throws SQLException {
        inventory = entity;
    }

    @Override
    public Inventory getEntity() throws SQLException {
        return inventory;
    }

    @Override
    public ArrayList<Inventory> getAll() throws SQLException {
        ResultSet rst = CRUDUtill.execute("select * from Inventory");
        ArrayList<Inventory> inventoryDtos = new ArrayList<>();
        while (rst.next()) {
            Inventory inventoryDto = new Inventory();
            inventoryDto.setInventoryId(rst.getString(1));
            inventoryDto.setSupplimentId(rst.getString(2));
            inventoryDto.setStock_qty(rst.getInt(3));
            inventoryDto.setWholesale_price(rst.getDouble(4));
            inventoryDto.setPurchase_date(rst.getDate(5));
            inventoryDto.setSupplier(rst.getString(6));
            inventoryDtos.add(inventoryDto);
        }
        return inventoryDtos;
    }

    @Override
    public boolean update() throws SQLException {
        boolean isInventoryUpdated=CRUDUtill.execute("update Inventory set stock_qty=?,purchase_date=?,supplier=?,wholesale_price=? where inventory_id=?",
                inventory.getStock_qty(),
                inventory.getPurchase_date(),
                inventory.getSupplier(),
                inventory.getWholesale_price(),
                inventory.getInventoryId()
        );
        if(isInventoryUpdated){
            new Alert(Alert.AlertType.INFORMATION, "Inventory updated successfully").show();
        }
        else{
            new Alert(Alert.AlertType.ERROR, "Inventory update failed").show();
        }
        return isInventoryUpdated;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean save() throws SQLException {
        return CRUDUtill.execute("insert into Inventory (inventory_id, supplement_id, stock_qty, wholesale_price, purchase_date, supplier)  values (?,?,?,?,?,?)",
                inventory.getInventoryId(),
                inventory.getSupplimentId(),
                inventory.getStock_qty(),
                inventory.getWholesale_price(),
                inventory.getPurchase_date(),
                inventory.getSupplier()

        );
    }

    @Override
    public boolean isQtyReduced(int stock_qty,String supplimentId) throws SQLException {
        return CRUDUtill.execute("update Inventory set stock_qty=stock_qty-? where supplement_id=?",
                stock_qty,supplimentId
        );
    }

    @Override
    public int howManyQtyRemained(String supplimentId) throws SQLException {
        ResultSet rst=CRUDUtill.execute("select Inventory.stock_qty from Inventory where supplement_id=?",
                supplimentId
        );
        if(rst.next()){
            return rst.getInt(1);
        }else{
            System.out.println("howManyQtyRemained Error");
            return 0;
        }
    }
}
