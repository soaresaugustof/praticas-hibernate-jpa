package br.com.testes.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.testes.loja.vo.Produto;

public class ProdutoDAO {

    private EntityManager em;

    public ProdutoDAO(EntityManager em) {
	this.em = em;
    }

    public void cadastrar(Produto produto) {
	this.em.persist(produto);
    }

    public void atualizar(Produto produto) {
	this.em.merge(produto);
    }

    public void remover(Produto produto) {
	produto = em.merge(produto);
	this.em.remove(produto);
    }

    public Produto buscaPorId(long id) {
	return em.find(Produto.class, id);
    }

    public List<Produto> buscarProdutos() {
	String jpql = "select p from Produto p";
	return em.createQuery(jpql, Produto.class).getResultList();
    }

    public List<Produto> buscarPorNome(String nome) {
	String jpql = "select p from Produto p where p.nome = :nome";
	return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();
    }

    public List<Produto> buscarPorNomeDaCategoria(String nome) {
	String jpql = "select p from Produto p where p.categoria.nome = :nome";
	return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();
    }

    public BigDecimal buscarPrecoDoProdutoPorNome(String nome) {
	String jpql = "select p.preco from Produto p where p.nome = :nome";
	return em.createQuery(jpql, BigDecimal.class).setParameter("nome", nome).getSingleResult();
    }
}
