package com.marcosbr.ejercicio.controlador;

import com.marcosbr.ejercicio.entidad.Persona;
import com.marcosbr.ejercicio.enums.NombreEntidad;
import com.marcosbr.ejercicio.servicio.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marcos
 */
@RestController
//@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/persona")
public class PersonaControlador extends BaseControladorImpl<Persona, Long> implements BaseControlador<Persona, Long> {

    @Autowired
    public PersonaControlador(PersonaServicio personaServicio) {
        super(personaServicio, NombreEntidad.PERSONA.getValor());
    }

}