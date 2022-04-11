package com.marcosbr.ejercicio.servicio;

import com.marcosbr.ejercicio.entidad.Usuario;
import com.marcosbr.ejercicio.util.RespuestaControlador;

/**
 *
 * @author Marcos
 */
public interface UsuarioServicio extends BaseServicio<Usuario, Long> {
    
    public RespuestaControlador validarCredenciales(String usuario, String contrasena);
    
}
