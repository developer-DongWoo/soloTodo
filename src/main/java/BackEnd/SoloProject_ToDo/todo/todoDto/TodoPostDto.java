package BackEnd.SoloProject_ToDo.todo.todoDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TodoPostDto {
    @NotBlank(message = "일정을 입력해주세요")
    String title;
    @NotNull(message = "todo-Order를 입력해주세요")
    int todoOrder;
    boolean completed;
}
