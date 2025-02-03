package lk.ijse.gym_management.BO.Impl;

import lk.ijse.gym_management.BO.InventoryBO;
import lk.ijse.gym_management.DAO.DAOFactory;
import lk.ijse.gym_management.DAO.Impl.InventoryDaoImpl;
import lk.ijse.gym_management.DTO.InventoryDto;
import lk.ijse.gym_management.Entity.Inventory;

import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryBOImpl implements InventoryBO {
    InventoryDaoImpl inventoryDao=(InventoryDaoImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.INVENTORY);
    @Override
    public boolean isQtyReduced(int stock_qty, String supplimentId) throws SQLException {
        return inventoryDao.isQtyReduced(stock_qty,supplimentId);
    }

    @Override
    public int howManyQtyRemained(String supplimentId) throws SQLException {
        return inventoryDao.howManyQtyRemained(supplimentId);
    }

    @Override
    public String getNextId() throws SQLException {
        return inventoryDao.getNextId();
    }

    @Override
    public void setEntity(InventoryDto dto) throws SQLException {
        inventoryDao.setEntity(new Inventory(dto.getInventoryId(),dto.getSupplimentId(), dto.getStock_qty(), dto.getWholesale_price(),dto.getPurchase_date(),dto.getSupplier()));
    }

    @Override
    public InventoryDto getEntity() throws SQLException {
        Inventory inventory = inventoryDao.getEntity();
        InventoryDto inventoryDto=new InventoryDto(inventory.getInventoryId(),inventory.getSupplimentId(),inventory.getStock_qty(),inventory.getWholesale_price(),inventory.getPurchase_date(),inventory.getSupplier());
        return inventoryDto;
    }

    @Override
    public ArrayList<InventoryDto> getAll() throws SQLException {
        ArrayList<Inventory> inventoryList=inventoryDao.getAll();
        ArrayList<InventoryDto> inventoryDtoList=new ArrayList<>();
        for(Inventory inventory:inventoryList){
            InventoryDto inventory1=new InventoryDto(inventory.getInventoryId(),inventory.getSupplimentId(),inventory.getStock_qty(),inventory.getWholesale_price(),inventory.getPurchase_date(),inventory.getSupplier());
            inventoryDtoList.add(inventory1);
        }
        return inventoryDtoList;
    }

    @Override
    public boolean update() throws SQLException {
        return inventoryDao.update();
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return inventoryDao.delete(id);
    }

    @Override
    public boolean save() throws SQLException {
        return inventoryDao.save();
    }
}
