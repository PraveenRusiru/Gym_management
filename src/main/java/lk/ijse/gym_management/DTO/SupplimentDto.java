package lk.ijse.gym_management.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SupplimentDto {
    private String supplimentId;
    private String supplimentTitle;
    private String supplimentDescription;
    private String supplimentCategory;
    private double supplimentPrice;
}
