package epiis.unamba.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import epiis.unamba.dto.AuthResponse;
import epiis.unamba.model.Usuario;
import epiis.unamba.repository.UsuarioRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	private static final String SECRET_KEY = "Universidad2026.CatalogoAPI.SecretKeySuperSegura!";
	private static final long EXPIRATION_TIME = 60000; //3600000; //1 hora
	
	@Autowired
	UsuarioRepository usuarioRepo;
	
	public Date generarFechaEmision() {
		return new Date(System.currentTimeMillis());
	}
	
	public Date generarFechaExpiracion() {
		return new Date(System.currentTimeMillis() + EXPIRATION_TIME);
	}
	
	private SecretKey getSigninKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}
	
	public String generarToken(Usuario usuario) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("rol", usuario.getRol());
		claims.put("idUsuario", usuario.getId());
		claims.put("nombreUsuario", usuario.getUsername());
		claims.put("entidad", "UNAMBA");
		
		return Jwts.builder()
			.claims(claims)
			.subject(usuario.getUsername())
			.issuedAt(new Date(System.currentTimeMillis()))
			.expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
			.signWith(getSigninKey())
			.compact();
		
	}
	
	public AuthResponse refrescarToken(String token) {
		String username = this.getUsername(token);
		Usuario usuario = usuarioRepo.findByUsername(username)
				.orElseThrow( () -> new RuntimeException("Usuario no encontrado"));
		return new AuthResponse(
				this.generarToken(usuario),
				usuario.getUsername(),
				usuario.getRol(),
				this.generarFechaEmision(),
				this.generarFechaExpiracion()
			);
	}

	public void isValidToken(String token) {
		Jwts.parser().verifyWith(getSigninKey()).build()
		.parseSignedClaims(token);
	}

	public String getUsername(String token) {
		try {
			return Jwts.parser()
				.verifyWith(getSigninKey())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
		}catch(ExpiredJwtException e) {
			return e.getClaims().getSubject();
		}
	}
	
	
	
	
	
	
	
	
}
	
