package epiis.unamba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import epiis.unamba.model.Producto;
import epiis.unamba.repository.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	private ProductoRepository prodRepo;
	
	public List<Producto> listar(){
		return prodRepo.findAll();
	}
	
	public Producto guardar(Producto prod) {
		if(prod.getPrecio() != null && prod.getPrecio() < 0) {
			throw new 
			IllegalArgumentException("El precio no puede ser cero");
		}		
		
		return prodRepo.save(prod);
	}
	
	
	
}
