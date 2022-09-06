package br.com.testes.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.testes.loja.dao.CategoriaDAO;
import br.com.testes.loja.dao.ClienteDAO;
import br.com.testes.loja.dao.PedidoDAO;
import br.com.testes.loja.dao.ProdutoDAO;
import br.com.testes.loja.util.JPAUtil;
import br.com.testes.loja.vo.Categoria;
import br.com.testes.loja.vo.Cliente;
import br.com.testes.loja.vo.ItemPedido;
import br.com.testes.loja.vo.Pedido;
import br.com.testes.loja.vo.Produto;
import br.com.testes.loja.vo.RelatorioDeVendasVo;

public class CadastroDePedido {

    public static void main(String[] args) {
	popularBancoDeDados();
	EntityManager em = JPAUtil.getEntityManager();
	ProdutoDAO produtoDAO = new ProdutoDAO(em);
	ClienteDAO clienteDAO = new ClienteDAO(em);

	Produto produto = produtoDAO.buscaPorId(1l);
	Produto produto2 = produtoDAO.buscaPorId(2l);
	Produto produto3 = produtoDAO.buscaPorId(3l);
	Cliente cliente = clienteDAO.buscaPorId(1l);

	em.getTransaction().begin();

	Pedido pedido = new Pedido(cliente);
	pedido.adicionarItem(new ItemPedido(10, pedido, produto));
	pedido.adicionarItem(new ItemPedido(40, pedido, produto2));

	Pedido pedido2 = new Pedido(cliente);
	pedido2.adicionarItem(new ItemPedido(2, pedido2, produto3));

	PedidoDAO pedidoDAO = new PedidoDAO(em);
	pedidoDAO.cadastrar(pedido);
	pedidoDAO.cadastrar(pedido2);

	em.getTransaction().commit();

	BigDecimal totalVendido = pedidoDAO.valorTotalVendido();
	System.out.println("Valor total: " + totalVendido);

	List<RelatorioDeVendasVo> relatorio = pedidoDAO.relatorioDeVendas();
	for (RelatorioDeVendasVo obj : relatorio) {
	    System.out.println(obj);
	}
    }

    private static void popularBancoDeDados() {
	Categoria celulares = new Categoria("CELULARES");
	Categoria videogames = new Categoria("VIDEOGAMES");
	Categoria informatica = new Categoria("INFORMÁTICA");

	Produto celular = new Produto("Xiaomi Redmi", "Topo de linha", new BigDecimal("800"), celulares);
	Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("2500"), videogames);
	Produto macbook = new Produto("Macbook", "Macbook pro M1", new BigDecimal("10000"), informatica);

	Cliente cliente = new Cliente("Augusto", "1234");

	EntityManager em = JPAUtil.getEntityManager();
	ProdutoDAO produtoDAO = new ProdutoDAO(em);
	CategoriaDAO categoriaDAO = new CategoriaDAO(em);
	ClienteDAO clienteDAO = new ClienteDAO(em);

	em.getTransaction().begin();

	categoriaDAO.cadastrar(celulares);
	categoriaDAO.cadastrar(videogames);
	categoriaDAO.cadastrar(informatica);

	produtoDAO.cadastrar(celular);
	produtoDAO.cadastrar(videogame);
	produtoDAO.cadastrar(macbook);

	clienteDAO.cadastrar(cliente);
	em.getTransaction().commit();
	em.close();
    }

}
