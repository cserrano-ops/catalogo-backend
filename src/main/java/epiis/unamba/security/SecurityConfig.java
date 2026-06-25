package epiis.unamba.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		
		http
		.csrf(csrf -> csrf.disable())
		.sessionManagement(
			session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		)
		.authorizeHttpRequests(
			auth -> auth
			.requestMatchers("/api/auth/**").permitAll()
			//.requestMatchers("/api/categorias/**").authenticated()
			.anyRequest().authenticated()
		)
		.exceptionHandling( 
			ex -> ex.authenticationEntryPoint(
					(request, response, authException) -> {
						response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //401
						response.setContentType("application/json");
						
						String errorJwt = (String) request.getAttribute("error_jwt");
						String mensajeError;						
						if("TOKEN_EXPIRED".equals(errorJwt)) {
							mensajeError = "TOKEN_EXPIRED";
						}else if ("TOKEN_INVALID".equals(errorJwt)) {
							mensajeError = "TOKEN_INVALID";
						}else {
							mensajeError = "UNAUTHORIZED";
						}
						
						response.getWriter().write("{\"error\" : \"" + mensajeError + "\"}");
					}
				)
			)
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	
	
	
	
	
	
	
	
	
}
