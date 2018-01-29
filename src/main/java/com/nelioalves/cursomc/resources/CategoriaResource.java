package com.nelioalves.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService; 
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
//		Categoria categoria1 = new Categoria(1, "Informática");
//		
//		Categoria categoria2 = new Categoria(2, "Escritório");
//		
//		List<Categoria> categorias = new ArrayList<Categoria>();
//		categorias.add(categoria1);
//		categorias.add(categoria2);
		
		Categoria categoria = categoriaService.buscar(id);
		
		return ResponseEntity.ok(categoria);
	}

}
