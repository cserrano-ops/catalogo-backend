package epiis.unamba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import epiis.unamba.model.Usuario;
import epiis.unamba.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private PasswordEncoder passEncoder;
	
	public List<Usuario> listar(){
		return repo.findAll();
	}
	
	public Usuario obtenerPorId(Long id) {
		return repo.findById(id)
				.orElseThrow( () -> new RuntimeException("Usuario no encontrado"));
	}
	
	public Usuario guardar(Usuario usuario) {
		String hash = passEncoder.encode(usuario.getPassword());
		usuario.setPassword(hash);
		return repo.save(usuario);
	}
	
	//TODO: Completar actualizar y eliminar
	
	
	
	
	
	
	
	
	
	
}
