package cat.itb.projecte.taskkillerapi.model.repositories;



import cat.itb.projecte.taskkillerapi.model.entities.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Repository
public interface RepositoriTodoItem extends JpaRepository<TodoItem,Integer> {
    List<TodoItem> findByLlistaId(Integer idllista);
    List<TodoItem> deleteByLlistaIdAndIdItem(Integer llistaId, Integer idItem);
}
