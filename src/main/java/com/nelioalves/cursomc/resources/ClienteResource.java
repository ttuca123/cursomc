package com.nelioalves.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.dto.ClienteNewDTO;
import com.nelioalves.cursomc.services.ClienteService;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService; 
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {

		
		Cliente cliente = clienteService.find(id);
		
		return ResponseEntity.ok(cliente);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO clienteNewDTO) {

		Cliente obj = clienteService.fromDTO(clienteNewDTO);
		
		obj = clienteService.insert(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteNewDTO).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody ClienteDTO catDTO, @PathVariable Integer id) {

		Cliente obj = clienteService.fromDTO(catDTO);
		obj.setId(id);		
		
		clienteService.update(obj);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Cliente> delete(@PathVariable Integer id) {

		find(id);

		try {
			clienteService.delete(id);

		} catch (DataIntegrityViolationException e) {

			throw new DataIntegrityException("Não é possível excluir uma Cliente que possui produtos");
		}

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {

		List<Cliente> Clientes = clienteService.findAll();

		List<ClienteDTO> list = Clientes.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(list);

	}
	
	
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
				@RequestParam(value="page", defaultValue="0") Integer page,
				@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
				@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
				@RequestParam(value="direction", defaultValue="ASC") String direction
			) {
		
		Page<Cliente> list = clienteService.findPage(page, linesPerPage, orderBy, direction);
		
		Page<ClienteDTO> listDTO = list.map( obj -> new ClienteDTO(obj));
		

		return ResponseEntity.ok().body(listDTO);

	}

}
