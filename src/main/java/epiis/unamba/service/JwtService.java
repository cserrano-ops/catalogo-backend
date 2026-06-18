package epiis.unamba.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import epiis.unamba.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	private static final String SECRET_KEY = "Universidad2026.CatalogoAPI.SecretKeySuperSegura!";
	private static final long EXPIRATION_TIME = 3600000;
	
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
	
	
	
	
	
	
	
	
}
