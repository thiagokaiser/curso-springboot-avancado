package com.kaiser.cursomc;

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
import com.kaiser.cursomc.domain.Produto;
import com.kaiser.cursomc.domain.enums.TipoCliente;
import com.kaiser.cursomc.repositories.CategoriaRepository;
import com.kaiser.cursomc.repositories.CidadeRepository;
import com.kaiser.cursomc.repositories.ClienteRepository;
import com.kaiser.cursomc.repositories.EnderecoRepository;
import com.kaiser.cursomc.repositories.EstadoRepository;
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
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));		
		
		categoriaRepo.saveAll(Arrays.asList(cat1, cat2));
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
		
	}

}
