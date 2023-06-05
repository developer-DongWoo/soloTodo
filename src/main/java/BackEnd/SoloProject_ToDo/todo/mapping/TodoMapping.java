package BackEnd.SoloProject_ToDo.todo.mapping;

import BackEnd.SoloProject_ToDo.todo.entity.Todo;
import BackEnd.SoloProject_ToDo.todo.todoDto.TodoPatchDto;
import BackEnd.SoloProject_ToDo.todo.todoDto.TodoPostDto;
import BackEnd.SoloProject_ToDo.todo.todoDto.TodoResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoMapping {
    Todo todoPostDtoToTodo(TodoPostDto todoPostDto);
    Todo todoPatchDtoToTodo(TodoPatchDto todoPatchDto);
    public TodoResponseDto todoToTodoResponseDto(Todo todo);

}
