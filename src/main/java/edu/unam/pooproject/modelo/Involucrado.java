package edu.unam.pooproject.modelo;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "involucrado")
@PrimaryKeyJoinColumn(name = "personas")
public class Involucrado extends Persona {
}
