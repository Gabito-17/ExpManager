package edu.unam.pooproject.modelo;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "miembro")
@PrimaryKeyJoinColumn(name = "personas")
public class Miembro extends Persona {
}
