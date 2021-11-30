package cat.itb.projecte.taskkillerapi.model.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity

//Aquesta annotation s'ha de posar per quan fem consulta de TodoList per Id i no té
//TodoItem encara i retorna l'array buit.
//per quan es crida al  return repo.getById(id); al controlador de TodoList    @GetMapping("todolist/{id}")
@JsonIgnoreProperties(value={"handler","hibernateLazyInitializer", "fieldHandler", "tasques"})
public class TodoList {
    @Id
    @GeneratedValue
    private Integer id;
    private String nomLlista;

   //cascade = CascadeType.ALL per eliminar items de llista en cascada
   @OneToMany (mappedBy = "llista", cascade = CascadeType.ALL)
   private Collection<TodoItem> tasques;
}
