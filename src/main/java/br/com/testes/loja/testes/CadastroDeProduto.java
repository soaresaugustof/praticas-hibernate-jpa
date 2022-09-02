package br.com.testes.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.testes.loja.dao.CategoriaDAO;
import br.com.testes.loja.dao.ProdutoDAO;
import br.com.testes.loja.util.JPAUtil;
import br.com.testes.loja.vo.Categoria;
import br.com.testes.loja.vo.Produto;

public class CadastroDeProduto {
    public static void main(String[] args) {
	cadastrarProduto();
	Long id = 1L;
	EntityManager em = JPAUtil.getEntityManager();
	ProdutoDAO produtoDAO = new ProdutoDAO(em);
	Produto p = produtoDAO.buscaPorId(id);
	System.out.println(p.getPreco());
	List<Produto> produtos = produtoDAO.buscarPorNomeDaCategoria("CELULARES");
	for (Produto p2 : produtos) {
	    System.out.println(p2.getNome());
	}

	BigDecimal precoProduto = produtoDAO.buscarPrecoDoProdutoPorNome("Xiaomi Redmi");
	System.out.println("Preço do produto: " + precoProduto);
    }

    private static void cadastrarProduto() {
	Categoria celulares = new Categoria("CELULARES");
	Produto celular = new Produto("Xiaomi Redmi", "Topo de linha", new BigDecimal("800"), celulares);

	EntityManager em = JPAUtil.getEntityManager();
	ProdutoDAO produtoDAO = new ProdutoDAO(em);
	CategoriaDAO categoriaDAO = new CategoriaDAO(em);

	em.getTransaction().begin();
	categoriaDAO.cadastrar(celulares);
	produtoDAO.cadastrar(celular);
	em.getTransaction().commit();
	em.close();
    }
}
