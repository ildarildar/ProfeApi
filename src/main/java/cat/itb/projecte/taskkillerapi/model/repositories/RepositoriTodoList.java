package cat.itb.projecte.taskkillerapi.model.repositories;


import cat.itb.projecte.taskkillerapi.model.entities.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface RepositoriTodoList extends JpaRepository<TodoList, Integer> {
}
