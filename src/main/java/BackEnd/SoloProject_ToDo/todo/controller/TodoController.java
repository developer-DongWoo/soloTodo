package BackEnd.SoloProject_ToDo.todo.controller;


import BackEnd.SoloProject_ToDo.todo.entity.Todo;
import BackEnd.SoloProject_ToDo.todo.mapping.TodoMapping;
import BackEnd.SoloProject_ToDo.todo.service.TodoService;
import BackEnd.SoloProject_ToDo.todo.todoDto.TodoPatchDto;
import BackEnd.SoloProject_ToDo.todo.todoDto.TodoPostDto;
import BackEnd.SoloProject_ToDo.todo.todoDto.TodoResponseDto;
import BackEnd.SoloProject_ToDo.todo.utils.UriCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@Validated
public class TodoController {
    private final static String MEMBER_DEFAULT_URL = "/";

    private final TodoService todoService;
    private final TodoMapping todoMapping;

    public TodoController(TodoService todoService, TodoMapping todoMapping){
        this.todoService = todoService;
        this.todoMapping = todoMapping;
    }
    @PostMapping
    public ResponseEntity postTodo(@Valid @RequestBody TodoPostDto todoPostDto){
        Todo todo = todoMapping.todoPostDtoToTodo(todoPostDto);
        Todo serviceResult = todoService.createTodo(todo);
        URI location = UriCreator.createUri(MEMBER_DEFAULT_URL, serviceResult.getTodoId());
        return ResponseEntity.created(location).build();
//        return new ResponseEntity<>(todoMapping.todoToTodoResponseDto(serviceResult), HttpStatus.CREATED);
    }

    @GetMapping("{todo-id}")
    public ResponseEntity getTodo(@PathVariable("todo-id") long todoId){
        Todo serviceResult = todoService.findTodo(todoId);
        return new ResponseEntity<>(todoMapping.todoToTodoResponseDto(serviceResult), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTodos(){
        List<Todo> serviceResult = todoService.findTodos();
        List<TodoResponseDto> todoResponseDtoList =
                serviceResult.stream()
                        .map(todo -> todoMapping.todoToTodoResponseDto(todo))
                        .collect(Collectors.toList());
        return new ResponseEntity<>(todoResponseDtoList,HttpStatus.OK);
    }

    @PatchMapping("{todo-id}")
    public ResponseEntity patchTodo(@PathVariable("todo-id") long todoId,
                                    @RequestBody TodoPatchDto todoPatchDto){
        todoPatchDto.setTodoId(todoId);
        Todo todo = todoMapping.todoPatchDtoToTodo(todoPatchDto);
        Todo serviceResult = todoService.updateTodo(todo);

        return new ResponseEntity<>(todoMapping.todoToTodoResponseDto(serviceResult), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteTodos(){
        todoService.deleteAllTodo();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{todo-id}")
    public ResponseEntity deleteTodo(@PathVariable("todo-id") long todoId){
        todoService.deleteTodo(todoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
