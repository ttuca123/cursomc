package com.nelioalves.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		
		
		Cliente cliente = repo.findOne(id);
		
		if(cliente == null) {
			
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		
		return cliente;
	}
	
	public Cliente insert(Cliente cat) {
		
		cat.setId(null);
		
		return repo.save(cat);
		
	}
	
	public Cliente update(Cliente cat) {
		
		find(cat.getId());
		
		return repo.save(cat);
	}
	
	public void delete (Integer id) {
		
		try {
			find(id);
			
			repo.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Cliente> findAll() {		
		
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
			PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
			
			return repo.findAll(pageRequest);
		
	}
	
	public Cliente fromDTO (ClienteDTO objDTO) {
		
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
}
