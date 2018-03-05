package com.nelioalves.cursomc;

import java.text.SimpleDateFormat;
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
import com.nelioalves.cursomc.domain.ItemPedido;
import com.nelioalves.cursomc.domain.Pagamento;
import com.nelioalves.cursomc.domain.PagamentoComBoleto;
import com.nelioalves.cursomc.domain.PagamentoComCartao;
import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.domain.enums.EstadoPagamento;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.repositories.EstadoRepository;
import com.nelioalves.cursomc.repositories.ItemPedidoRepository;
import com.nelioalves.cursomc.repositories.PagamentoRepository;
import com.nelioalves.cursomc.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		Produto p1 = new Produto(null, "Computador", 2000.0);
		
		Produto p2 = new Produto(null, "Impressora", 800.0);
		
		Produto p3 = new Produto(null, "Mouse", 50.0);		
		
		Produto p4 = new Produto(null, "Mesa de Escritório", 300.00);
		
		Produto p5 = new Produto(null, "Toalha", 50.00);
		
		Produto p6 = new Produto(null, "Colcha", 200.00);
		
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		
		Produto p9 = new Produto(null, "Abajur", 100.00);
		
		Produto p10 = new Produto(null, "Pendente", 100.00);
		
		Produto p11 = new Produto(null, "Shampoo", 10.00);		
		
		
		Categoria c1 = new Categoria(null, "Informática");
		c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		
		Categoria c2 = new Categoria(null, "Escritório");
		c2.getProdutos().addAll(Arrays.asList(p2,p4));
		
		Categoria c3 = new Categoria(null, "Cama Mesa e Banho");
		
		c3.getProdutos().addAll(Arrays.asList(p5,p6));
		
		Categoria c4 = new Categoria(null, "Eletrônicos");
		
		c4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		
		Categoria c5 = new Categoria(null, "Jardinagem");
		
		c5.getProdutos().addAll(Arrays.asList(p8));
		
		Categoria c6 = new Categoria(null, "Decoração");
		
		c6.getProdutos().addAll(Arrays.asList(p9, p10 ));
		
		Categoria c7 = new Categoria(null, "Perfumaria");
		
		c7.getProdutos().addAll(Arrays.asList(p11 ));
		
		p1.getCategorias().addAll(Arrays.asList(c1, c4));
		p2.getCategorias().addAll(Arrays.asList(c1, c2, c4));
		p3.getCategorias().addAll(Arrays.asList(c1, c4));
		p4.getCategorias().addAll(Arrays.asList(c2));
		p5.getCategorias().addAll(Arrays.asList(c3));
		p6.getCategorias().addAll(Arrays.asList(c3));
		p7.getCategorias().addAll(Arrays.asList(c4));
		p8.getCategorias().addAll(Arrays.asList(c5));
		p9.getCategorias().addAll(Arrays.asList(c6));
		p10.getCategorias().addAll(Arrays.asList(c6));
		
		p11.getCategorias().addAll(Arrays.asList(c7));
		
		
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("31/01/2018 11:35"), cliente1, endereco1);
		
		Pedido ped2 = new Pedido(null, sdf.parse("31/01/2018 11:35"), cliente1, endereco1);
		
		Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagamento1);
		
		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.QUITADO, ped2, sdf.parse("31/01/2018 11:35"),
				sdf.parse("28/02/2018 11:35"));
		ped2.setPagamento(pagamento2);
		
		
		
		ItemPedido itemPedido1 = new ItemPedido(ped1, p1, 0.0, 1, 2000.0);
		
		ItemPedido itemPedido2 = new ItemPedido(ped1, p3, 0.0, 1, 800.0);
		
		ItemPedido itemPedido3 = new ItemPedido(ped2, p2, 0.0, 1, 100.0);
		
		ped1.getItens().addAll(Arrays.asList(itemPedido1, itemPedido2));
		
		ped2.getItens().addAll(Arrays.asList(itemPedido3));		
		
		cliente1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		categoriaRepository.save(Arrays.asList(c1, c2, c3, c4, c5, c6, c7));
		
		produtoRepository.save(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		estadoRepository.save(Arrays.asList(est1, est2));
		
		cidadeRepository.save(Arrays.asList(cid1, cid2, cid3));
		
		clienteRepository.save(Arrays.asList(cliente1));
		
		enderecoRepository.save(Arrays.asList(endereco1, endereco2));
		
		pedidoRepository.save(Arrays.asList(ped1, ped2));
		
		pagamentoRepository.save(Arrays.asList(pagamento1, pagamento2));
		
		itemPedidoRepository.save(Arrays.asList(itemPedido1, itemPedido2));
	}
}
