package com.nelioalves.cursomc.dto;

import java.io.Serializable;

import com.nelioalves.cursomc.domain.Categoria;

public class CategoriaDTO implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String nome;
	
	
	
	public CategoriaDTO() {
		
		
	}
	
	public CategoriaDTO(Categoria categoria) {
		
		id = categoria.getId();
		nome = categoria.getNome();
		
	}
}
