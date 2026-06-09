package epiis.unamba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import epiis.unamba.model.Categoria;
import epiis.unamba.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository catRepo;
	
	public List<Categoria> listar(){
		return catRepo.findAll();
	}
	
	public Categoria guardar(Categoria cat) {
		return catRepo.save(cat);
	}
}
