package lk.ijse.gym_management.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class InventoryDto {
    private String inventoryId;
    private String supplimentId;
    private int stock_qty;
    private double wholesale_price;
    private Date purchase_date;
    private String supplier;
}
