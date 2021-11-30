package cat.itb.projecte.taskkillerapi.controllers;


import cat.itb.projecte.taskkillerapi.model.entities.TodoItem;
import cat.itb.projecte.taskkillerapi.model.entities.TodoList;
import cat.itb.projecte.taskkillerapi.model.services.ServeiTodoList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ControladorTodoListRest {

    private final ServeiTodoList serveiTodoList;


    /* POST
    {
    "nomLlista":"llista de la compra del Mercadona",
    }

     */
    @PostMapping("/todolists")
    public ResponseEntity<?> novaTodoList(@RequestBody TodoList nova) {
        TodoList res = serveiTodoList.afegirTodoList(nova);
        return new ResponseEntity<TodoList>(res, HttpStatus.CREATED);
    }


    @GetMapping("/todolists")
    public ResponseEntity<?> obtenirTotesLesLlistes() {
        List<TodoList> llista = serveiTodoList.llistarTodoLists();
        if (llista.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else return ResponseEntity.ok(llista);
    }


    @GetMapping("todolists/{id}")
    public ResponseEntity<?> obtenirUnaLlista(@PathVariable int id) {
        TodoList res = serveiTodoList.consultarTodoList(id);
        if (res != null) {
            return ResponseEntity.ok(res);
        } else return ResponseEntity.notFound().build();
    }


    /*
    PUT
    {
    "id":2,
    "nomLlista": "Nom modificat de la llista,
    }
     */
    @PutMapping("/todolists")
    public ResponseEntity<?> modificarLlista(@RequestBody TodoList llistaMod) {
        TodoList res = serveiTodoList.modificarLlista(llistaMod);
        if (res != null) {
            return ResponseEntity.ok(res);
        } else return ResponseEntity.notFound().build();
    }


    //elimina llista i elimina en cascada tots els items de llista
    @DeleteMapping("/todolists/{idLlista}")
    public ResponseEntity<?> eliminarUnaLlista(@PathVariable Integer idLlista) {
        TodoList res = serveiTodoList.eliminarTodoList(idLlista);
        if (res != null) {
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/todolists/{idList}/todoitems/{idItem}")
    public ResponseEntity<?> eliminarItemDeLLlista(@PathVariable Integer idList, @PathVariable Integer idItem) {
        List<TodoItem> res = serveiTodoList.eliminarItemDeLlista(idList, idItem);
        if (res != null) {
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.notFound().build();
    }

    //OK Postman, però potser interessa tenir un DTO que no retorni l'id de llista, només el nomLlista
    @GetMapping("/todolists/{idList}/todoitems")
    public ResponseEntity<?> obtenirTotsItemsDeLlista(@PathVariable int idList) {
        if(serveiTodoList.consultarTodoList(idList)==null){
            return ResponseEntity.notFound().build();
        }
        else {
            List<TodoItem> res = serveiTodoList.llistarItemsDeLlista(idList);
            return ResponseEntity.ok(res);
        }
    }





    /*
    POST /todolists/3/todoitems
    {
    "descripcio":"rucula",
    "fet":false
    }
     */
    @PostMapping("/todolists/{idList}/todoitems")
    public ResponseEntity<?> afegirItemALlista(@PathVariable int idList, @RequestBody TodoItem nou) {
        return afegirModificar(idList,nou);
    }

    public ResponseEntity<?> afegirModificar(@PathVariable int idList, @RequestBody TodoItem nou) {
        TodoList res = serveiTodoList.afegirNouItemALlista(idList, nou);
        if (res != null) return ResponseEntity.ok(res);
        else return ResponseEntity.notFound().build();
    }


    @PutMapping("/todolists/{idList}/todoitems")
    public ResponseEntity<?> modificarItemALlista(@PathVariable int idList, @RequestBody TodoItem nou) {
    /*
    en aquesta versió, si el PUT és d'un item que no existeix, també el crearà, no hauria de passar
    però en la web no es pot modificar un ítem que no està creat prèviament
     */
        return afegirModificar(idList,nou);
    }


}
