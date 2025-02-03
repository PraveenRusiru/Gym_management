package lk.ijse.gym_management.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Shedule {
    private String sheduleId;
    private String clientId;
    private Date issuedDate;
    private Date endDate;
    private String goal;
}
