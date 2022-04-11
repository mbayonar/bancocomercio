package com.marcosbr.ejercicio.repositorio;

import com.marcosbr.ejercicio.util.Criterio;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Marcos
 */
public interface BaseRepositorio<Entidad, TipoLlave> {
    
    Entidad obtener(TipoLlave id);

    void crear(Entidad entidad);

    void actualizar(Entidad entidad);

    void grabarTodos(List<Entidad> list);
    
    Session getCurrentSession();

    List<Entidad> obtenerTodos();
    
    Entidad obtenerPorCriterio(Criterio filtro);

    Entidad obtenerInclusoEliminado(TipoLlave id);

    public List<Entidad> buscarPorCriteria(Criterio filtro);
    
    @SuppressWarnings("rawtypes")
    List proyeccionPorCriteria(Criterio filtro, Class resultado);
    
    public Object obtenerConResultSet(Criterio filtro, Class resultado);
    
}
