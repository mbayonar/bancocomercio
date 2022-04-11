package com.marcosbr.ejercicio.entidad;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Marcos
 */
@MappedSuperclass
@JsonAutoDetect
@Getter
@Setter
public class AuditoriaEntidad implements Serializable {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @JsonIgnore
    @Column(name = "usuario_creacion", nullable = false, updatable = false, length = 50)
    private String usuarioCreacion;

    @JsonIgnore
    @Column(name = "ip_creacion", nullable = false, updatable = false, length = 50)
    private String ipCreacion;

    @JsonIgnore
    @Column(name = "pc_creacion", nullable = false, updatable = false, length = 50)
    private String pcCreacion;

    @JsonIgnore
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private Timestamp fechaCreacion;

    @JsonIgnore
    @Column(name = "usuario_modificacion", nullable = false, length = 50)
    private String usuarioModificacion;

    @JsonIgnore
    @Column(name = "pc_modificacion", nullable = false, length = 50)
    private String pcModificacion;

    @JsonIgnore
    @Column(name = "ip_modificacion", nullable = false, length = 50)
    private String ipModificacion;

    @JsonIgnore
    @Column(name = "fecha_modificacion", nullable = false)
    private Timestamp fechaModificacion;

    @Transient
    private Boolean eliminar;

    @Transient
    private Object extraInfo;

    public AuditoriaEntidad() {
        eliminar = Boolean.FALSE;
    }

    public AuditoriaEntidad(Long id) {
        this();
        this.id = id;
    }

    protected void copy(final AuditoriaEntidad source) {
        this.usuarioCreacion = source.usuarioCreacion;
        this.fechaCreacion = source.fechaCreacion;
        this.usuarioModificacion = source.usuarioModificacion;
    }

    public boolean esNuevo() {
        return this.id == null && this.usuarioCreacion == null && this.fechaCreacion == null;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AuditoriaEntidad other = (AuditoriaEntidad) obj;
        return Objects.equals(this.id, other.id);
    }

}
