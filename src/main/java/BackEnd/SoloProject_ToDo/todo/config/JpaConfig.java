package BackEnd.SoloProject_ToDo.todo.config;

import BackEnd.SoloProject_ToDo.todo.entity.Todo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.CommandLinePropertySource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Configuration
public class JpaConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner jpaRunner(EntityManagerFactory emFactory){
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args -> {
            connectDB();
        };
    }


    private void connectDB(){
//        tx.begin();
//        em.persist(new Todo());
//        tx.commit();
//        Todo todo = em.find(Todo.class, 1L);
//
//        System.out.println("# memberId: " + todo.getTitle());
    }
}
