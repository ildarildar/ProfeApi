package cat.itb.projecte.taskkillerapi.model.services;


import cat.itb.projecte.taskkillerapi.model.entities.TodoItem;
import cat.itb.projecte.taskkillerapi.model.entities.TodoList;
import cat.itb.projecte.taskkillerapi.model.repositories.RepositoriTodoItem;
import cat.itb.projecte.taskkillerapi.model.repositories.RepositoriTodoList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServeiTodoList {

    private final RepositoriTodoList todoListRepo;
    private final RepositoriTodoItem todoItemRepo;

    public List<TodoList> llistarTodoLists() {
        return todoListRepo.findAll();
    }

   //donada una llista, retornar la llista dels seus items
    public List<TodoItem> llistarItemsDeLlista(int idLlista){
       return todoItemRepo.findByLlistaId(idLlista);
    }

    //consulta una llista per id
    public TodoList consultarTodoList(int id) {
        return todoListRepo.findById(id).orElse(null);
    }

    public TodoList afegirTodoList(TodoList li) {
        return todoListRepo.save(li);
    }

    public TodoList afegirNouItemALlista(int idLlista, TodoItem nou){
        TodoList llista=todoListRepo.findById(idLlista).orElse(null);
        if(llista!=null){
            nou.setLlista(llista);
            todoItemRepo.save(nou);
        }
        return llista;
    }

    public List<TodoItem> ordenarItemsLlistaPerNoFets(List<TodoItem> llista){
        // List<TodoItem> ordenada=llista.stream().sorted().collect(Collectors.toList());
        Collections.sort(llista, (x,y)->Boolean.compare(x.isFet(),y.isFet()));
        return llista;
    }

    public TodoList eliminarTodoList(int idLlista){
        TodoList res=todoListRepo.findById(idLlista).orElse(null);
        if(res!=null) todoListRepo.deleteById(idLlista);
        return res;
    }

    public List<TodoItem> eliminarItemDeLlista(int idLlista, int idItem){
        TodoList res=todoListRepo.findById(idLlista).orElse(null);
        if(res!=null) return todoItemRepo.deleteByLlistaIdAndIdItem(idLlista, idItem);
        else return null;
    }

    //modificar sencer, si existeix el canvia, sino retorna null
    public TodoList modificarLlista(TodoList llista){
        TodoList res=null;
        if(todoListRepo.existsById(llista.getId())) res=todoListRepo.save(llista);
        return res;
    }

}
