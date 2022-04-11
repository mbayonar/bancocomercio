package com.marcosbr.ejercicio.servicio;

import com.marcosbr.ejercicio.excepcion.EntidadDuplicadaExcepcion;
import com.marcosbr.ejercicio.util.RespuestaControlador;
import java.util.List;

/**
 *
 * @author Marcos
 */
public interface BaseServicio<Entidad, TipoLlave> {

    Entidad obtener(TipoLlave id);

    void grabarTodos(List<Entidad> list) throws EntidadDuplicadaExcepcion;

    List<Entidad> obtenerTodos();

    public RespuestaControlador crear(Entidad entidad) throws EntidadDuplicadaExcepcion;

    public RespuestaControlador actualizar(Entidad entidad) throws EntidadDuplicadaExcepcion;

    public RespuestaControlador eliminar(TipoLlave entidadId);

}
