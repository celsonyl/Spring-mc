package com.celso.springmc.services;

import com.celso.springmc.SpringmcApplication;
import com.celso.springmc.domain.*;
import com.celso.springmc.domain.enums.EstadoPagamento;
import com.celso.springmc.domain.enums.Perfil;
import com.celso.springmc.domain.enums.TipoCliente;
import com.celso.springmc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {
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
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public void instantiateTestDB() throws ParseException {

        Categoria c1 = new Categoria(null, "Informática");
        Categoria c2 = new Categoria(null, "Escritório");
        Categoria c3 = new Categoria(null, "Cama mesa e Banho");
        Categoria c4 = new Categoria(null, "Eletrõnicos");
        Categoria c5 = new Categoria(null, "Jardinagem");
        Categoria c6 = new Categoria(null, "Decoração");
        Categoria c7 = new Categoria(null, "Perfumaria");

        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);
        Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
        Produto p5 = new Produto(null, "Toalha", 50.00);
        Produto p6 = new Produto(null, "Colcha", 200.00);
        Produto p7 = new Produto(null, "TV True Color", 1200.00);
        Produto p8 = new Produto(null, "Roçadeira", 800.00);
        Produto p9 = new Produto(null, "Abajour", 100.00);
        Produto p10 = new Produto(null, "Pendente", 180.00);
        Produto p11 = new Produto(null, "Shampoo", 90.00);
        Produto p12 = new Produto(null,"Produto 12",17.00);
        Produto p13 = new Produto(null,"Produto 13",21.00);
        Produto p14 = new Produto(null,"Produto 14",102.00);
        Produto p15 = new Produto(null,"Produto 15",18.00);
        Produto p16 = new Produto(null,"Produto 16",12.00);
        Produto p17 = new Produto(null,"Produto 17",12.00);
        Produto p18 = new Produto(null,"Produto 18",12.00);
        Produto p19 = new Produto(null,"Produto 19",12.00);
        Produto p20 = new Produto(null,"Produto 20",12.00);
        Produto p21 = new Produto(null,"Produto 21",12.00);
        Produto p22 = new Produto(null,"Produto 22",12.00);
        Produto p23 = new Produto(null,"Produto 23",12.00);
        Produto p24 = new Produto(null,"Produto 24",12.00);
        Produto p25 = new Produto(null,"Produto 25",12.00);
        Produto p26 = new Produto(null,"Produto 26",12.00);
        Produto p27 = new Produto(null,"Produto 27",12.00);
        Produto p28 = new Produto(null,"Produto 28",12.00);
        Produto p29 = new Produto(null,"Produto 29",12.00);
        Produto p30 = new Produto(null,"Produto 30",12.00);
        Produto p31 = new Produto(null,"Produto 31",12.00);
        Produto p32 = new Produto(null,"Produto 32",12.00);
        Produto p33 = new Produto(null,"Produto 33",12.00);
        Produto p34 = new Produto(null,"Produto 34",12.00);
        Produto p35 = new Produto(null,"Produto 35",12.00);
        Produto p36 = new Produto(null,"Produto 36",12.00);

        c1.getProdutos().addAll(Arrays.asList(p12,p13,p14,p15,p16,p17,p18,p19,p20,p21,p22,p23,p24,p25,p26,p27,p28,p29,p30,p31,p32,
                p33,p34,p35,p36));
        c2.getProdutos().addAll(Arrays.asList(p2, p4));
        c3.getProdutos().addAll(Arrays.asList(p5, p6));
        c4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
        c5.getProdutos().add(p8);
        c6.getProdutos().addAll(Arrays.asList(p9, p10));
        c7.getProdutos().add(p11);

        p1.getCategorias().addAll(Arrays.asList(c1, c4));
        p2.getCategorias().addAll(Arrays.asList(c1, c2, c4));
        p3.getCategorias().addAll(Arrays.asList(c1, c4));
        p4.getCategorias().add(c2);
        p5.getCategorias().add(c3);
        p6.getCategorias().add(c3);
        p7.getCategorias().add(c4);
        p8.getCategorias().add(c5);
        p9.getCategorias().add(c6);
        p10.getCategorias().add(c6);
        p11.getCategorias().add(c7);
        p12.getCategorias().add(c1);
        p13.getCategorias().add(c1);
        p14.getCategorias().add(c1);
        p15.getCategorias().add(c1);
        p16.getCategorias().add(c1);
        p17.getCategorias().add(c1);
        p18.getCategorias().add(c1);
        p19.getCategorias().add(c1);
        p20.getCategorias().add(c1);
        p21.getCategorias().add(c1);
        p22.getCategorias().add(c1);
        p23.getCategorias().add(c1);
        p24.getCategorias().add(c1);
        p25.getCategorias().add(c1);
        p26.getCategorias().add(c1);
        p27.getCategorias().add(c1);
        p28.getCategorias().add(c1);
        p29.getCategorias().add(c1);
        p30.getCategorias().add(c1);
        p31.getCategorias().add(c1);
        p32.getCategorias().add(c1);
        p33.getCategorias().add(c1);
        p34.getCategorias().add(c1);
        p35.getCategorias().add(c1);
        p36.getCategorias().add(c1);


        categoriaRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11,p12,p13,p14,p15,p16,p17,p18,p19,p20,p21,p22,p23,p24,p25,p26,p27,p28,p29,p30,p31,p32,p33,p34,p35,p36));

        Estado est1 = new Estado(null, "Minas Geraris");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade cid1 = new Cidade(null, "Uberlândia", est1);
        Cidade cid2 = new Cidade(null, "São Paulo", est2);
        Cidade cid3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().add(cid1);
        est2.getCidades().addAll(Arrays.asList(cid2, cid3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));

        Cliente cli1 = new Cliente(null, "Maria Silva", "celsonyl@hotmail.com", "36378912377", TipoCliente.PESSOAFISICA,bCryptPasswordEncoder.encode("rato"));
        Cliente cli2 = new Cliente(null, "Celso Antonio", "apenaslebot@hotmail.com", "901.117.050-45", TipoCliente.PESSOAFISICA,bCryptPasswordEncoder.encode("87"));
        cli2.addPerfil(Perfil.ADMIN);
        cli1.getTelefones().addAll(Arrays.asList("93838393", "27363323"));
        cli2.getTelefones().addAll(Arrays.asList("9123393", "431223"));

        Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, cid1);
        Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, cid2);
        Endereco e3 = new Endereco(null, "Avenida Mata Pombo", "200", null, "Costa Norte", "2357852", cli2, cid2);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
        cli2.getEnderecos().add(e3);

        clienteRepository.saveAll(Arrays.asList(cli1,cli2));
        enderecoRepository.saveAll(Arrays.asList(e1, e2));

        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null, date.parse("30/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null, date.parse("10/10/2017 19:35"), cli1, e2);

        Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pag1);

        Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, date.parse("20/10/2017 00:00"), null);
        ped2.setPagamento(pag2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));

        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().add(ip3);

        p1.getItens().add(ip1);
        p2.getItens().add(ip3);
        p3.getItens().add(ip2);

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
    }
}

