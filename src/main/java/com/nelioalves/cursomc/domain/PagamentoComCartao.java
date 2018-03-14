package com.nelioalves.cursomc.domain;

import javax.persistence.Entity;

import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.annotation.JsonTypeName;

import com.nelioalves.cursomc.domain.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {

	
	private Integer numeroParcelas;

	public PagamentoComCartao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroParcelas) {
		super(id, estado, pedido);
		this.numeroParcelas = numeroParcelas;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
	
	
}
