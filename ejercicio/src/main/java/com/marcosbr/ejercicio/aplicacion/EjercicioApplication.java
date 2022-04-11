package com.marcosbr.ejercicio.aplicacion;

import com.marcosbr.ejercicio.security.JWTAuthorizationFilter;
import com.marcosbr.ejercicio.util.TablasH2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan(basePackages = {"com.marcosbr.ejercicio"})
public class EjercicioApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    private JdbcTemplate template;

    public static void main(String[] args) {
        SpringApplication.run(EjercicioApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EjercicioApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        // Para 'moneda'
        TablasH2.crearTablaPersona(template);
        TablasH2.insertarRegistrosTablaPersona(template);
        // Para 'tipocambio'
        TablasH2.crearTablaUsuario(template);
        TablasH2.insertarRegistrosTablaUsuario(template);
    }

    @EnableWebSecurity
    @Configuration
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/usuario/logeo").permitAll()
                    .anyRequest().authenticated();
//            http
//                    .csrf()
//                    .disable()
//                    .authorizeRequests()
//                    .antMatchers("/**").permitAll()
//                    .anyRequest().authenticated();
        }
    }

}
