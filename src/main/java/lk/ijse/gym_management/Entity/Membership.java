package lk.ijse.gym_management.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Membership {
    private String memberId;
    private String clientId;
    private Date startDate;
    private Date endDate;
    private String membershipType;
}
