package com.marcosbr.ejercicio.servicio.impl;

import com.marcosbr.ejercicio.entidad.Usuario;
import com.marcosbr.ejercicio.enums.NombreEntidad;
import com.marcosbr.ejercicio.excepcion.EntidadDuplicadaExcepcion;
import com.marcosbr.ejercicio.repositorio.UsuarioRepositorio;
import com.marcosbr.ejercicio.servicio.UsuarioServicio;
import com.marcosbr.ejercicio.util.Constantes;
import com.marcosbr.ejercicio.util.Criterio;
import com.marcosbr.ejercicio.util.RespuestaControlador;
import com.marcosbr.ejercicio.util.RespuestaControladorServicio;
import com.marcosbr.ejercicio.util.SistemaUtil;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marcos
 */
@Service
public class UsuarioServicioImpl extends BaseServicioImpl<Usuario, Long> implements UsuarioServicio {

    private final Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private RespuestaControladorServicio respuestaControladorServicio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio) {
        super(usuarioRepositorio);
    }

    @Override
    public RespuestaControlador crear(Usuario usuario) throws EntidadDuplicadaExcepcion {
        this.validarDuplicado(usuario);
        this.usuarioRepositorio.crear(usuario);
        return this.respuestaControladorServicio.obtenerRespuestaDeExitoCrearConData(NombreEntidad.USUARIO.getValor(), usuario.getId());
    }

    @Override
    public RespuestaControlador actualizar(Usuario usuario) throws EntidadDuplicadaExcepcion {
        this.validarDuplicado(usuario);
        this.usuarioRepositorio.actualizar(usuario);
        return respuestaControladorServicio.obtenerRespuestaDeExitoActualizar(NombreEntidad.USUARIO.getValor());
    }

    @Override
    public RespuestaControlador eliminar(Long usuarioId) {
        RespuestaControlador respuesta;
        Usuario usuario;
        Boolean puedeEliminar;

        puedeEliminar = true;

        if (puedeEliminar == null || !puedeEliminar) {
            respuesta = RespuestaControlador.obtenerRespuestaDeError("El " + NombreEntidad.USUARIO.getValor().toLowerCase() + " ha sido asignado a uno o varios usuarios y no se puede eliminar");
        } else {
            usuario = usuarioRepositorio.obtener(usuarioId);
            usuario.setEstado(Boolean.FALSE);
            usuarioRepositorio.actualizar(usuario);
            respuesta = respuestaControladorServicio.obtenerRespuestaDeExitoEliminar(NombreEntidad.USUARIO.getValor());
        }

        return respuesta;
    }

    public void validarDuplicado(Usuario usuario) throws EntidadDuplicadaExcepcion {
//        Criterio filtro = Criterio.forClass(Alumno.class);
//
//        filtro.add(Restrictions.eq("estado", Boolean.TRUE));
//        filtro.add(Restrictions.eq("persona.id", proveedor.getPersona().getId()));
//
//        // Si es una actualizacion
//        if (proveedor.getId() != null) {
//            filtro.add(Restrictions.ne("id", proveedor.getId()));
//        }
//        if (alumnoRepositorio.cantidadPorCriteri(filtro) > 0) {
//            throw new EntidadDuplicadaExcepcion("Ya existe otro " + NombreEntidad.ALUMNO.getValor() + " asignado a la misma persona.");
//        }
    }

    @Override
    public RespuestaControlador validarCredenciales(String login, String contrasena) {
        RespuestaControlador respuesta = RespuestaControlador.obtenerRespuestaDeError(Constantes.RESPUESTA_CONTROLADOR.MENSAJE_ERROR_AUTENTICACION);
        System.out.println("");
        System.out.println("TOKEN: " + this.generarJWToken(login));
        System.out.println("");
        
        Criterio filtro = Criterio.forClass(Usuario.class);
        filtro.createAlias("persona", "per", JoinType.LEFT_OUTER_JOIN);
        filtro.add(Restrictions.eq("login", login));
        filtro.add(Restrictions.eq("contrasena", contrasena));
        filtro.add(Restrictions.eq("estado", Boolean.TRUE));
        
        System.out.println("");
        System.out.println("Antes de crear usuario");
        System.out.println("");
        Usuario usuarioSession = usuarioRepositorio.obtenerPorCriterio(filtro);
        System.out.println("Usuario.ID = " + usuarioSession.getId());
        if (SistemaUtil.esNoNulo(usuarioSession)) {
            usuarioSession.setToken(this.generarJWToken(login));
            respuesta = RespuestaControlador.obtenerRespuestaExitoConData(usuarioSession);
        }
        return respuesta;
    }

    private String generarJWToken(String usuario) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts.builder().setId("softtekJWT").setSubject(usuario)
                .claim("authorities", grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

    @Override
    public List<Usuario> obtenerTodos() {
        Criterio filtro = Criterio.forClass(Usuario.class);
        filtro.createAlias("persona", "per", JoinType.LEFT_OUTER_JOIN);
        filtro.add(Restrictions.eq("estado", Boolean.TRUE));

        return this.usuarioRepositorio.buscarPorCriteria(filtro);
    }

    @Override
    public Usuario obtener(Long usuarioId) {
        Usuario usuario;
        Criterio filtro = Criterio.forClass(Usuario.class);
        filtro.createAlias("persona", "per", JoinType.LEFT_OUTER_JOIN);
        filtro.add(Restrictions.eq("id", usuarioId));
        filtro.add(Restrictions.eq("estado", Boolean.TRUE));
        usuario = usuarioRepositorio.obtenerPorCriterio(filtro);

        return usuario;
    }
}
