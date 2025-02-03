package lk.ijse.gym_management.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NutraionProgramme {
    private String programmeId;
    private  String sheduleId;
    private int day;
    private int calories;
    private int protein;
    private int carbs;
    private int fat;
}
