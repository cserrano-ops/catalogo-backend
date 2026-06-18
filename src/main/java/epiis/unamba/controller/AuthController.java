package epiis.unamba.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epiis.unamba.model.Usuario;
import epiis.unamba.repository.UsuarioRepository;
import epiis.unamba.service.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private JwtService jwtService;
	
	public static class LoginRequest {
		public String username;
		public String password; 
	}
	
	@PostMapping
	public ResponseEntity<?> iniciarSesion(@RequestBody LoginRequest request){ 
		Optional<Usuario> usuario = usuarioRepo.findByUsername(request.username);
		
		if(usuario.isPresent() &&
				usuario.get().getPassword().equals(request.password)) {
			String token = jwtService.generarToken(usuario.get());
			return ResponseEntity.ok("Bearer " + token);
		}else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
		}
	}
	
	
	
}
