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

import epiis.unamba.model.Categoria;
import epiis.unamba.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService catService;
	
	@GetMapping
	public List<Categoria> listar(){
		return catService.listar();
	}
	
	@PostMapping
	public ResponseEntity<Categoria>
		crear(@RequestBody Categoria categoria){
		
		return new ResponseEntity<>(
				catService.guardar(categoria),
				HttpStatus.CREATED
			);
	}
}
