package epiis.unamba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> 
		obtenerPorId(@PathVariable Long id){
		return new ResponseEntity<>(
			catService.obtenerPorId(id),
			HttpStatus.OK
		);
	}
	
	@PostMapping
	public ResponseEntity<Categoria>
		crear(@RequestBody Categoria categoria){
		
		return new ResponseEntity<>(
				catService.guardar(categoria),
				HttpStatus.CREATED
			);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria>
		actualizar(@PathVariable Long id,@RequestBody Categoria cat){
		return new ResponseEntity<>(
			catService.actualizar(id, cat),
			HttpStatus.OK
		);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Categoria>
		actualizarParcial(@PathVariable Long id, @RequestBody Categoria cat){
		return new ResponseEntity<>(
				catService.actualizarParcial(id, cat),
				HttpStatus.OK
			);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Long id){
		if(catService.eliminar(id))
			return new ResponseEntity<>(
					"Categoria eliminada",
					HttpStatus.OK
				);
		return new ResponseEntity<>(
				"No se pudo eliminar",
				HttpStatus.UNPROCESSABLE_CONTENT
			);
	}
		
}
