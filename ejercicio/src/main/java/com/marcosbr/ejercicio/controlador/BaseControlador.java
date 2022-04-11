package com.marcosbr.ejercicio.controlador;

import com.marcosbr.ejercicio.util.RespuestaControlador;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Marcos
 */
public interface BaseControlador<Entidad, TipoLlave> {

    public ResponseEntity<RespuestaControlador> crear(Entidad entidad);

    public ResponseEntity<RespuestaControlador> obtener(TipoLlave id);

    public ResponseEntity<RespuestaControlador> actualizar(Entidad entidad);

    public ResponseEntity<RespuestaControlador> eliminar(TipoLlave entidadId);

    public ResponseEntity<RespuestaControlador> obtenerTodos();

}
