/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcosbr.ejercicio.servicio.impl;

import com.marcosbr.ejercicio.entidad.Persona;
import com.marcosbr.ejercicio.enums.NombreEntidad;
import com.marcosbr.ejercicio.excepcion.EntidadDuplicadaExcepcion;
import com.marcosbr.ejercicio.repositorio.PersonaRepositorio;
import com.marcosbr.ejercicio.servicio.PersonaServicio;
import com.marcosbr.ejercicio.util.RespuestaControlador;
import com.marcosbr.ejercicio.util.RespuestaControladorServicio;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marcos
 */
@Service
public class PersonaServicioImpl extends BaseServicioImpl<Persona, Long> implements PersonaServicio {

    private final Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private RespuestaControladorServicio respuestaControladorServicio;

    @Autowired
    private PersonaRepositorio personaRepositorio;

    @Autowired
    public PersonaServicioImpl(PersonaRepositorio personaRepositorio) {
        super(personaRepositorio);
    }

    @Override
    public RespuestaControlador crear(Persona persona) throws EntidadDuplicadaExcepcion {
        this.personaRepositorio.crear(persona);
        return this.respuestaControladorServicio.obtenerRespuestaDeExitoCrearConData(NombreEntidad.PERSONA.getValor(), persona.getId());
    }

    @Override
    public RespuestaControlador actualizar(Persona persona) throws EntidadDuplicadaExcepcion {
        this.personaRepositorio.actualizar(persona);
        return respuestaControladorServicio.obtenerRespuestaDeExitoActualizar(NombreEntidad.PERSONA.getValor());
    }

    @Override
    public RespuestaControlador eliminar(Long personaId) {
        RespuestaControlador respuesta;
        Persona persona;
        Boolean puedeEliminar;

        puedeEliminar = true;

        if (puedeEliminar == null || !puedeEliminar) {
            respuesta = RespuestaControlador.obtenerRespuestaDeError("El " + NombreEntidad.PERSONA.getValor().toLowerCase() + " ha sido asignado a uno o varios usuarios y no se puede eliminar");
        } else {
            persona = personaRepositorio.obtener(personaId);
            persona.setEstado(Boolean.FALSE);
            personaRepositorio.actualizar(persona);
            respuesta = respuestaControladorServicio.obtenerRespuestaDeExitoEliminar(NombreEntidad.PERSONA.getValor());
        }

        return respuesta;
    }
}
