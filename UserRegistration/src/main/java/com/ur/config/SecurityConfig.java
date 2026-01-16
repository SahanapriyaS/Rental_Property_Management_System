package com.ur.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ur.security.JwtAuthenticationFilter;
import com.ur.security.JwtAuthorizationFilter;
import com.ur.security.JwtUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
private final JwtUtil jwtUtil;
	
	public SecurityConfig(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}
	
	@Bean
	public UserDetailsService userDetailsService(DataSource datasource) {
		JdbcDaoImpl jdbcDao=new JdbcDaoImpl();
		jdbcDao.setDataSource(datasource);
		jdbcDao.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username= ?");
		jdbcDao.setAuthoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username= ?");
 
		return jdbcDao;
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, 
			AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService
) throws Exception {
	 
		JwtAuthenticationFilter jwtAuthFilter = new JwtAuthenticationFilter(authenticationManager, jwtUtil);
		jwtAuthFilter.setFilterProcessesUrl("/login");
		
	    http
	    .csrf(csrf -> csrf.disable())
	    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	    .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/signup","/login").permitAll()
	            .requestMatchers("/admin/**").hasAuthority("ADMIN")
	            .requestMatchers("/owner/**").hasAnyAuthority("OWNER","ADMIN")
	            .requestMatchers("/tenant/**").hasAnyAuthority("TENANT","ADMIN")
	            .anyRequest().authenticated()
	        )
//	        .formLogin(login -> login.permitAll())
	        .addFilter(jwtAuthFilter)
	        .addFilterBefore(new JwtAuthorizationFilter(jwtUtil, userDetailsService), UsernamePasswordAuthenticationFilter.class);
//	        .logout(logout -> logout.permitAll());
	 
	    return http.build();
	}

	
	

}
