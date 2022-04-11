package com.marcosbr.ejercicio.repositorio.impl;

import com.marcosbr.ejercicio.entidad.Persona;
import com.marcosbr.ejercicio.repositorio.PersonaRepositorio;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marcos
 */
@Repository
public class PersonaRepositorioImpl extends BaseRepositorioImpl<Persona, Long> implements PersonaRepositorio {

}
