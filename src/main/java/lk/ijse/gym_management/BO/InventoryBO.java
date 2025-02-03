package lk.ijse.gym_management.BO;

import lk.ijse.gym_management.DTO.InventoryDto;

import java.sql.SQLException;

public interface InventoryBO extends SuperBO<InventoryDto> {
    public boolean isQtyReduced(int stock_qty,String supplimentId) throws SQLException;
    public int howManyQtyRemained(String supplimentId) throws SQLException;
}
