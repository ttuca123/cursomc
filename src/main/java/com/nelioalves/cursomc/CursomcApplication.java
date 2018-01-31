package com.nelioalves.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.repositories.EstadoRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Produto p1 = new Produto(null, "Computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 50.0);		
		
		
		Categoria c1 = new Categoria(null, "Informática");
		c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		
		Categoria c2 = new Categoria(null, "Escritório");
		c2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().add(c1);
		p2.getCategorias().add(c1);
		p2.getCategorias().add(c2);
		p3.getCategorias().add(c1);
		
		Estado est1 = new Estado(null, "Minas Gerais");
		
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade( "Uberlândia", est1);
		
		Cidade cid2 = new Cidade( "São Paulo", est2);
		
		Cidade cid3 = new Cidade("Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));
		
		Cliente cliente1 = new Cliente(null, "Artur Cavalcante", "artur.ti.solution@gmail.com",
				"981291464", TipoCliente.PESSOAFISICA);
		
		cliente1.getTelefones().addAll(Arrays.asList("9546546546", "65165465466"));
		
		Endereco endereco1 = new Endereco(null, "Rua Flores", "300", "apto 303", "Jardim", "3981296546", cliente1, cid2);
		
		Endereco endereco2 = new Endereco(null, "Av Uber ", "100", "", "Centro", "3981565", cliente1, cid1);
		
		cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));
		
		categoriaRepository.save(Arrays.asList(c1, c2));
		
		produtoRepository.save(Arrays.asList(p1, p2, p3));
		
		estadoRepository.save(Arrays.asList(est1, est2));
		
		cidadeRepository.save(Arrays.asList(cid1, cid2, cid3));
		
		clienteRepository.save(Arrays.asList(cliente1));
		
		enderecoRepository.save(Arrays.asList(endereco1, endereco2));
	}
}
