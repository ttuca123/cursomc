package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.dto.CategoriaDTO;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		
		
		Optional<Categoria> categoria = repo.findById(id);
		
		if(categoria == null) {
			
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		
		return categoria.get();
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
			
			repo.deleteById(id);
		}
		
		public List<Categoria> findAll() {		
			
			return repo.findAll();
		}
		
		public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
				PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
				
				return repo.findAll(pageRequest);
			
		}
		
		public Categoria fromDTO (CategoriaDTO objDTO) {
			
			return new Categoria(objDTO.getId(), objDTO.getNome());
		}
		
}
