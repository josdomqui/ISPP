package org.springframework.samples.commandfast.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author japarejo
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private static final String RESTAURANTE_URL = "/restaurante/**";
	private static final String STRING_ADMIN = "admin";
	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/resources/**", "/webjars/**", "/h2-console/**").permitAll()
				.antMatchers("/payment/subscription").permitAll()
				.antMatchers(HttpMethod.GET, "/", "/oups").permitAll()
				.antMatchers("/users/new").permitAll()
				.antMatchers("/charge").permitAll()
				.antMatchers("/create-charge").permitAll()
				.antMatchers("/restaurant/paymentPanel").hasAnyAuthority("restaurant")
				.antMatchers("/restaurante/signup").permitAll()
				.antMatchers("/restaurante/notify/**").permitAll()
				.antMatchers("/restaurante/list/**").permitAll()
				.antMatchers(RESTAURANTE_URL).permitAll()
				.antMatchers(HttpMethod.GET, "/restaurante/list/**").permitAll()
				.antMatchers(HttpMethod.GET, RESTAURANTE_URL).permitAll()
				.antMatchers(HttpMethod.POST, RESTAURANTE_URL).permitAll()
				.antMatchers(HttpMethod.POST, "/command/new/**/**").permitAll()
				.antMatchers("/command/new").permitAll()
				.antMatchers("/command/redirect/qr").permitAll()
				.antMatchers("/command/new/**/**").permitAll()
				.antMatchers("/command/all").hasAnyAuthority("restaurant")
				.antMatchers("/carta/**").permitAll()
				.antMatchers("/admin/**").hasAnyAuthority(STRING_ADMIN)
				.antMatchers("/owners/**").hasAnyAuthority("owner", STRING_ADMIN)
				.antMatchers("/command/**", "/restaurantes/**").authenticated().and()
				.formLogin()
				/* .loginPage("/login") */
				.failureUrl("/login-error").and().logout().logoutSuccessUrl("/");
		// Configuración para que funcione la consola de administración
		// de la BD H2 (deshabilitar las cabeceras de protección contra
		// ataques de tipo csrf y habilitar los framesets si su contenido
		// se sirve desde esta misma página.
		http.csrf().ignoringAntMatchers("/h2-console/**", "/charge", "command/new", "command/new/**/**",
				"/payment/subscription");
		http.headers().frameOptions().sameOrigin();

	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery(
						"select username,password,enabled "
								+ "from users "
								+ "where username = ?")
				.authoritiesByUsernameQuery(
						"select username, authority "
								+ "from authorities "
								+ "where username = ?")
				.passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
