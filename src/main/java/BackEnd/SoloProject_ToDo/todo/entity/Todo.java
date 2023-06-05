package BackEnd.SoloProject_ToDo.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long todoId;

    private Todo(long todoId){
        this.todoId = todoId;
    }

    @Column
    private String title;
    private int todoOrder;
    private boolean completed;
}
