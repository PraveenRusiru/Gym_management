package lk.ijse.gym_management.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Payment {
    private String paymentId;
    private String memberId;
    private String sheduleId;
    private double amount;
    private Date date;
    private String status;
}
