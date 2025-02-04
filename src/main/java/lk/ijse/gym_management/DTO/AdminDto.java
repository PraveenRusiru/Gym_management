package lk.ijse.gym_management.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AdminDto {
    private String adminId;
    private String adminName;
    private String Password;
}
