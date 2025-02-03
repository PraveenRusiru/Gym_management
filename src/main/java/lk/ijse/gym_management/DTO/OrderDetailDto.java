package lk.ijse.gym_management.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderDetailDto {
    private String orderId;
    private String productId;
    private int qty;
    private double unitPrice;
}
