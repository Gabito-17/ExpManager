package edu.unam.pooproject.modelo;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "miembro")
@PrimaryKeyJoinColumn(name = "personas")
public class Miembro extends Persona {
    @ManyToMany(mappedBy = "miembros")
    private List<Reunion> reuniones;
}
