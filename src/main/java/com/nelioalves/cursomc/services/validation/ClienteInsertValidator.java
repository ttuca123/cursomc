package com.nelioalves.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.nelioalves.cursomc.dto.ClienteNewDTO;
import com.nelioalves.cursomc.resources.exceptions.FieldMessage;

public class ClienteInsertValidator implements ConstraintValidator<ClientInsert, ClienteNewDTO>{

	@Override
	public void initialize(ClientInsert arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(ClienteNewDTO arg0, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		for(FieldMessage e: list) {
			
			context.disableDefaultConstraintViolation();
			
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
			.addConstraintViolation();
		}
		
		
		return list.isEmpty();
	}
	
	
	

}
