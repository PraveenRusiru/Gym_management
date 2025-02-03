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
public class PaymentDto {
    private String paymentId;
    private String memberId;
    private String sheduleId;
    private double amount;
    private Date date;
    private String status;
}
