package BackEnd.SoloProject_ToDo.todo.service;

import BackEnd.SoloProject_ToDo.todo.entity.Todo;
import BackEnd.SoloProject_ToDo.todo.exception.BusinessLogicException;
import BackEnd.SoloProject_ToDo.todo.exception.ExceptionCode;
import BackEnd.SoloProject_ToDo.todo.exception.GlobalExceptionAdvice;
import BackEnd.SoloProject_ToDo.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TodoService {
    private final TodoRepository todoRepository;
    public TodoService(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }
    public Todo createTodo(Todo todo){
        Todo saveTodo = todoRepository.save(todo);
        return saveTodo;
    }
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Todo updateTodo(Todo todo){
        Todo findTodo = findVerifiedTodo(todo.getTodoId());
        Optional.ofNullable(todo.getTitle())
                .ifPresent(title -> findTodo.setTitle(title));
        Optional.ofNullable(todo.getTodoOrder())
                .ifPresent(todoOrder -> findTodo.setTodoOrder(todoOrder));
        Optional.ofNullable(todo.isCompleted())
                .ifPresent(completed -> findTodo.setCompleted(completed));
        return todoRepository.save(findTodo);
    }

    @Transactional(readOnly = true)
    public Todo findTodo(long todoId){
        Todo findTodo = findVerifiedTodo(todoId);
        return findTodo;
    }

    @Transactional(readOnly = true)
    public List<Todo> findTodos(){
        List<Todo> findTodos = (List<Todo>) todoRepository.findAll();
        return findTodos;
    }

    public void deleteTodo(long todoId){
        todoRepository.deleteById(todoId);
        System.out.println(String.format("DELETED TODO ID : %d",todoId));
    }

    public void deleteAllTodo(){
        todoRepository.deleteAll();
        System.out.println("ALL DELETE");
    }


    public Todo findVerifiedTodo(long todoId){
        Optional<Todo> optionalTodo =
                todoRepository.findById(todoId);
        Todo todo = optionalTodo.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.TODO_NOT_FOUND));
        return todo;
    };
}
