package epiis.unamba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	private ProductoService productoService;

	@GetMapping
	public List<Producto> obtenerProductos() {
		return productoService.listar();
	}

	@PostMapping
	public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
		Producto nuevo = productoService.guardar(producto);
		return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
		Producto actualizado = productoService.actualizar(id, producto);
		return new ResponseEntity<>(actualizado, HttpStatus.OK);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Producto> actualizarProductoParcial(@PathVariable Long id, @RequestBody Producto producto) {
		Producto actualizado = productoService.actualizarParcial(id, producto);
		return new ResponseEntity<>(actualizado, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {
		productoService.eliminar(id);
		return new ResponseEntity<>("Producto eliminado exitosamente", HttpStatus.OK);
	}
}
