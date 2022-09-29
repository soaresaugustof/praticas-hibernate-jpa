package br.com.testes.loja.testes;

import java.math.BigDecimal;

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

public class PerformanceConsultas {

    public static void main(String[] args) {
	popularBancoDeDados();
	EntityManager em = JPAUtil.getEntityManager();
	PedidoDAO pedidoDAO = new PedidoDAO(em);
	Pedido pedido = pedidoDAO.buscarPedidoComCliente(1l);

	em.close();
	System.out.println(pedido.getCliente().getNome());
    }

    private static void popularBancoDeDados() {
	Categoria celulares = new Categoria("CELULARES");
	Categoria videogames = new Categoria("VIDEOGAMES");
	Categoria informatica = new Categoria("INFORMÁTICA");

	Produto celular = new Produto("Xiaomi Redmi", "Topo de linha", new BigDecimal("800"), celulares);
	Produto videogame = new Produto("PS5", "Playstation", new BigDecimal("1500"), videogames);
	Produto macbook = new Produto("Mac", "Macbook", new BigDecimal("4300"), informatica);

	Cliente cliente = new Cliente("Augusto", "1234");

	Pedido pedido = new Pedido(cliente);
	pedido.adicionarItem(new ItemPedido(2, pedido, celular));
	pedido.adicionarItem(new ItemPedido(5, pedido, videogame));

	Pedido pedido2 = new Pedido(cliente);
	pedido2.adicionarItem(new ItemPedido(2, pedido2, macbook));

	EntityManager em = JPAUtil.getEntityManager();
	ProdutoDAO produtoDAO = new ProdutoDAO(em);
	ClienteDAO clienteDAO = new ClienteDAO(em);
	CategoriaDAO categoriaDAO = new CategoriaDAO(em);
	PedidoDAO pedidoDAO = new PedidoDAO(em);

	em.getTransaction().begin();

	categoriaDAO.cadastrar(celulares);
	categoriaDAO.cadastrar(videogames);
	categoriaDAO.cadastrar(informatica);

	produtoDAO.cadastrar(celular);
	produtoDAO.cadastrar(videogame);
	produtoDAO.cadastrar(macbook);

	clienteDAO.cadastrar(cliente);

	pedidoDAO.cadastrar(pedido);
	pedidoDAO.cadastrar(pedido2);

	em.getTransaction().commit();
    }
}
