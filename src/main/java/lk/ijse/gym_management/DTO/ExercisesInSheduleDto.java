package lk.ijse.gym_management.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExercisesInSheduleDto {
    private String scheduleId;
    private String exerciseId;
    private int day;
    private int priorityNum;
    private int weight;
    private int sets;
    private  int reps;
    private int rest;
    private int tempo;
}
