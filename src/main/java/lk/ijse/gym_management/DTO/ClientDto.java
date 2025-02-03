package lk.ijse.gym_management.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientDto {
    private String clientId;
    private String clientName;
    private int age;
    private String gender;
    private String goal;
    private int height;
    private int weight;
    private int fat_per;
    private Date joined_date;
}
