package BackEnd.SoloProject_ToDo.todo.repository;

import BackEnd.SoloProject_ToDo.todo.entity.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {

}
