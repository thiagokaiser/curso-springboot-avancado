package com.kaiser.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kaiser.cursomc.domain.Categoria;
import com.kaiser.cursomc.domain.Cidade;
import com.kaiser.cursomc.domain.Cliente;
import com.kaiser.cursomc.domain.Endereco;
import com.kaiser.cursomc.domain.Estado;
import com.kaiser.cursomc.domain.ItemPedido;
import com.kaiser.cursomc.domain.Pagamento;
import com.kaiser.cursomc.domain.PagamentoComBoleto;
import com.kaiser.cursomc.domain.PagamentoComCartao;
import com.kaiser.cursomc.domain.Pedido;
import com.kaiser.cursomc.domain.Produto;
import com.kaiser.cursomc.domain.enums.EstadoPagamento;
import com.kaiser.cursomc.domain.enums.TipoCliente;
import com.kaiser.cursomc.repositories.CategoriaRepository;
import com.kaiser.cursomc.repositories.CidadeRepository;
import com.kaiser.cursomc.repositories.ClienteRepository;
import com.kaiser.cursomc.repositories.EnderecoRepository;
import com.kaiser.cursomc.repositories.EstadoRepository;
import com.kaiser.cursomc.repositories.ItemPedidoRepository;
import com.kaiser.cursomc.repositories.PagamentoRepository;
import com.kaiser.cursomc.repositories.PedidoRepository;
import com.kaiser.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepo;	
	@Autowired
	private ProdutoRepository produtoRepo;	
	@Autowired
	private CidadeRepository cidadeRepo;	
	@Autowired
	private EstadoRepository estadoRepo;	
	@Autowired
	private ClienteRepository clienteRepo;	
	@Autowired
	private EnderecoRepository enderecoRepo;
	@Autowired
	private PedidoRepository pedidoRepo;
	@Autowired
	private PagamentoRepository pagamentoRepo;
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Categ 03");
		Categoria cat4 = new Categoria(null, "Categ 04");
		Categoria cat5 = new Categoria(null, "Categ 05");
		Categoria cat6 = new Categoria(null, "Categ 06");
		Categoria cat7 = new Categoria(null, "Categ 07");
		Categoria cat8 = new Categoria(null, "Categ 08");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));		
		
		categoriaRepo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8));
		produtoRepo.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Santa Catarina");
		Estado est2 = new Estado(null, "Parana");
		
		Cidade cid1 = new Cidade(null, "Joinville", est1);
		Cidade cid2 = new Cidade(null, "Curitiba", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est1.getCidades().addAll(Arrays.asList(cid2));
		
		estadoRepo.saveAll(Arrays.asList(est1,est2));
		cidadeRepo.saveAll(Arrays.asList(cid1,cid2));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("123123123","321321321"));
		
		Endereco e1 = new Endereco(null, "rua Flores", "300", "apto 201", "Jardim", "38220834", cli1, cid1);
		Endereco e2 = new Endereco(null, "rua Matos", "105", "sala 800", "Centro", "38777012", cli1, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepo.saveAll(Arrays.asList(cli1));
		enderecoRepo.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
					
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepo.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepo.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepo.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}

}
