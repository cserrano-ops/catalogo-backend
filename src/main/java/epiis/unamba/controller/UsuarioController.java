package epiis.unamba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epiis.unamba.model.Usuario;
import epiis.unamba.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@GetMapping
	public List<Usuario> listar(){
		return service.listar();
	}
	
	@PostMapping
	public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario){
		return new ResponseEntity<>(service.guardar(usuario), HttpStatus.CREATED);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
