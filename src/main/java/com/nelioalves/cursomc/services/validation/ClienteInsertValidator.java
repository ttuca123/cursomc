package com.nelioalves.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.dto.ClienteNewDTO;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.resources.exceptions.FieldMessage;
import com.nelioalves.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClientInsert, ClienteNewDTO>{

	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClientInsert arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !BR.validaCPF(objDTO.getCpfOuCnpj())) {
			
			list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));
			
		}
		
		if(objDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !BR.validaCNPJ(objDTO.getCpfOuCnpj())) {
			
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
			
		}
		
		Cliente aux = repo.findByEmail(objDTO.getEmail());
		
		if(aux != null) {
			
			list.add(new FieldMessage("email", "Email já existe"));
		}
		
		for(FieldMessage e: list) {
			
			context.disableDefaultConstraintViolation();
			
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
			.addConstraintViolation();
		}
		
		
		return list.isEmpty();
	}
	
	
	

}
