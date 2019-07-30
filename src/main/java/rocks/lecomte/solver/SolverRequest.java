package rocks.lecomte.solver;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Data
@ToString
public class SolverRequest {
    private String tenant;
    private List<Room> rooms;
    private List<Teacher>teachers;
    private String identifier;

}
