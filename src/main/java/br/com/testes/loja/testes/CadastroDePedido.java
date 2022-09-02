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

public class CadastroDePedido {

    public static void main(String[] args) {
	popularBancoDeDados();
	EntityManager em = JPAUtil.getEntityManager();
	ProdutoDAO produtoDAO = new ProdutoDAO(em);
	ClienteDAO clienteDAO = new ClienteDAO(em);

	Produto produto = produtoDAO.buscaPorId(1l);
	Cliente cliente = clienteDAO.buscaPorId(1l);

	em.getTransaction().begin();

	Pedido pedido = new Pedido(cliente);
	pedido.adicionarItem(new ItemPedido(10, pedido, produto));

	PedidoDAO pedidoDAO = new PedidoDAO(em);
	pedidoDAO.cadastrar(pedido);
    }

    private static void popularBancoDeDados() {
	Categoria celulares = new Categoria("CELULARES");
	Produto celular = new Produto("Xiaomi Redmi", "Topo de linha", new BigDecimal("800"), celulares);
	Cliente cliente = new Cliente("Augusto", "1234");

	EntityManager em = JPAUtil.getEntityManager();
	ProdutoDAO produtoDAO = new ProdutoDAO(em);
	CategoriaDAO categoriaDAO = new CategoriaDAO(em);
	ClienteDAO clienteDAO = new ClienteDAO(em);

	em.getTransaction().begin();
	categoriaDAO.cadastrar(celulares);
	produtoDAO.cadastrar(celular);
	clienteDAO.cadastrar(cliente);
	em.getTransaction().commit();
	em.close();
    }

}
