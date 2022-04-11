package com.marcosbr.ejercicio.util;

import java.util.Date;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Marcos
 */
public class TablasH2 {

    public static void crearTablaPersona(JdbcTemplate template) {
        String sqlTablaMoneda;

        sqlTablaMoneda = "DROP TABLE persona IF EXISTS;";
        sqlTablaMoneda = "CREATE TABLE persona(";
        sqlTablaMoneda = sqlTablaMoneda + "id INTEGER(11) PRIMARY KEY auto_increment, ";
        sqlTablaMoneda = sqlTablaMoneda + "nombres VARCHAR(50), ";
        sqlTablaMoneda = sqlTablaMoneda + "apellidos VARCHAR(50), ";
        sqlTablaMoneda = sqlTablaMoneda + "num_documento CHAR(8), ";
        sqlTablaMoneda = sqlTablaMoneda + "direccion VARCHAR(100), ";
        sqlTablaMoneda = sqlTablaMoneda + "telefono VARCHAR(9), ";
        sqlTablaMoneda = sqlTablaMoneda + "email VARCHAR(100), ";
        // Creamos los campos de auditoría
        sqlTablaMoneda = sqlTablaMoneda + "estado boolean DEFAULT true, ";
        sqlTablaMoneda = sqlTablaMoneda + "usuario_creacion VARCHAR(100), ";
        sqlTablaMoneda = sqlTablaMoneda + "ip_creacion VARCHAR(100), ";
        sqlTablaMoneda = sqlTablaMoneda + "pc_creacion VARCHAR(100), ";
        sqlTablaMoneda = sqlTablaMoneda + "fecha_creacion TIMESTAMP DEFAULT NOW(), ";
        sqlTablaMoneda = sqlTablaMoneda + "usuario_modificacion VARCHAR(100), ";
        sqlTablaMoneda = sqlTablaMoneda + "ip_modificacion VARCHAR(100), ";
        sqlTablaMoneda = sqlTablaMoneda + "pc_modificacion VARCHAR(100), ";
        sqlTablaMoneda = sqlTablaMoneda + "fecha_modificacion TIMESTAMP DEFAULT NOW()";
        sqlTablaMoneda = sqlTablaMoneda + ");";
        template.execute(sqlTablaMoneda);
    }

    public static void insertarRegistrosTablaPersona(JdbcTemplate template) {
        String sqlInsertarMoneda;
        String[] nombres = {"MARCOS MARIANO", "JEREMY ROLANDO", "FRANZ JUNIOR"};
        String[] apellidos = {"BAYONA RIJALBA", "AGURTO GUZMÁN", "MORENO CRUZ"};
        String[] num_documentos = {"76424069", "74125896", "78965412"};
        String[] direcciones = {"DIRECCIÓN ABC", "DIRECCIÓN 123", "DIRECCIÓN XYZ"};
        String[] telefonos = {"947181319", "987456132", "965131651"};
        String[] emails = {"marcos@gmail.com", "jeremy@gmail.com", "franz@gmail.com"};

        for (int i = 0; i < nombres.length; i++) {
            sqlInsertarMoneda = "";
            sqlInsertarMoneda = sqlInsertarMoneda + "INSERT INTO persona(nombres, apellidos, num_documento, direccion, telefono, email) VALUES (";
            sqlInsertarMoneda = sqlInsertarMoneda + "'" + nombres[i] + "', '" + apellidos[i] + "', '" + num_documentos[i] + "', '" + direcciones[i] + "', '" + telefonos[i] + "', '" + emails[i] + "');";
            template.update(sqlInsertarMoneda);
        }
    }

    public static void crearTablaUsuario(JdbcTemplate template) {
        String sqlTablaTipoCambio;

        sqlTablaTipoCambio = "DROP TABLE usuario IF EXISTS;";
        sqlTablaTipoCambio = "CREATE TABLE usuario(";
        sqlTablaTipoCambio = sqlTablaTipoCambio + "id INTEGER(11) PRIMARY KEY auto_increment, ";
        sqlTablaTipoCambio = sqlTablaTipoCambio + "login VARCHAR(50), ";
        sqlTablaTipoCambio = sqlTablaTipoCambio + "contrasena VARCHAR(50), ";
        sqlTablaTipoCambio = sqlTablaTipoCambio + "id_persona INTEGER(11), ";
        // Creamos los campos de auditoría
        sqlTablaTipoCambio = sqlTablaTipoCambio + "estado boolean DEFAULT true, ";
        sqlTablaTipoCambio = sqlTablaTipoCambio + "usuario_creacion VARCHAR(100), ";
        sqlTablaTipoCambio = sqlTablaTipoCambio + "ip_creacion VARCHAR(100), ";
        sqlTablaTipoCambio = sqlTablaTipoCambio + "pc_creacion VARCHAR(100), ";
        sqlTablaTipoCambio = sqlTablaTipoCambio + "fecha_creacion TIMESTAMP DEFAULT NOW(), ";
        sqlTablaTipoCambio = sqlTablaTipoCambio + "usuario_modificacion VARCHAR(100), ";
        sqlTablaTipoCambio = sqlTablaTipoCambio + "ip_modificacion VARCHAR(100), ";
        sqlTablaTipoCambio = sqlTablaTipoCambio + "pc_modificacion VARCHAR(100), ";
        sqlTablaTipoCambio = sqlTablaTipoCambio + "fecha_modificacion TIMESTAMP DEFAULT NOW(), ";
        sqlTablaTipoCambio = sqlTablaTipoCambio + "FOREIGN KEY (id_persona) references persona(id)";
        sqlTablaTipoCambio = sqlTablaTipoCambio + ");";
        template.execute(sqlTablaTipoCambio);
    }

    public static void insertarRegistrosTablaUsuario(JdbcTemplate template) {
        String sqlInsertarTipoCambio;
        
        String[] logins = {"marcosbr", "admin"};
        String[] contrasenas = {"123456", "admin123"};
        int[] idPersonas = {1, 2};

        for (int i = 0; i < logins.length; i++) {
            sqlInsertarTipoCambio = "";
            sqlInsertarTipoCambio = sqlInsertarTipoCambio + "INSERT INTO usuario(login, contrasena, id_persona) VALUES (";
            sqlInsertarTipoCambio = sqlInsertarTipoCambio + "'" + logins[i] + "', '" + contrasenas[i] + "', " + idPersonas[i] + ");";
            template.update(sqlInsertarTipoCambio);
        }
    }
}
