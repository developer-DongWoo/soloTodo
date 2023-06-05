package BackEnd.SoloProject_ToDo.todo.todoDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoPatchDto {
    long todoId;
    String title;
    int todoOrder;
    boolean completed;
}
