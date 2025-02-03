package lk.ijse.gym_management.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Suppliment {
    private String supplimentId;
    private String supplimentTitle;
    private String supplimentDescription;
    private String supplimentCategory;
    private double supplimentPrice;
}
