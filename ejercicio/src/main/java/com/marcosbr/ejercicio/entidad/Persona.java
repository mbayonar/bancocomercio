package com.marcosbr.ejercicio.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

/**
 *
 * @author Marcos
 */
@Entity
@Table(name = "persona")
@DynamicUpdate(value = true)
@DynamicInsert(value = true)
@SelectBeforeUpdate
@Data
public class Persona extends AuditoriaEntidad {

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "num_documento", nullable = false, length = 8)
    private String numDocumento;

    @Column(name = "direccion", nullable = true)
    private String direccion;

    @Column(name = "telefono", nullable = false, length = 9)
    private String telefono;

    @Column(name = "email", nullable = true)
    private String email;
    
}
