package com.celso.springmc;

import com.celso.springmc.domain.*;
import com.celso.springmc.domain.enums.TipoCliente;
import com.celso.springmc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringmcApplication implements CommandLineRunner {

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
		SpringApplication.run(SpringmcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria c1 = new Categoria(0,"Informática");
		Categoria c2 = new Categoria(0,"Escritório");

		Produto p1 = new Produto(0,"Computador",2000.00);
		Produto p2 = new Produto(0,"Impressora",800.00);
		Produto p3 = new Produto(0,"Mouse",80.00);

		c1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		c2.getProdutos().add(p3);

		p1.getCategorias().add(c1);
		p2.getCategorias().addAll(Arrays.asList(c1,c2));
		p3.getCategorias().add(c1);

		categoriaRepository.saveAll(Arrays.asList(c1,c2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));

		Estado est1 = new Estado(0,"Minas Geraris");
		Estado est2 = new Estado(0,"São Paulo");

		Cidade cid1 = new Cidade(0,"Uberlândia",est1);
		Cidade cid2 = new Cidade(0,"São Paulo",est2);
		Cidade cid3 = new Cidade(0,"Campinas",est2);

		est1.getCidades().add(cid1);
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));

		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));

		Cliente cli1 = new Cliente(0,"Maria Silva","maria@gmail.com","36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("93838393","27363323"));

		Endereco e1 = new Endereco(0,"Rua Flores","300","Apto 203","Jardim","38220834",cli1,cid1);
		Endereco e2 = new Endereco(0,"Avenida Matos","105","Sala 800","Centro","38777012",cli1,cid2);

		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));







	}
}
