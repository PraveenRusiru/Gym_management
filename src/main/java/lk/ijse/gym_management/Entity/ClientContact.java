package lk.ijse.gym_management.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientContact {
    private String nic;
    private String phone;
    private String email;
    private String client_id;
}
