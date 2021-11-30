package cat.itb.projecte.taskkillerapi.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class TodoItem {
    @Id
    @GeneratedValue
    private int idItem;
    private String descripcio;
    private boolean fet;

    @ManyToOne
    @JoinColumn
    private TodoList llista;
}

