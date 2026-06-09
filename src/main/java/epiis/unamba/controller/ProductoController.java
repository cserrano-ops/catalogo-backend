package epiis.unamba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import epiis.unamba.model.Producto;
import epiis.unamba.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	@Autowired
	private ProductoService prodService;
	
	@GetMapping
	public List<Producto> listar(){
		return prodService.listar();
	}
	
	@PostMapping
	public ResponseEntity<Producto> 
		crear(@RequestBody Producto producto){
		
		return new ResponseEntity<>(
				prodService.guardar(producto),
				HttpStatus.CREATED
			);
			
	}
}
