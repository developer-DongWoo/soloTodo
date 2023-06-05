package BackEnd.SoloProject_ToDo.todo.todoDto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class TodoResponseDto {
    private long todoId;
    private String title;
    private int todoOrder;
    private boolean completed;
}
