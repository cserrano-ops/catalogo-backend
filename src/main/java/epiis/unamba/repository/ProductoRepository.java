package epiis.unamba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import epiis.unamba.model.Producto;

public interface ProductoRepository 
	extends JpaRepository<Producto, Long>{
	
}
