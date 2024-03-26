package edu.unam.pooproject.modelo;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "involucrado")
@PrimaryKeyJoinColumn(name = "personas")
public class Involucrado extends Persona {
    @ManyToMany(mappedBy = "involucrados")
    private List<Expediente> expedientes;

}
