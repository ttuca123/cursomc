package com.nelioalves.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		
		
		Categoria categoria = repo.findOne(id);
		
		if(categoria == null) {
			
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		
		return categoria;
	}
	
	
		public Categoria insert(Categoria cat) {
			
			cat.setId(null);
			
			return repo.save(cat);
			
		}
		
		public Categoria update(Categoria cat) {
			
			find(cat.getId());
			
			return repo.save(cat);
		}
		
		public void delete (Integer id) {
			
			find(id);
			
			repo.delete(id);
		}
}
