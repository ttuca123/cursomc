package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.dto.ClienteNewDTO;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(Integer id) {
		
		
		Optional<Cliente> cliente = repo.findById(id);
		
		if(cliente == null) {
			
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		
		return cliente.get();
	}
	
	public Cliente insert(Cliente obj) {
		
		obj.setId(null);
		obj = repo.save(obj);
		
		enderecoRepository.saveAll(obj.getEnderecos());	
				
		return obj;
		
	}
	
	public Cliente update(Cliente obj) {
		
		Cliente cliente = find(obj.getId());
					
		repo.save(cliente);			
		
		return repo.save(obj);
	}
	
	public void delete (Integer id) {
		
		try {
			
			find(id);
			
			repo.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados"); 
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
		
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), 
				objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()), objDto.getSenha());
		
		Optional<Cidade>  cid = cidadeRepository.findById(objDto.getCidadeId());
		
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(),
				objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid.get());
		
		cli.getEnderecos().add(end);
		
		cli.getTelefones().add(objDto.getTelefone1());
		
		if(objDto.getTelefone2() !=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		
		if(objDto.getTelefone3() !=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		
		return cli;
	}
}
