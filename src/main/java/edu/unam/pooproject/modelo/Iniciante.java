package edu.unam.pooproject.modelo;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "iniciante")
@PrimaryKeyJoinColumn(name = "personas")
public class Iniciante extends Persona {

    @OneToMany(mappedBy = "iniciante")
    private List<Expediente> expedientes;
}

