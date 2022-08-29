package br.com.testes.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.testes.loja.dao.CategoriaDAO;
import br.com.testes.loja.dao.ProdutoDAO;
import br.com.testes.loja.util.JPAUtil;
import br.com.testes.loja.vo.Categoria;
import br.com.testes.loja.vo.Produto;

public class CadastroDeProduto {
    public static void main(String[] args) {
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
