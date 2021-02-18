package com.celso.springmc;

import com.celso.springmc.domain.Categoria;
import com.celso.springmc.domain.Cidade;
import com.celso.springmc.domain.Estado;
import com.celso.springmc.domain.Produto;
import com.celso.springmc.repositories.CategoriaRepository;
import com.celso.springmc.repositories.CidadeRepository;
import com.celso.springmc.repositories.EstadoRepository;
import com.celso.springmc.repositories.ProdutoRepository;
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

	}
}
