package br.com.testes.loja.dao;

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
}
