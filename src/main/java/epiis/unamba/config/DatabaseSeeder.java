package epiis.unamba.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import epiis.unamba.model.Usuario;
import epiis.unamba.repository.UsuarioRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner{

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private PasswordEncoder passEnconder;
	
	@Override
	public void run(String... args) throws Exception {
		if(usuarioRepo.count() == 0) {
			System.out.println("No hay usuarios. Creando admin ...");
			Usuario admin = new Usuario();
			admin.setUsername("admin");
			admin.setPassword(passEnconder.encode("admin"));
			admin.setRol("ADMIN");
			
			usuarioRepo.save(admin);
			System.out.println("Usuario admin creado");
		}
		
	}

}
